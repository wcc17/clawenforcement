package com.claw.enforcement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.claw.enforcement.level.Level;
import com.claw.enforcement.level.SampleLevel;
import com.claw.enforcement.screen.SampleLevelScreen;

public class ClawEnforcement extends Game {
	
	FPSLogger fpsLogger = new FPSLogger();
	
	public static OrthographicCamera camera;
	public static FitViewport viewport;
	public static TextureAtlas textureAtlas;
	public static Level currentLevel;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(400, 400);
		viewport = new FitViewport(1920, 1080, camera);
		
		currentLevel = new SampleLevel();
		this.setScreen(Level.currentLevelScreen);
	}

	@Override
	public void render () {
		super.render();
		
		fpsLogger.log();
	}
}
