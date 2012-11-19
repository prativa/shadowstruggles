package br.edu.ifsp.lp2.shadowstruggles.model;

import br.edu.ifsp.lp2.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;

/***
 * A representation of a fighter card on the field.
 */

public class Fighter extends Card {

	public static final String SIZE_SMALL = "SMALL";
	public static final String SIZE_MEDIUM = "MEDIUM";
	public static final String SIZE_LARGE = "LARGE";
	public static final float ATK_DELAY_SLOW=2F;
	public static final float ATK_DELAY_NORMAL=1.5F;
	public static final float ATK_DELAY_FAST=1F;
	public static final float MOV_SPEED_SLOW=0.3F;
	public static final float MOV_SPEED_NORMAL=0.5F;
	public static final float MOV_SPEED_FAST=0.8F;

	public int health;
	public int maxHealth;

	public int damage;
	public float speed;// = 0.5f;
	public int range;
	public boolean hasEffect;
	public float attackDelay;// = 0.0f;
	private String size;
	private float delay = 0.0f;

	
	



	public Fighter() {
		super();
		
		this.direction = 1;
	}

	public Fighter(BattlePlatform bl, int tile, int lane, int direction,
			String name, CardAction action, Image img, String size) {
		super(bl, tile, lane, name, action, img);
		
		this.direction = direction;
		this.setSize(size);
		this.direction = 1;
	}

	public Fighter(String name, int energyCost, String description,
			int buyCost, CardAction action, int health, int damage,
			float speed, int range, boolean hasEffect, String size,float attackDelay,Array<String> preRequisite) {
		super(name, energyCost, description, buyCost, action);
		
		this.health = health;
		this.damage = damage;
		this.speed = speed;
		this.range = range;
		this.preRequisites = preRequisite;
		this.hasEffect = hasEffect;
		this.setSize(size);
		this.attackDelay = attackDelay;
		this.direction = 1;
		this.maxHealth=health;
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

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("health", this.health);
		json.writeValue("damage", this.damage);
		json.writeValue("speed", this.speed);
		json.writeValue("range", this.range);
		json.writeValue("hasEffect", this.hasEffect);
		json.writeValue("size", this.size);

		//json.writeValue("attackDelay", this.attackDelay);
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		super.read(json, jsonData);

		this.health = json.readValue("health", Integer.class, jsonData);
		this.damage = json.readValue("damage", Integer.class, jsonData);
		setSpeed(json.readValue("speed", String.class, jsonData));
		this.range = json.readValue("range", Integer.class, jsonData);
		this.hasEffect = json.readValue("hasEffect", Boolean.class, jsonData);
		this.size = json.readValue("size", String.class, jsonData);
		setAttackDelay(json.readValue("attackDelay", String.class, jsonData));
		this.delay = attackDelay;
		
	}

	public float getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(String attackDelay) {
		if(attackDelay.equals("FAST"))
		this.attackDelay = ATK_DELAY_FAST;
		if(attackDelay.equals("NORMAL"))
			this.attackDelay = ATK_DELAY_NORMAL;
		if(attackDelay.equals("SLOW"))
			this.attackDelay = ATK_DELAY_SLOW;
	}

	public void setSpeed(String speed) {
		if(speed.equals("FAST"))
			this.speed=MOV_SPEED_FAST;
		if(speed.equals("NORMAL"))
			this.speed=MOV_SPEED_NORMAL;
		if(speed.equals("SLOW"))
			this.speed=MOV_SPEED_SLOW;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public float getDelay() {
		return delay;
	}

	public void setDelay(float delay) {
		this.delay = delay;
	}

	public Array<String> getPreRequisites() {
		return preRequisites;
	}
	
}
