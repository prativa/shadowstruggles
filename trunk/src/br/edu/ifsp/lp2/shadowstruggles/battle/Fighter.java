package br.edu.ifsp.lp2.shadowstruggles.battle;


import br.edu.ifsp.lp2.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;

/***
 * A representation of a fighter card on the field.
 */

public class Fighter extends Card {

	public int health;
	public int damage;
	public float speed = 0.5f;
	public int range;
	public boolean hasEffect;
	public float attackDelay = 0.5f; 


	/***
	 * Specifies the fighter's walking direction. 1 - The fighter is moving to
	 * the right. -1 - The fighter is moving to the left.
	 */

	private int direction;

	public Fighter() {
		super();
		
		this.direction = 1;
	}

	public Fighter(BattlePlatform bl, int tile, int lane, int direction,
			String name, CardAction action,Image img) {
		super(bl, tile, lane, name, action,img);
		this.direction = direction;
	}
	
	public Fighter(String name, int energyCost, String description, int buyCost, CardAction action,
			int health, int damage, float speed, int range, boolean hasEffect) {
		super(name, energyCost, description, buyCost, action);
		
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
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		super.read(json, jsonData);
		
		this.health = json.readValue("health", Integer.class, jsonData);
		this.damage = json.readValue("damage", Integer.class, jsonData);
		this.speed = json.readValue("speed", Float.class, jsonData);
		this.range = json.readValue("range", Integer.class, jsonData);
		this.hasEffect = json.readValue("hasEffect", Boolean.class, jsonData);
	}

	public float getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(float attackDelay) {
		this.attackDelay = attackDelay;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	
	
}
