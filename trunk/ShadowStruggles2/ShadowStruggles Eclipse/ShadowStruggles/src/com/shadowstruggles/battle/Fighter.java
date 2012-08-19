package com.shadowstruggles.battle;

import com.badlogic.gdx.graphics.Texture;

public class Fighter extends Card{

	private int health;
	private int damage;
	private int speed;
	private int range;
	private int side;
	private boolean hasEffect;
	private boolean walking = false;
	private boolean attacking = false;
	private int cardID;
	private String name;
	private int nWalk = 2;
	private int nAttack = 0;
	private Texture[] walk;
	private Texture[] attack;
	
	public Fighter()
	{
		this.health =0;
		this.damage = 0;
		this.speed = 0;
		this.range = 0;
		this.hasEffect = false;
	}
	
	public Fighter(int health, int damage, int speed, int range,
			boolean hasEffect) {
		super();
		this.health = health;
		this.damage = damage;
		this.speed = speed;
		this.range = range;
		this.hasEffect = hasEffect;
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

	public int getSpeed() {
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
