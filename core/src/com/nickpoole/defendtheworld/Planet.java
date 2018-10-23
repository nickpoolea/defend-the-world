package com.nickpoole.defendtheworld;

public class Planet extends BaseActor {

    private final float originX;
    private final float originY;
    private final int anglePerFrame = 1;
    private float angle;

    public Planet(float originX, float originY) {
        super();
        this.originX = originX;
        this.originY = originY;
        angle = 0;
    }

    @Override
    public void act(float delta) {

        angle += delta * anglePerFrame;

        setPosition(
                (float) ( originX + 150 * Math.cos(angle)),
                (float) ( originY + 150 * Math.sin(angle))
        );
    }

}
