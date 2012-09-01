package com.shadowstruggles.battle;

public class Effect extends Card{
	private float duration;
	private boolean isImmediateEffect;
	private boolean isActivated=false;
	
	
	
	public Effect(BattleLogic bl,int lane,int tile, int sentido, String name ) {		
		super(bl, lane, tile, name);				
	}

	public boolean isImmediateEffect() {
		return isImmediateEffect;
	}




	public void setImmediateEffect(boolean isImmediateEffect) {
		this.isImmediateEffect = isImmediateEffect;
	}




	public boolean isActivated() {
		return isActivated;
	}




	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}




	public float getDuration() {
		return duration;
	}




	public void setDuration(float duration) {
		this.duration = duration;
	}
}
