package com.nickpoole.defendtheworld;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedDestroyedActor extends BaseActor {

    protected Animation animation;
    protected float elapsedTime;

    protected boolean destroyed;

    public AnimatedDestroyedActor() {
        super();
        animation = null;
        elapsedTime = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(destroyed) {
            setRegion( ((TextureRegion) animation.getKeyFrame(elapsedTime)).getTexture());
        }

        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (destroyed) {
            elapsedTime += delta;
        }

    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(elapsedTime);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

}
