package com.claw.enforcement.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends Entity {
	
	private static final int STANDING_FRAME = 0;
	
	private float moveSpeed  = 0.105f;
	private TextureAtlas playerAtlas;
	
	private float walkAnimationSpeed = 0.075f;
	private Animation walkAnimation;
	private TextureRegion[] walkFrames;
	
	private float blinkAnimationSpeed = 0.075f;
	private Animation blinkAnimation;
	private TextureRegion[] blinkFrames;
	private Animation standingBlinkAnimation;
	private TextureRegion[] standingBlinkFrames;
	
	private boolean blinking;
	private float notBlinkingInterval = 0f;
	
	private float stateTime = 0f;
	private TextureRegion currentFrame;
	
	public Player(TextureAtlas atlas, String originalSprite) {
		super(atlas, originalSprite);
		
		playerAtlas = atlas;
		
		stateTime = 0f;
		
		createWalkAnimation();
		createBlinkAnimation();
		createStandingBlinkAnimation();
	}
	
	public void createWalkAnimation() {
		AtlasRegion region1 = playerAtlas.findRegion("movingCat1");
		AtlasRegion region2 = playerAtlas.findRegion("movingCat2");
		AtlasRegion region3 = playerAtlas.findRegion("movingCat3");

		walkFrames = new TextureRegion[] {region1, region2, region3};
		walkAnimation = new Animation(walkAnimationSpeed, walkFrames);	//NEED A CONSTANT HERE
	}
	
	public void createBlinkAnimation() {
		//using movingCat1 because its eyes are completely open and we're only going to be using the face from these
		AtlasRegion region1 = playerAtlas.findRegion("movingCat1");
		AtlasRegion region2 = playerAtlas.findRegion("movingblink1Cat3");
		AtlasRegion region3 = playerAtlas.findRegion("movingblink2Cat3");
		
		blinkFrames = new TextureRegion[] {region1, region2, region3};
		blinkAnimation = new Animation(blinkAnimationSpeed, blinkFrames);
	}
	
	public void createStandingBlinkAnimation() {
		//using movingCat1 because its eyes are completely open and we're only going to be using the face from these
		AtlasRegion region1 = playerAtlas.findRegion("movingCat1");
		AtlasRegion region2 = playerAtlas.findRegion("standingBlink1");
		AtlasRegion region3 = playerAtlas.findRegion("standingBlink2");
		
		standingBlinkFrames = new TextureRegion[] {region1, region2, region3};
		standingBlinkAnimation = new Animation(blinkAnimationSpeed, standingBlinkFrames);
	}
	
	public void handleWalk() {
		if(!blinking) {
			//doing the animation this way is necessary or isAnimationFinsihed() won't work
			currentFrame = walkAnimation.getKeyFrame(stateTime, false);
			if(walkAnimation.isAnimationFinished(stateTime)) {
				stateTime = 0;
			}
		}
	}
	
	//passing in animation here to recycle blinking code for both moving and standing still
	public void handleBlinking(Animation animation) {
		notBlinkingInterval += Gdx.graphics.getDeltaTime();
		
		//if not blinking
		//increase blinking interval before next blink
		//if its time for next blink, set blink to true, reset interval
		//keep walking if not blinking
		if(!blinking) {
			if(notBlinkingInterval > 1) {
				blinking = true;
				notBlinkingInterval = 0;
			}
		} 
		
		//if blinking
		//make sure walk animation is finished
		//set current frame to blink animation
		//if blink animation is finished. reset statetime. blinking to false
		if(blinking) {
			if(walkAnimation.isAnimationFinished(stateTime)) {
				currentFrame = animation.getKeyFrame(stateTime, false);
			}
			
			if(animation.isAnimationFinished(stateTime)) {
				stateTime = 0;
				blinking = false;
			}
		}
	}
	
	public void handleIdle() {
		if(!blinking) {
			currentFrame = walkFrames[STANDING_FRAME];
		}
	}
	
	public void updatePlayerFrame() {
		stateTime += Gdx.graphics.getDeltaTime();
		
		if(moveLeft || moveRight || moveUp || moveDown) {
			handleWalk();
			handleBlinking(blinkAnimation);
		} else {
			handleIdle();
			handleBlinking(standingBlinkAnimation);
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		updatePlayerFrame();
		
		move(delta);
	}
	
	@Override
	public void move(float delta) {
		if(moveLeft) {
			this.addAction(Actions.moveBy(-moveSpeed/delta, 0));
			movingRight = false;
		}
		
		if(moveRight) {
			this.addAction(Actions.moveBy(moveSpeed/delta, 0));
			movingRight = true;
		}
		
		if(moveUp) {
			this.addAction(Actions.moveBy(0, moveSpeed/delta));
		}
		
		if(moveDown) {
			this.addAction(Actions.moveBy(0, -moveSpeed/delta));
		}
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		Texture texture = currentFrame.getTexture();
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		batch.draw(texture, this.getX(), this.getY(), this.getOriginX(), 
			this.getOriginY(), currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), this.getScaleX(),
			this.getScaleY(), this.getRotation(), currentFrame.getRegionX(), currentFrame.getRegionY(),
			(int)currentFrame.getRegionWidth(), (int)currentFrame.getRegionHeight(), movingRight, false);
		
	}
}
