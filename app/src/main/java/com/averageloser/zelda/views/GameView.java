package com.averageloser.zelda.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.averageloser.zelda.entities.EnemyEntity;
import com.averageloser.zelda.entities.PlayerEntity;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by tj on 3/17/2016.
 */

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private volatile boolean playing;
    private Thread gameThread;
    private static final long rate = 17L; // target loop rate for 60fps.
    private long sleepTime; //How long the game loop should sleep between iterations.
    private Canvas canvas;
    private Paint paint;
    private SurfaceHolder ourHolder;
    private PlayerEntity player;
    private int screenWidth;
    private int screenHeight;

    private Queue commands = new ArrayDeque();

    private volatile boolean validSurface;

    private EnemyEntity[] enemies; //array of enemis.

    public GameView(Context context) {
        super(context);

        paint = new Paint();

        ourHolder = getHolder();
        ourHolder.addCallback(this);

        setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public void run() {
        while (playing) {
            if (validSurface) {
                long startTime = System.currentTimeMillis(); //When we started a loop.

                update();
                draw();
                control();

                long endTime = System.currentTimeMillis(); //When the loop ended.

                long loopTime = endTime - startTime; // How many ms it took to do a loop.

                //make sure sleepTime doesn't go negative, defaulting to 2ms to allow garbage collection, etc.
                sleepTime = ((rate - loopTime) < 0) ? 2L : rate - loopTime;

                try {
                    Thread.sleep(sleepTime); //subtract the loop time from the sleep rate.
                } catch (InterruptedException e) {
                    Log.e("game loop", e.getMessage());
                }
            }
        }
    }


    private void update() {
        player.act();

        for (EnemyEntity enemy : enemies) {
            enemy.act();
        }
    }

    private void draw() {
        canvas = ourHolder.lockCanvas();
        canvas.drawColor(Color.rgb(73, 149, 255));
        canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);

        for (EnemyEntity enemy : enemies) {
            canvas.drawBitmap(enemy.getBitmap(), enemy.getX(), enemy.getY(), paint);
        }

        ourHolder.unlockCanvasAndPost(canvas);
    }

    private void control() {

    }

    public void pause() {
        playing = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        playing = true;

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (validSurface) {

            int motionEvent = event.getAction();

            switch (motionEvent) {
                case MotionEvent.ACTION_UP:
                    player.stopBoosting();
                    return true;

                case MotionEvent.ACTION_DOWN:
                    player.setBoosting();
                    return true;
            }

            Log.i("MotionEvent", String.valueOf(event.getAction()));
        }

        return super.onTouchEvent(event);
    }

    //initialize game components;
    private void init() {
        player = new PlayerEntity(getContext(), screenWidth, screenHeight);

        enemies = new EnemyEntity[3];
        for (int i = 0; i < 3; i++) {
            EnemyEntity enemy = new EnemyEntity(getContext(), screenWidth, screenHeight);
            enemy.setX(screenWidth - (enemy.getBitmap().getWidth()));
            enemy.setY(i * (screenHeight / 3) + (screenHeight / 8));
            enemies[i] = enemy;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;

        Log.i("width and height", String.valueOf(w + " " + h));
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("surfacesize",
                String.valueOf(holder.getSurfaceFrame().width()
                        + " "
                        + holder.getSurfaceFrame().height()));

        init();

        validSurface = holder.getSurface().isValid();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
