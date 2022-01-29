package com.claw.enforcement.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.claw.enforcement.animation.PlayerAnimation;

public class Player extends Entity {
	
	private PlayerAnimation playerAnimation;
	
	private float moveSpeed  = 0.105f;
	
	private boolean isScratching = false;
	private boolean isSecondScratching = false;
	
	public Player(TextureAtlas atlas, String originalSprite) {
		super(atlas, originalSprite);
		
		playerAnimation = new PlayerAnimation(atlas);
	}
	
	public void updatePlayerFrame() {
		playerAnimation.setStateTime(playerAnimation.getStateTime() + Gdx.graphics.getDeltaTime());
		
		boolean isMoving;
		if( (moveLeft || moveRight || moveUp || moveDown) && !isScratching) {
			playerAnimation.handleWalk();
			isMoving = true;
		} else if( (moveLeft || moveRight || moveUp || moveDown) && isScratching) {
			isMoving = true;
		} else {
			playerAnimation.handleIdle();
			isMoving = false;
		}
		
		if(!isScratching) {
			playerAnimation.handleScratchDelay(Gdx.graphics.getDeltaTime());
		} else {
			isScratching = playerAnimation.handleScratchAttack(Gdx.graphics.getDeltaTime(), isMoving);
		}
		
		playerAnimation.handleBlinking(isMoving);
		
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
		TextureRegion currentFrame = playerAnimation.getCurrentFrame();
		
		Texture texture = currentFrame.getTexture();
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//TODO: GET RID OF
//		if( !("movingCat1").equals(currentFrame.toString())
//				&& !("movingCat2").equals(currentFrame.toString()) 
//				&& !("movingCat3").equals(currentFrame.toString()) ) {
//			System.out.println(currentFrame);
//		}
		
		batch.draw(texture, this.getX(), this.getY(), this.getOriginX(), 
			this.getOriginY(), currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), this.getScaleX(),
			this.getScaleY(), this.getRotation(), currentFrame.getRegionX(), currentFrame.getRegionY(),
			(int)currentFrame.getRegionWidth(), (int)currentFrame.getRegionHeight(), movingRight, false);
		
	}

	public boolean isScratching() {
		return isScratching;
	}

	public void setScratching(boolean isScratching) {
		this.isScratching = isScratching;
	}
	
	public boolean isSecondScratching() {
		return isSecondScratching;
	}
	
	public void setSecondScratching(boolean isSecondScratching) {
		this.isSecondScratching = isSecondScratching;
	}
	
	public boolean noScratch() {
		return playerAnimation.isNoScratch();
	}
}













//package com.claw.enforcement.entity;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.Texture.TextureFilter;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//import com.claw.enforcement.animation.PlayerAnimation;
//
//public class Player extends Entity {
//	
//	private PlayerAnimation playerAnimation;
//	
//	private float moveSpeed  = 0.105f;
//	
//	private boolean firstScratchActivated = false;
//	private boolean secondScratchActivated = false;
//	
//	public Player(TextureAtlas atlas, String originalSprite) {
//		super(atlas, originalSprite);
//		
//		playerAnimation = new PlayerAnimation(atlas);
//	}
//	
//	public void updatePlayerFrame() {
//		playerAnimation.setStateTime(playerAnimation.getStateTime() + Gdx.graphics.getDeltaTime());
//		
//		boolean isMoving;
//		if( (moveLeft || moveRight || moveUp || moveDown) && !firstScratchActivated) {
//			playerAnimation.handleWalk();
//			isMoving = true;
//		} else if( (moveLeft || moveRight || moveUp || moveDown) && firstScratchActivated) {
//			isMoving = true;
//		} else {
//			playerAnimation.handleIdle();
//			isMoving = false;
//		}
//		
//		handlePlayerControls(isMoving);
//		
//		playerAnimation.handleBlinking(isMoving);
//		
//	}
//	
//	public void handlePlayerControls(boolean isMoving) {
//		if(!firstScratchActivated) {
//			playerAnimation.handleScratchDelay(Gdx.graphics.getDeltaTime());
//		} else {
//			firstScratchActivated = playerAnimation.handleScratchAttack(Gdx.graphics.getDeltaTime(), isMoving);
//		}
//	}
//	
//	@Override
//	public void act(float delta) {
//		super.act(delta);
//		
//		updatePlayerFrame();
//		
//		move(delta);
//	}
//	
//	@Override
//	public void move(float delta) {
//		if(moveLeft) {
//			this.addAction(Actions.moveBy(-moveSpeed/delta, 0));
//			movingRight = false;
//		}
//		if(moveRight) {
//			this.addAction(Actions.moveBy(moveSpeed/delta, 0));
//			movingRight = true;
//		}
//		if(moveUp) {
//			this.addAction(Actions.moveBy(0, moveSpeed/delta));
//		}
//		if(moveDown) {
//			this.addAction(Actions.moveBy(0, -moveSpeed/delta));
//		}
//	}
//	
//	@Override
//	public void draw(Batch batch, float alpha) {
//		TextureRegion currentFrame = playerAnimation.getCurrentFrame();
//		
//		Texture texture = currentFrame.getTexture();
//		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		
//		//TODO: GET RID OF
////		if( !("movingCat1").equals(currentFrame.toString())
////				&& !("movingCat2").equals(currentFrame.toString()) 
////				&& !("movingCat3").equals(currentFrame.toString()) ) {
////			System.out.println(currentFrame);
////		}
//		
//		batch.draw(texture, this.getX(), this.getY(), this.getOriginX(), 
//			this.getOriginY(), currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), this.getScaleX(),
//			this.getScaleY(), this.getRotation(), currentFrame.getRegionX(), currentFrame.getRegionY(),
//			(int)currentFrame.getRegionWidth(), (int)currentFrame.getRegionHeight(), movingRight, false);
//		
//	}
//
//	public boolean firstScratchActivated() {
//		return firstScratchActivated;
//	}
//
//	public void setFirstScratchActivated(boolean firstScratchActivated) {
//		this.firstScratchActivated = firstScratchActivated;
//	}
//	
//	public boolean secondScratchActivated() {
//		return secondScratchActivated;
//	}
//	
//	public void setSecondScratchActivated(boolean secondScratchActivated) {
//		this.secondScratchActivated = secondScratchActivated;
//	}
//	
//	public boolean noScratch() {
//		return playerAnimation.isNoScratch();
//	}
//}
//
