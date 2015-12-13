package com.claw.enforcement.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.claw.enforcement.entity.Player;

public class SampleLevel extends Level {
	
	public SampleLevel() {
		super();
		
		indoorLevel = true;
		
		TextureAtlas playerAtlas;
		playerAtlas = new TextureAtlas(Gdx.files.internal("player/catPack.atlas"));
		this.player = new Player(playerAtlas, "movingCat1");
		this.player.setPosition(75, 100);
		
		this.backgroundTexture = new Texture("level/backgroundWindowScaled.png");
		this.groundTexture = new Texture("level/foregroundTableScaled.png");
		
		this.background = new Image(backgroundTexture);
		this.ground = new Image(groundTexture);

		//these need to be fucking constants
		background.setPosition(ground.getImageX(), 1080 - (background.getHeight() - 100));
		ground.setPosition(ground.getImageX(), -300);
	}
	
}
