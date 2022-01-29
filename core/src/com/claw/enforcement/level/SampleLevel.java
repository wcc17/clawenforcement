package com.claw.enforcement.level;

import java.util.ArrayList;
import java.util.List;

import com.claw.enforcement.screen.LevelScreen;
import com.claw.enforcement.screen.SampleLevelScreen;

public class SampleLevel extends Level {
	
	public SampleLevel() {
		intializeLevelScreens();
	}

	@Override
	public void intializeLevelScreens() {
		List<LevelScreen> levelScreens = new ArrayList<LevelScreen>();
		
		//levels will have multiple screens later on and a certain way to switch between them
		levelScreens.add(new SampleLevelScreen());
		
		currentLevelScreen = levelScreens.get(0);
	}

}
