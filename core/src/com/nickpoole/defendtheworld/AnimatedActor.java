package com.nickpoole.defendtheworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedActor extends BaseActor {

    private Animation animation;
    private float elapsedTime;

    public AnimatedActor() {
        super();
        animation = null;
        elapsedTime = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(animation != null) {
            setRegion( ((TextureRegion) animation.getKeyFrame(elapsedTime)).getTexture());
        }

        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (animation != null) {
            elapsedTime += delta;
        }

    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public boolean isAnimationFinished() {
        if (animation == null) {
            return false;
        }
        return animation.isAnimationFinished(elapsedTime);
    }

}
