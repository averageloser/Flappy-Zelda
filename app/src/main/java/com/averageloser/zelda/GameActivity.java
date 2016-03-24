package com.averageloser.zelda;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.averageloser.zelda.views.GameView;

/**
 * Created by tj on 3/17/2016.
 */
public class GameActivity extends Activity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(gameView = new GameView(this));
    }

    @Override
    protected void onPause() {
        super.onPause();

        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        gameView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("Destroyed", "OnDestroy() called");
    }

}
