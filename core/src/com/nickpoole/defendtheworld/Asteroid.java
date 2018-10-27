package com.nickpoole.defendtheworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Asteroid extends AnimatedDestroyedActor {

    private Vector2 position;
    private Vector2 destination;
    private Vector2 velocity;
    private int speed = 300;
    private boolean interactive;

    public Asteroid() {
        super();
        position = new Vector2();
        velocity = new Vector2();
        destination = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        interactive = true;
        generateCoordinates();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (!destroyed) {
            velocity.set(
                    destination.x - position.x - getWidth() / 2,
                    destination.y - position.y - getHeight() / 2);
            velocity.nor();

            velocity.x *= speed * delta;
            velocity.y *= speed * delta;

            position.add(velocity);
            setPosition(position.x, position.y);
        }

    }

    public void generateCoordinates() {
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();

        Random r = new Random();
        float startX = 0 - getWidth();
        float startY = 0 - getHeight();

        if(r.nextBoolean()) {
            startX = r.nextInt(width + 1);
        } else {
            startY = r.nextInt(height + 1);
        }

        setPosition(startX, startY);
        position.set(startX, startY);
    }

    public void reverseDirection() {
        speed *= -1;
        setInteractive(false);
    }

    public boolean isInteractive() {
        return interactive;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }


}
