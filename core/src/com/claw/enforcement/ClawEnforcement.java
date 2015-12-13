package com.claw.enforcement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.claw.enforcement.level.SampleLevel;

public class ClawEnforcement extends Game {
	
	FPSLogger fpsLogger = new FPSLogger();
	
	public static OrthographicCamera camera;
	public static FitViewport viewport;
	public static TextureAtlas textureAtlas;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(400, 400);
		viewport = new FitViewport(1920, 1080, camera);

		this.setScreen(new SampleLevel());
	}

	@Override
	public void render () {
		super.render();
		
		fpsLogger.log();
	}
}
