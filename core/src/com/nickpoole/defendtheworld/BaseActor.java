package com.nickpoole.defendtheworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseActor extends Actor {

    protected   TextureRegion region;
    private Rectangle boundary;

    public BaseActor() {
        super();
        region = new TextureRegion();
        boundary = new Rectangle();
    }

    public void setRegion(Texture texture) {
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        region.setRegion(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        batch.draw( region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }

    public Rectangle getBoundingRectangle() {
        boundary.set( getX(), getY(), getWidth(), getHeight() );
        return boundary;
    }

}
