package com.nickpoole.defendtheworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DtWLevel1 implements Screen {

    private DtWGame game;
    private Stage stage;
    private BaseActor background;
    private BaseActor sun;
    private Planet planet;
    private Ship ship;
    private List<Asteroid> asteroids;
    private Animation explosion;

    private float asteroidTime = 0;
    private final int SPAWN_TIME = 2;

    public DtWLevel1(DtWGame game) {
        this.game = game;
        create();
    }

    public void create() {

        stage = new Stage();
        asteroids = new ArrayList<Asteroid>();

        background = new BaseActor();
        background.setRegion(new Texture(Gdx.files.internal("space-backdrop.png")));
        background.setPosition(0, 0);
        stage.addActor(background);

        sun = new BaseActor();
        sun.setRegion(new Texture(Gdx.files.internal("sun.png")));
        sun.setPosition(
                stage.getWidth() / 2 - sun.getWidth() / 2,
                stage.getHeight() / 2 - sun.getHeight() / 2
        );
        stage.addActor(sun);

        planet = new Planet();
        planet.setRegion(new Texture(Gdx.files.internal("planet.png")));
        stage.addActor(planet);

        ship = new Ship();
        ship.setRegion(new Texture(Gdx.files.internal("ship.png")));
        stage.addActor(ship);

        TextureRegion[] frames = new TextureRegion[10];
        for (int i = 0; i < 10; i++) {
            String fileName = "explosion/explosion" + i + ".png";
            Texture tex = new Texture(Gdx.files.internal(fileName));
            tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            frames[i] = new TextureRegion( tex );
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);

        explosion = new Animation(0.1f, framesArray, Animation.PlayMode.NORMAL);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        asteroidTime += delta;

        if (asteroidTime > SPAWN_TIME) {
            Asteroid asteroid = new Asteroid();
            asteroid.setRegion(new Texture(Gdx.files.internal("brown-asteroid.png")));
            asteroids.add(asteroid);
            stage.addActor(asteroid);
            asteroidTime = 0;
        }

        Iterator<Asteroid> iterator = asteroids.iterator();

        while (iterator.hasNext()) {

            Asteroid asteroid = iterator.next();

            if (asteroid.isAnimationFinished()) {
                asteroid.addAction(Actions.removeActor());
                iterator.remove();
            }

            Rectangle asteroidRectangle = asteroid.getBoundingRectangle();
            Rectangle shipRectangle = ship.getBoundingRectangle();
            Rectangle planetRectangle = planet.getBoundingRectangle();
            Rectangle sunRectangle = sun.getBoundingRectangle();

            if (shipRectangle.contains(asteroidRectangle) ||
                    sunRectangle.contains(asteroidRectangle) ||
                    planetRectangle.contains(asteroidRectangle)
            ) {
                asteroid.setAnimation(explosion);
            }
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
