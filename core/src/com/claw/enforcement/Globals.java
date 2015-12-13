package com.claw.enforcement;

public class Globals {
	private static Globals instance = null;
	private int screenWidth = 800;
	private int screenHeight = 480;
	private boolean fullScreenEnabled = false;
	private boolean vSyncEnabled = true;
	
	protected Globals() {
		//exists only to defeat instantiation
	}
	
	public static Globals getInstance() {
		if(instance == null) {
			instance = new Globals();
		}
		
		return instance;
	}
	
	public int getScreenWidth() {
		return this.screenWidth;
	}
	
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	public int getScreenHeight() {
		return this.screenHeight;
	}
	
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	public boolean getFullScreenEnabled() {
		return this.fullScreenEnabled;
	}
	
	public void setFullScreenEnabled(boolean fullScreenEnabled) {
		this.fullScreenEnabled = fullScreenEnabled;
	}
	
	public boolean getVSyncEnabled() {
		return this.vSyncEnabled;
	}
	
	public void setVSyncEnabled(boolean vSyncEnabled) {
		this.vSyncEnabled = vSyncEnabled;
	}
}
