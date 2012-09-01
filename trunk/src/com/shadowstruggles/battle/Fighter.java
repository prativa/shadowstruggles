package com.shadowstruggles.battle;

/***
 * A representation of a fighter card on the field.
 */

public class Fighter extends Card {

	private int health;
	private int damage;
	private float speed = 0.5f;
	private int range;
	private int side;
	private boolean hasEffect;
	private boolean walking = false;
	private boolean attacking = false;
	private int cardID;
	private String name;
	private int nWalk = 2;
	private int nAttack = 0;

	/***
	 * Specifies the fighter's walking direction.
	 * 1 - The fighter is moving to the right.
	 * 2 - The fighter is moving to the left.
	 */

	private int direction;

	public Fighter(BattleLogic bl, int lane, int tile, int direction,
			String name) {
		super(bl, lane, tile, name);
		this.direction = direction;

	}
	
	public void action() {

		/*
		 * if(direction == 1){ if((int)((this.x - 288)/48)!=this.tile){
		 * this.tile++; } } else{ if((int)((this.x -
		 * (1920-288))/48)!=this.tile){ this.tile++; } } }
		 */

		this.x = this.x + (this.speed) * direction;
		if (count == 2) {
			walkIndex = (walkIndex + 1) % 10;
			count = 0;
		} else
			count++;
		this.setRegion(walk[walkIndex]);

	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public boolean isHasEffect() {
		return hasEffect;
	}

	public void setHasEffect(boolean hasEffect) {
		this.hasEffect = hasEffect;
	}

}
