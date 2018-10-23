package com.nickpoole.defendtheworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class DtWLevel1 implements Screen {

    private DtWGame game;
    private Stage stage;
    private BaseActor background;
    private BaseActor sun;
    private Planet planet;
    private Ship ship;

    private float x;
    private float y;
    private int radius = 100;
    private float angle = 10;

    public DtWLevel1(DtWGame game) {
        this.game = game;
        create();
    }

    public void create() {

        stage = new Stage();

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

        Texture planetTexture = new Texture(Gdx.files.internal("planet.png"));
        planet = new Planet(
                stage.getWidth() / 2 - planetTexture.getWidth() / 2,
                stage.getHeight() / 2 - planetTexture.getHeight() / 2
        );
        planet.setRegion(planetTexture);
        stage.addActor(planet);

        ship = new Ship();
        ship.setRegion(new Texture(Gdx.files.internal("ship.png")));
        ship.setPosition(0, 0);
        stage.addActor(ship);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {



        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
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
