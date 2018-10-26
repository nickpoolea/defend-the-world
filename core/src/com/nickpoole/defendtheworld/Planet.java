package com.nickpoole.defendtheworld;

import com.badlogic.gdx.Gdx;

public class Planet extends BaseActor {

    private final int anglePerFrame = 1;
    private float angle;

    public Planet() {
        super();
        angle = 0;

        System.out.println(" width: " + Gdx.graphics.getWidth());
        System.out.println(" Height: " + Gdx.graphics.getHeight());
    }

    @Override
    public void act(float delta) {

        angle += delta * anglePerFrame;

        float originX = Gdx.graphics.getWidth() / 2 - getWidth() / 2;
        float originY = Gdx.graphics.getHeight() / 2 - getHeight() / 2;

        setPosition(
                (float) ( originX + 150 * Math.cos(angle) ),
                (float) ( originY + 150 * Math.sin(angle) )
        );
    }

}
