package com.nickpoole.defendtheworld;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class DtWMenu implements Screen, InputProcessor {

    private DtWGame game;
    private Stage stage;

    public DtWMenu(DtWGame game) {
        super();
        this.game = game;
        create();
    }

    public void create() {

        stage = new Stage();

        InputMultiplexer im = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor( im );

        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle( font, Color.WHITE );

        String text;

        text = "Defend the World!";

        Label titleLabel = new Label(text, style);
        titleLabel.setFontScale(3);
        titleLabel.setPosition(
                stage.getWidth() / 2 - (titleLabel.getWidth() / 2) * 3,
                Gdx.graphics.getHeight() - titleLabel.getHeight() * 4
        );
        stage.addActor(titleLabel);

        text = "Use the mouse to deflect asteroids from hitting the planet";
        Label directionsLabel = new Label(text, style);
        directionsLabel.setFontScale(2);
        directionsLabel.setPosition(
                stage.getWidth() / 2 - (directionsLabel.getWidth() / 2) * 2,
                Gdx.graphics.getHeight() - directionsLabel.getHeight() * 8
        );
        stage.addActor(directionsLabel);

        text = "Press SPACE to begin";
        Label startLabel = new Label(text, style);
        startLabel.setFontScale(3);
        startLabel.setPosition(
                stage.getWidth() / 2 - (startLabel.getWidth() / 2) * 3,
                Gdx.graphics.getHeight() - startLabel.getHeight() * 12
        );
        stage.addActor(startLabel);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {

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

    @Override
    public boolean keyDown(int keycode) {
        
        if (keycode == Input.Keys.SPACE) {
            game.setScreen(new DtWLevel1(game));
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
