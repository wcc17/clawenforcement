package com.claw.enforcement.screen;

import java.util.ArrayList;
import java.util.List;

import listener.PlayerInputListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.claw.enforcement.ClawEnforcement;
import com.claw.enforcement.entity.Player;
import com.claw.enforcement.level.Level;

public class LevelScreen implements Screen {
	Texture backgroundTexture1;
	Texture groundTexture1;
	Texture foregroundTexture1;
	
	protected static Image background1;
	protected static Image ground1;
	protected static Image foreground1;
	
	boolean indoorLevel = false;
	
	protected boolean stageMoveUp;
	protected boolean stageMoveDown;
	protected boolean cameraMoveLeft;
	protected boolean cameraMoveRight;
	float cameraMoveSpeed = 0.1f;
	float moveSpeed = 0.05f;
	
	//could probably move this and some other stuff to Level instead of LevelScreen
	Stage stage;
	
	List<String> backGroundImageNames = new ArrayList<String>();
	List<String> groundImageNames = new ArrayList<String>();
	int currentBackgroundImageIndex = 0;

	public LevelScreen() {
		stage = new Stage(ClawEnforcement.viewport);
	}
	
	//handle how much of foreground, ground, and background are shown
	//depending on the players position
	public void handleSceneryMovement(float delta) {
		if(stageMoveUp) {
			ground1.addAction(Actions.moveBy(0, moveSpeed/delta));
			if(indoorLevel) {
				background1.addAction(Actions.moveBy(0, moveSpeed/delta));
			}
		}
		
		if(stageMoveDown) {
			ground1.addAction(Actions.moveBy(0, -moveSpeed/delta));
			if(indoorLevel) {
				background1.addAction(Actions.moveBy(0, -moveSpeed/delta));
			}
		}
	}
	
	public void handleCameraMovement(float delta) {
		if(cameraMoveLeft) {
			ClawEnforcement.camera.position.x -= cameraMoveSpeed/delta;
			ClawEnforcement.camera.update();
		}
		if(cameraMoveRight) {
			ClawEnforcement.camera.position.x += cameraMoveSpeed/delta;
			ClawEnforcement.camera.update();
		}
	}
	
	public void initializeInputListener() {
		PlayerInputListener playerInputListener = new PlayerInputListener();
		stage.addListener(playerInputListener);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		initializeInputListener();
		
		if(background1 != null) stage.addActor(background1);
		if(ground1 != null) stage.addActor(ground1);
		if(foreground1 != null) stage.addActor(foreground1);
		
		if(Level.player != null) stage.addActor(Level.player);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		handleSceneryMovement(delta);
		handleCameraMovement(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void hide() {
		dispose(); 
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		if(foregroundTexture1 != null) foregroundTexture1.dispose();
		if(groundTexture1 != null) groundTexture1.dispose();
		if(backgroundTexture1 != null) backgroundTexture1.dispose();
	}

	public boolean isStageMoveUp() {
		return stageMoveUp;
	}

	public void setStageMoveUp(boolean stageMoveUp) {
		this.stageMoveUp = stageMoveUp;
	}

	public boolean isStageMoveDown() {
		return stageMoveDown;
	}

	public void setStageMoveDown(boolean stageMoveDown) {
		this.stageMoveDown = stageMoveDown;
	}

	public boolean isCameraMoveLeft() {
		return cameraMoveLeft;
	}

	public void setCameraMoveLeft(boolean cameraMoveLeft) {
		this.cameraMoveLeft = cameraMoveLeft;
	}

	public boolean isCameraMoveRight() {
		return cameraMoveRight;
	}

	public void setCameraMoveRight(boolean cameraMoveRight) {
		this.cameraMoveRight = cameraMoveRight;
	}
}
