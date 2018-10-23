package com.nickpoole.defendtheworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseActor extends Actor {

    private TextureRegion region;

    public BaseActor() {

        super();
    }

    public void setRegion(Texture texture) {

        if (region == null) {
            region = new TextureRegion();
        }
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        region.setRegion(texture);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        batch.draw( region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }

}
