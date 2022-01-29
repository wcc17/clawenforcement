package com.claw.enforcement.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.claw.enforcement.entity.Player;
import com.claw.enforcement.level.Level;

public class SampleLevelScreen extends LevelScreen {
	
	public SampleLevelScreen() {
		super();
		
		initializeBackground();
		
		//NEED TO DISPOSE THIS
		TextureAtlas playerAtlas;
		playerAtlas = new TextureAtlas(Gdx.files.internal("player/catPack.atlas"));
		Level.player = new Player(playerAtlas, "movingCat1");
		Level.player.setPosition( (1920 / 2) - 512/2, 100);
	}
	
	public void initializeBackground() {
		indoorLevel = true;
		
		backGroundImageNames.add("level/backgroundWindow1Scaled.png");
		backGroundImageNames.add("level/backgroundWindow2Scaled.png");
		backGroundImageNames.add("level/backgroundWindow3Scaled.png");
		backGroundImageNames.add("level/backgroundWindow1Scaled.png");
		backGroundImageNames.add("level/backgroundWindow2Scaled.png");
		backGroundImageNames.add("level/backgroundWindow3Scaled.png");
		backGroundImageNames.add("level/backgroundWindow1Scaled.png");
		backGroundImageNames.add("level/backgroundWindow2Scaled.png");
		backGroundImageNames.add("level/backgroundWindow3Scaled.png");
		backGroundImageNames.add("level/backgroundWindow1Scaled.png");
		backGroundImageNames.add("level/backgroundWindow2Scaled.png");
		backGroundImageNames.add("level/backgroundWindow3Scaled.png");
		backGroundImageNames.add("level/backgroundWindow1Scaled.png");
		backGroundImageNames.add("level/backgroundWindow2Scaled.png");
		backGroundImageNames.add("level/backgroundWindow3Scaled.png");
		
		groundImageNames.add("level/foregroundTable1Scaled.png");
		groundImageNames.add("level/foregroundTable2Scaled.png");
		groundImageNames.add("level/foregroundTable3Scaled.png");
		groundImageNames.add("level/foregroundTable1Scaled.png");
		groundImageNames.add("level/foregroundTable2Scaled.png");
		groundImageNames.add("level/foregroundTable3Scaled.png");
		groundImageNames.add("level/foregroundTable1Scaled.png");
		groundImageNames.add("level/foregroundTable2Scaled.png");
		groundImageNames.add("level/foregroundTable3Scaled.png");
		groundImageNames.add("level/foregroundTable1Scaled.png");
		groundImageNames.add("level/foregroundTable2Scaled.png");
		groundImageNames.add("level/foregroundTable3Scaled.png");
		groundImageNames.add("level/foregroundTable1Scaled.png");
		groundImageNames.add("level/foregroundTable2Scaled.png");
		groundImageNames.add("level/foregroundTable3Scaled.png");
		
		this.backgroundTexture1 = new Texture(backGroundImageNames.get(0));
		this.groundTexture1 = new Texture(groundImageNames.get(0));
		
		this.background1 = new Image(backgroundTexture1);
		this.ground1 = new Image(groundTexture1);
		
		background1.setPosition(background1.getImageX(), 1080 - (background1.getHeight() - 100));
		ground1.setPosition(ground1.getImageX(), -300);
	}
	
}
