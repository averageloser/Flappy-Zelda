package com.averageloser.zelda.entities;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by tj on 3/23/2016.
 */
public abstract class GameEntity {
    private Context context;
    private Bitmap bitmap;
    private int x;
    private int y;
    private int screenX; //width of screen.
    private int screenY; //height of screen.
    private int gravity;


    public GameEntity(Context context, int screenX, int screenY) {
        this.context = context;
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getScreenX() {
        return screenX;
    }

    /*
    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }
    */

    public int getScreenY() {
        return screenY;
    }

    /*
    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }
    */
}
