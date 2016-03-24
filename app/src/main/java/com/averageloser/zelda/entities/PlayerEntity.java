package com.averageloser.zelda.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.averageloser.zelda.R;

/**
 * Created by tj on 3/17/2016.
 */
public class PlayerEntity extends MovableEntity {

    private int screenX; //width of screen.
    private int screenY; //height of screen.
    private volatile boolean boosting;

    public PlayerEntity(Context context, int screenX, int screenY) {
        super(context, screenX, screenY);

        Bitmap image = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.linkswordright);

        setBitmap(Bitmap.createScaledBitmap(image,
                image.getWidth() * 2,
                image.getHeight() * 2,
                true));

        this.screenX = screenX;

        this.screenY = screenY;

        Log.i("width and height", String.valueOf(screenX + " " + screenY));

        setX(50);

        setY(50);

        setSpeed(1);

        setMinSpeed(1);

        setMaxSpeed(10);

        setMinX(0);

        setMinY(0);

        setMaxY(this.screenY - getBitmap().getHeight());

        setMaxX(this.screenX - getBitmap().getWidth());

        setGravity(5);

        boosting = false;
    }


    public synchronized void setBoosting() {
        boosting = true;
    }

    public synchronized void stopBoosting() {
        boosting = false;
    }

    public void act() {
        move();
        update();
    }

    @Override
    public void move() {
        //setX(getX() + getSpeed());
        //Check to see if we are boosting.
        if (boosting) {
            setSpeed(getSpeed() + 2);

            setY(getY() - (getGravity() * 2)); //move up 2x gravity.
        } else {
            setSpeed(getSpeed() - 5);
        }

        //Never stop completely.
        if (getSpeed() < getMinSpeed()) {
            setSpeed(getMinSpeed());
        }

        //do some logging.
        //Log.i("X", String.valueOf(x));

        //Log.i("Y", String.valueOf(y));

        //Log.i("boosting", String.valueOf(boosting));
    }

    //Used to update the state of game entities.
    @Override
    public void update() {
        //Apply gravity to the ship.
        setY(getY() + getGravity());

        //Constrain the top speed.
        if (getSpeed() > getMaxSpeed()) {
            setSpeed(getMaxSpeed());
        }

        //constrain the ship to the top screen bounds.
        if (getY() < getMinY()) {
            setY(getMinY());
        }

        //constrain ship to bottom screen bounds.
        if (getY() > getMaxY()) {
            setY(getMaxY());
        }

        //constrain ship to the left.
        if (getX() < getMinX())  {
            setX(getMinX());
        }

        //constrain ship to the right.
        if (getX() > getMaxX()) {
            setX(getMaxX()); //reset x to 0 if it goes out of bounds.
        }
    }
 }
