package com.shadowstruggles.battle;

public class Trap extends Card{

	private int duration;
	private boolean hasImediateEffect;
	private boolean isActivated = false;
	
	
	
	public Trap(BattleLogic bl, int lane, int tile, boolean sentido, String name) {
		super(bl,lane,tile, name );
	}



	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isHasImediateEffect() {
		return hasImediateEffect;
	}

	public void setHasImediateEffect(boolean hasImediateEffect) {
		this.hasImediateEffect = hasImediateEffect;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	
	
	
}
