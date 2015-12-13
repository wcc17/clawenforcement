package com.claw.enforcement.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor {
	
	boolean moveLeft;
	boolean moveRight;
	boolean moveUp;
	boolean moveDown;
	boolean movingRight = false;
	
	public Entity(TextureAtlas atlas, String originalSprite) {
		super();
	}
	
	public void move(float delta) {

	}
	
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}
	
	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}
}
