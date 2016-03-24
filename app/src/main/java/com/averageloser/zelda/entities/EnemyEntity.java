package com.averageloser.zelda.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.averageloser.zelda.R;

/**
 * Created by tj on 3/23/2016.
 *
 * So this class represents the enemies in the game.   In a real game, it would be a better idea
 * to make more specific classes.
 * */

public class EnemyEntity extends MovableEntity {

    public EnemyEntity(Context context, int screenX, int screenY) {
        super(context, screenX, screenY);

        Bitmap image = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.octarok_left);

        setBitmap(Bitmap.createScaledBitmap(image,
                image.getWidth() * 2,
                image.getHeight() * 2,
                true));

        setSpeed(3);

        setMinX(0);

        setMaxX(screenX - getBitmap().getWidth());
        setMinY(0);

        setMaxY(screenY - getBitmap().getHeight());
    }

    public void act() {
        move();
        update();
    }

    @Override
    public void move() {
        setX(getX() - getSpeed());
    }

    @Override
    public void update() {
        //constrain enemy to top.
        if (getY() < getMinY()) {
            setY(getMinY());
        }

        //constrain enemy to bottom.
        if (getY() > getMaxY() ) {
            setY(getMaxY());
        }

        //reset enemy to right of screen when it leaves.
        if (getX() < getMinX()) {
            setX(getMaxX());
        }
    }
}
