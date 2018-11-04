package com.nickpoole.defendtheworld;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DtwGameEnd implements Screen, InputProcessor {

    private DtWGame game;
    private Stage stage;

    public DtwGameEnd(DtWGame game) {
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

        Table table = new Table();
        table.setFillParent(true);

        Label gameStatusLabel = new Label("You Lose!", style);
        gameStatusLabel.setFontScale(3);
        table.add(gameStatusLabel);
        table.row();

        Label gameReplayLabel = new Label("Press SPACE to play again.", style);
        gameReplayLabel.setFontScale(3);
        table.add(gameReplayLabel);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            Gdx.input.setInputProcessor(null);
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
