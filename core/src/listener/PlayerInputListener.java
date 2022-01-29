package listener;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.claw.enforcement.level.Level;
import com.claw.enforcement.screen.LevelScreen;

public class PlayerInputListener extends InputListener {
	
	LevelScreen currentLevelScreen;
	
	long lastPressSpace;
	
	public PlayerInputListener() {
		super();
		
		lastPressSpace = TimeUtils.nanoTime();
		
		currentLevelScreen = Level.currentLevelScreen;
	}
	
	//1000000000 /* 1,000,000,000ns == one second */
	//1000000	 /* 1,000,000ns == one millisecond */
	public long timeSinceLastPress(int keycode) {
		long timeSinceLastPress = 0;
		
		switch(keycode) {
			case(Keys.SPACE):
				timeSinceLastPress = TimeUtils.nanoTime() - lastPressSpace;
				lastPressSpace = TimeUtils.nanoTime();
			
				System.out.println(timeSinceLastPress / 1000000);
				break;
		}
		
		return timeSinceLastPress;
	}
	
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		switch(keycode) {
			case(Keys.LEFT):
				Level.player.setMoveLeft(true);
				currentLevelScreen.setCameraMoveLeft(true);
				break;
			case(Keys.RIGHT):
				Level.player.setMoveRight(true);
				currentLevelScreen.setCameraMoveRight(true);
				break;
			case(Keys.UP):
				Level.player.setMoveUp(true);
				currentLevelScreen.setStageMoveDown(true);
				break;
			case(Keys.DOWN):
				Level.player.setMoveDown(true);
				currentLevelScreen.setStageMoveUp(true);
				break;
			case(Keys.SPACE):
				if(!Level.player.isScratching() && !Level.player.noScratch()) {
					Level.player.setScratching(true);
				}
				break;
		}
		return true;
	}
	
	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		switch(keycode) {
			case(Keys.LEFT):
				Level.player.setMoveLeft(false);
				currentLevelScreen.setCameraMoveLeft(false);
				break;
			case(Keys.RIGHT):
				Level.player.setMoveRight(false);
				currentLevelScreen.setCameraMoveRight(false);
				break;
			case(Keys.UP):
				Level.player.setMoveUp(false);
				currentLevelScreen.setStageMoveDown(false);
				break;
			case(Keys.DOWN):
				Level.player.setMoveDown(false);
				currentLevelScreen.setStageMoveUp(false);
				break;
	}
		return true;
	}
}
