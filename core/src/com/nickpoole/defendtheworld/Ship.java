package com.nickpoole.defendtheworld;

import com.badlogic.gdx.Gdx;

public class Ship extends BaseActor {

    @Override
    public void act(float delta) {

        setPosition(
                Gdx.input.getX() - getWidth() / 2,
                Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight()/2
        );

    }
}
