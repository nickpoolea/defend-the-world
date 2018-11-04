package com.nickpoole.defendtheworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class DtWLevel1 implements Screen {

    private DtWGame game;
    private Stage stage;
    private BaseActor background;
    private BaseActor sun;
    private Planet planet;
    private Ship ship;
    private Array<Asteroid> asteroids;
    private Animation explosion;
    private float elapsedTime;

    private Label planetHealthLabel;

    private int planetHealth;

    private float spawnTimer = 0;
    private double spawnInterval = 0.5;
    private final int planetDamage = 10;

    public DtWLevel1(DtWGame game) {
        this.game = game;
        create();
    }

    public void create() {

        planetHealth = 100;
        elapsedTime = 0;

        stage = new Stage();
        System.out.println("SIZE: " + stage.getActors().size);
        asteroids = new Array<Asteroid>();

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

        explosion = new Animation(0.05f, framesArray, Animation.PlayMode.NORMAL);


        BitmapFont font = new BitmapFont();
        LabelStyle style = new LabelStyle( font, Color.WHITE );

        planetHealthLabel = new Label("Planet Health: 100", style);
        planetHealthLabel.setFontScale(2);
        planetHealthLabel.setPosition(
                20, stage.getHeight() - planetHealthLabel.getHeight() * 2
        );
        stage.addActor(planetHealthLabel);

    }

    @Override
    public void render(float delta) {

        spawnTimer += delta;
        elapsedTime += delta;

        if (elapsedTime > 5) {
            spawnInterval -= 0.1;
            elapsedTime = 0;
        }

        if (spawnTimer > spawnInterval) {
            spawnTimer = 0;
            Asteroid asteroid = new Asteroid();
            asteroid.setRegion(new Texture(Gdx.files.internal("brown-asteroid.png")));
            asteroid.setAnimation(explosion);
            asteroids.add(asteroid);
            stage.addActor(asteroid);
        }

        Iterator<Asteroid> iterator = asteroids.iterator();

        while (iterator.hasNext()) {

            Asteroid asteroid = iterator.next();

            if (asteroid.isAnimationFinished()) {
                asteroid.addAction(Actions.removeActor());
                iterator.remove();
            }

            if (asteroid.isInteractive()) {
                Rectangle asteroidRectangle = asteroid.getBoundingRectangle();
                Rectangle shipRectangle = ship.getBoundingRectangle();
                Rectangle planetRectangle = planet.getBoundingRectangle();
                Rectangle sunRectangle = sun.getBoundingRectangle();

                if (sunRectangle.contains(asteroidRectangle)) {
                    asteroid.setDestroyed(true);
                }

                if (planetRectangle.overlaps(asteroidRectangle)) {
                    asteroid.setDestroyed(true);
                    asteroid.setInteractive(false);
                    planetHealth -= planetDamage;
                }

                if (shipRectangle.overlaps(asteroidRectangle)) {
                    asteroid.reverseDirection();
                }
            }
        }

        if (planetHealth > 0) {
            planetHealthLabel.setText("Planet Health: " + planetHealth);

            Gdx.gl.glClearColor(0,0,0,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();
        } else {
            game.setScreen(new DtwGameEnd(game));
        }

    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
