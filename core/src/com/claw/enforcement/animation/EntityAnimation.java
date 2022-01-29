package com.claw.enforcement.animation;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityAnimation {
	
	protected TextureAtlas atlas;
	protected float stateTime = 0f;
	protected TextureRegion currentFrame;
	
	public EntityAnimation(TextureAtlas atlas) {
		this.atlas = atlas;
	}
	
	public float getStateTime() {
		return this.stateTime;
	}
	
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
	
	public TextureRegion getCurrentFrame() {
		return this.currentFrame;
	}
	
	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}
	
}
