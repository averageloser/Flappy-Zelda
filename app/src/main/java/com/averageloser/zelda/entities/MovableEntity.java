package com.averageloser.zelda.entities;

import android.content.Context;

import com.averageloser.zelda.interfaces.Movable;
import com.averageloser.zelda.interfaces.Updatable;

/**
 * Created by tj on 3/23/2016.
 */
public abstract class MovableEntity extends GameEntity implements Movable, Updatable {
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int speed;
    private int maxSpeed;
    private int minSpeed;

    public MovableEntity(Context context, int screenX, int screenY) {
        super(context, screenX, screenY);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Override
    public abstract void move();

    @Override
    public abstract  void update();

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }
}
