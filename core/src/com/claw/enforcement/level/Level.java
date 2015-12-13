package com.claw.enforcement.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.claw.enforcement.ClawEnforcement;
import com.claw.enforcement.entity.Player;

public class Level implements Screen {
	Texture backgroundTexture;
	Texture groundTexture;
	Texture foregroundTexture;
	Texture playerTexture;
	Image background;
	Image ground;
	Image foreground;
	
	boolean indoorLevel = false;
	
	boolean stageMoveUp;
	boolean stageMoveDown;
	float moveSpeed = 0.05f;
	
	ClawEnforcement game;
	Stage stage;
	
	Player player;

	public Level() {
		stage = new Stage(ClawEnforcement.viewport);
	}
	
	//handle how much of foreground, ground, and background are shown
	//depending on the players position
	public void handleScenery(float delta) {
		if(stageMoveUp) {
			ground.addAction(Actions.moveBy(0,  moveSpeed/delta));
			if(indoorLevel) {
				background.addAction(Actions.moveBy(0, moveSpeed/delta));
			}
		}
		
		if(stageMoveDown) {
			ground.addAction(Actions.moveBy(0, -moveSpeed/delta));
			if(indoorLevel) {
				background.addAction(Actions.moveBy(0, -moveSpeed/delta));
			}
		}
	}
	
	public void initializeInputListener() {
		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.LEFT) {
					player.setMoveLeft(true);
				}
				if(keycode == Keys.RIGHT) {
					player.setMoveRight(true);
				}
				if(keycode == Keys.UP) {
					player.setMoveUp(true);
					stageMoveDown = true;
				}
				if(keycode == Keys.DOWN) {
					player.setMoveDown(true);
					stageMoveUp = true;
				}
				return true;
			}
			
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				if(keycode == Keys.LEFT) {
					player.setMoveLeft(false);
				}
				if(keycode == Keys.RIGHT) {
					player.setMoveRight(false);
				}
				if(keycode == Keys.UP) {
					player.setMoveUp(false);
					stageMoveDown = false;
				}
				if(keycode == Keys.DOWN) {
					player.setMoveDown(false);
					stageMoveUp = false;
				}
				return true;
			}
		});
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		initializeInputListener();
		
		if(background != null) stage.addActor(background);
		if(ground != null) stage.addActor(ground);
		if(foreground != null) stage.addActor(foreground);
		if(player != null) stage.addActor(player);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		handleScenery(delta);
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
		if(foregroundTexture != null) foregroundTexture.dispose();
		if(groundTexture != null) groundTexture.dispose();
		if(backgroundTexture != null) backgroundTexture.dispose();
		if(playerTexture != null) playerTexture.dispose();
	}
}
