package com.claw.enforcement.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.claw.enforcement.ClawEnforcement;
import com.claw.enforcement.Globals;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = Globals.getInstance().getScreenWidth();
		config.height = Globals.getInstance().getScreenHeight();
		
		config.fullscreen = Globals.getInstance().getFullScreenEnabled();
		
		config.vSyncEnabled = Globals.getInstance().getVSyncEnabled();
		
		new LwjglApplication(new ClawEnforcement(), config);
	}
}
