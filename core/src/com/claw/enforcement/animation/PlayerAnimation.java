package com.claw.enforcement.animation;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class PlayerAnimation extends EntityAnimation{
	
	private static final int STANDING_FRAME = 0;
	
	private Animation walkAnimation;
	private Animation blinkAnimation;
	private Animation standingBlinkAnimation;
	private Animation scratchAnimation;
	private Animation standingScratchAnimation;
	private float walkAnimationSpeed = 0.085f;
	private float blinkAnimationSpeed = 0.085f;
	private float scratchAnimationSpeed = 0.065f;
	
	private boolean blinking;
	private float notBlinkingInterval = 0f;
	
	private boolean noScratch = false;
	private float noScratchInterval = 0f;
	private float scratchStateTime = 0f;
	
	public PlayerAnimation(TextureAtlas playerAtlas) {
		super(playerAtlas);
		
		createWalkAnimation();
		createBlinkAnimation();
		createStandingBlinkAnimation();
		createScratchAnimation();
		createStandingScratchAnimation();
	}
	
	public void handleWalk() {
		if(!blinking) {
			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		}
	}
	
	//when player.isScratching is false, this will run to keep the player
	//from spamming the scratch attack. 
	public void handleScratchDelay(float deltaTime) {
		if(noScratch) {	
			noScratchInterval += deltaTime;
			
			if(noScratchInterval >= 0.25f) {
				noScratch = false;
				noScratchInterval = 0f;
			}
		}
	}
	
	//handles scratch animation.
	//will run animation one time and then return false so that
	//player.isScratching can be false and then handleScratch delay can run
	public boolean handleScratchAttack(float deltaTime, boolean isMoving) {
		Animation animation;
		boolean stillScratching = true;
		
		if(isMoving) {
			animation = scratchAnimation;
		} else {
			animation = standingScratchAnimation;
		}
		
		if(!noScratch) {
			blinking = false;
			notBlinkingInterval = 0f;
			
			scratchStateTime += deltaTime;
			currentFrame = animation.getKeyFrame(scratchStateTime, false);
			
			if(animation.isAnimationFinished(scratchStateTime)) {
				scratchStateTime = 0f;
				noScratch = true;
			}
		} else {
			stillScratching = false;
		}
		
		return stillScratching;
	}
	
	//passing in animation here to recycle blinking code for both moving and standing still
	public void handleBlinking(boolean isMoving) {
		Animation animation;
		if(isMoving) {
			animation = blinkAnimation;
		} else {
			animation = standingBlinkAnimation;
		}
		
		notBlinkingInterval += Gdx.graphics.getDeltaTime();
		
		//if not blinking
		//increase blinking interval before next blink
		//if its time for next blink, set blink to true, reset interval
		//keep walking if not blinking
		if(!blinking) {
			if(notBlinkingInterval > 2) {
				blinking = true;
				notBlinkingInterval = 0;
				stateTime = 0;
			}
		} 
		
		//if blinking
		//make sure walk animation is finished
		//set current frame to blink animation
		//if blink animation is finished. reset statetime. blinking to false
		if(blinking) {
			if(allAnimationsFinished()) {
				currentFrame = animation.getKeyFrame(stateTime, false);
			}
			
			currentFrame = animation.getKeyFrame(stateTime, false);
			if(animation.isAnimationFinished(stateTime)) {
				stateTime = 0;
				blinking = false;
			}
		}
	}
	
	public boolean allAnimationsFinished() {
		if(walkAnimation.isAnimationFinished(stateTime)
				&& scratchAnimation.isAnimationFinished(stateTime)
				&& blinkAnimation.isAnimationFinished(stateTime)
				&& standingBlinkAnimation.isAnimationFinished(stateTime)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void handleIdle() {
		if(!blinking) {
			currentFrame = walkAnimation.getKeyFrames()[STANDING_FRAME];
		}
	}
	
	public void createWalkAnimation() {
		List<String> walkRegionNames = new ArrayList<String>();
		walkRegionNames.add("movingCat1");
		walkRegionNames.add("movingCat2");
		walkRegionNames.add("movingCat3");
		walkAnimation = createAnimation(walkRegionNames, walkAnimationSpeed);
	}
	
	public void createBlinkAnimation() {
		//using movingCat1 because its eyes are completely open and we're only going to be using the face from these
		List<String> blinkRegionNames = new ArrayList<String>();
		blinkRegionNames.add("movingCat1");
		blinkRegionNames.add("movingBlink1");
		blinkRegionNames.add("movingBlink2");
		blinkAnimation = createAnimation(blinkRegionNames, blinkAnimationSpeed);
	}
	
	public void createStandingBlinkAnimation() {
		//using movingCat1 because its eyes are completely open and we're only going to be using the face from these
		List<String> standingBlinkRegionNames = new ArrayList<String>();
		standingBlinkRegionNames.add("movingCat1");
		standingBlinkRegionNames.add("standingBlink1");
		standingBlinkRegionNames.add("standingBlink2");
		standingBlinkAnimation = createAnimation(standingBlinkRegionNames, blinkAnimationSpeed);
	}
	
	public void createScratchAnimation() {
		List<String> scratchRegionNames = new ArrayList<String>();
		scratchRegionNames.add("movingCat1");
		scratchRegionNames.add("movingScratch1");
		scratchRegionNames.add("movingScratch2");
		scratchRegionNames.add("movingScratch3");
		scratchAnimation = createAnimation(scratchRegionNames, scratchAnimationSpeed);
	}
	
	public void createStandingScratchAnimation() {
		List<String> standingScratchRegionNames = new ArrayList<String>();
		standingScratchRegionNames.add("movingCat1");
		standingScratchRegionNames.add("standingScratch1");
		standingScratchRegionNames.add("standingScratch2");
		standingScratchRegionNames.add("standingScratch3");
		standingScratchAnimation = createAnimation(standingScratchRegionNames, scratchAnimationSpeed);
	}
	
	public Animation createAnimation(List<String> regionNames, float animationSpeed) {
		TextureRegion[] frames = new TextureRegion[regionNames.size()];
		
		for(int i = 0; i < regionNames.size(); i++) {
			frames[i] = atlas.findRegion(regionNames.get(i));
		}
		
		return new Animation(animationSpeed, frames);
	}

	public boolean isNoScratch() {
		return noScratch;
	}

	public void setNoScratch(boolean noScratch) {
		this.noScratch = noScratch;
	}
}












//package com.claw.enforcement.animation;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
//
//public class PlayerAnimation extends EntityAnimation{
//	
//	private static final int STANDING_FRAME = 0;
//	
//	private Animation walkAnimation;
//	private Animation blinkAnimation;
//	private Animation standingBlinkAnimation;
//	private Animation scratchAnimation;
//	private Animation standingScratchAnimation;
//	private Animation scratchRightAnimation;
//	
//	private float walkAnimationSpeed = 0.085f;
//	private float blinkAnimationSpeed = 0.085f;
//	private float scratchAnimationSpeed = 0.065f;
//	private float notBlinkingInterval = 0f;
//	private float noScratchInterval = 0f;
//	
//	private boolean blinking;
//	private boolean noScratch = false;
//	
//	private float scratchStateTime = 0f;
//	
//	
//	public PlayerAnimation(TextureAtlas playerAtlas) {
//		super(playerAtlas);
//		
//		createWalkAnimation();
//		createBlinkAnimation();
//		createStandingBlinkAnimation();
//		createScratchAnimation();
//		createStandingScratchAnimation();
//	}
//	
//	public void handleWalk() {
//		if(!blinking) {
//			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
//		}
//	}
//	
//	//when player.isScratching is false, this will run to keep the player
//	//from spamming the scratch attack. 
//	public void handleScratchDelay(float deltaTime) {
//		if(noScratch) {	
//			noScratchInterval += deltaTime;
//			
//			if(noScratchInterval >= 0.30f) {
//				noScratch = false;
//				noScratchInterval = 0f;
//			}
//		}
//	}
//	
//	//handles scratch animation.
//	//will run animation one time and then return false so that
//	//player.isScratching can be false and then handleScratch delay can run
//	public boolean handleScratchAttack(float deltaTime, boolean isMoving) {
//		Animation animation;
//		boolean stillScratching = true;
//		
//		if(isMoving) {
//			animation = scratchAnimation;
//		} else {
//			animation = standingScratchAnimation;
//		}
//		
//		if(!noScratch) {
//			blinking = false;
//			notBlinkingInterval = 0f;
//			
//			scratchStateTime += deltaTime;
//			currentFrame = animation.getKeyFrame(scratchStateTime, false);
//			
//			if(animation.isAnimationFinished(scratchStateTime)) {
//				scratchStateTime = 0f;
//				noScratch = true;
//			}
//		} else {
//			stillScratching = false;
//		}
//		
//		return stillScratching;
//	}
//
//	//if user presses space bar twice in quick succession, the second scratch should happen
//	
//	public boolean handleScratchRightAttack(float deltaTime, boolean isMoving) {
//		boolean stillScratching = true;
//		
//		
//	}
//	
//	//passing in animation here to recycle blinking code for both moving and standing still
//	public void handleBlinking(boolean isMoving) {
//		Animation animation;
//		if(isMoving) {
//			animation = blinkAnimation;
//		} else {
//			animation = standingBlinkAnimation;
//		}
//		
//		notBlinkingInterval += Gdx.graphics.getDeltaTime();
//		
//		//if not blinking
//		//increase blinking interval before next blink
//		//if its time for next blink, set blink to true, reset interval
//		//keep walking if not blinking
//		if(!blinking) {
//			if(notBlinkingInterval > 2) {
//				blinking = true;
//				notBlinkingInterval = 0;
//				stateTime = 0;
//			}
//		} 
//		
//		//if blinking
//		//make sure walk animation is finished
//		//set current frame to blink animation
//		//if blink animation is finished. reset statetime. blinking to false
//		if(blinking) {
//			if(allAnimationsFinished()) {
//				currentFrame = animation.getKeyFrame(stateTime, false);
//			}
//			
//			currentFrame = animation.getKeyFrame(stateTime, false);
//			if(animation.isAnimationFinished(stateTime)) {
//				stateTime = 0;
//				blinking = false;
//			}
//		}
//	}
//	
//	public boolean allAnimationsFinished() {
//		if(walkAnimation.isAnimationFinished(stateTime)
//				&& scratchAnimation.isAnimationFinished(stateTime)
//				&& blinkAnimation.isAnimationFinished(stateTime)
//				&& standingBlinkAnimation.isAnimationFinished(stateTime)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	public void handleIdle() {
//		if(!blinking) {
//			currentFrame = walkAnimation.getKeyFrames()[STANDING_FRAME];
//		}
//	}
//	
//	public void createWalkAnimation() {
//		List<String> walkRegionNames = new ArrayList<String>();
//		walkRegionNames.add("movingCat1");
//		walkRegionNames.add("movingCat2");
//		walkRegionNames.add("movingCat3");
//		walkAnimation = createAnimation(walkRegionNames, walkAnimationSpeed);
//	}
//	
//	public void createBlinkAnimation() {
//		//using movingCat1 because its eyes are completely open and we're only going to be using the face from these
//		List<String> blinkRegionNames = new ArrayList<String>();
//		blinkRegionNames.add("movingCat1");
//		blinkRegionNames.add("movingBlink1");
//		blinkRegionNames.add("movingBlink2");
//		blinkAnimation = createAnimation(blinkRegionNames, blinkAnimationSpeed);
//	}
//	
//	public void createStandingBlinkAnimation() {
//		//using movingCat1 because its eyes are completely open and we're only going to be using the face from these
//		List<String> standingBlinkRegionNames = new ArrayList<String>();
//		standingBlinkRegionNames.add("movingCat1");
//		standingBlinkRegionNames.add("standingBlink1");
//		standingBlinkRegionNames.add("standingBlink2");
//		standingBlinkAnimation = createAnimation(standingBlinkRegionNames, blinkAnimationSpeed);
//	}
//	
//	public void createScratchAnimation() {
//		List<String> scratchRegionNames = new ArrayList<String>();
//		scratchRegionNames.add("movingCat1");
//		scratchRegionNames.add("movingScratch1");
//		scratchRegionNames.add("movingScratch2");
//		scratchRegionNames.add("movingScratch3");
//		scratchAnimation = createAnimation(scratchRegionNames, scratchAnimationSpeed);
//	}
//	
//	public void createStandingScratchAnimation() {
//		List<String> standingScratchRegionNames = new ArrayList<String>();
//		standingScratchRegionNames.add("movingCat1");
//		standingScratchRegionNames.add("standingScratch1");
//		standingScratchRegionNames.add("standingScratch2");
//		standingScratchRegionNames.add("standingScratch3");
//		standingScratchAnimation = createAnimation(standingScratchRegionNames, scratchAnimationSpeed);
//	}
//	
//	public Animation createAnimation(List<String> regionNames, float animationSpeed) {
//		TextureRegion[] frames = new TextureRegion[regionNames.size()];
//		
//		for(int i = 0; i < regionNames.size(); i++) {
//			frames[i] = atlas.findRegion(regionNames.get(i));
//		}
//		
//		return new Animation(animationSpeed, frames);
//	}
//
//	public boolean isNoScratch() {
//		return noScratch;
//	}
//
//	public void setNoScratch(boolean noScratch) {
//		this.noScratch = noScratch;
//	}
//}
