package br.edu.ifsp.lp2.shadowstruggles.model;


import br.edu.ifsp.lp2.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class Trap extends Card implements Serializable{

	private float duration;
	private boolean hasImmediateEffect;
	private boolean isActivated = false;

	public Trap(){}
	public Trap(BattlePlatform platform, int lane, int tile, boolean sentido,
			String name, CardAction action, Image img) {
		super(platform, lane, tile, name, action, img);
	}

	public Trap(String name, int energyCost, String description,
			int buyCost, CardAction action, float duration, boolean hasImmediateEffect) {
		super(name, energyCost, description, buyCost, action);
		this.duration = duration;
		this.hasImmediateEffect = hasImmediateEffect;
		this.direction =1;
	}
	public float getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isHasImmediateEffect() {
		return hasImmediateEffect;
	}

	public void setHasImmediateEffect(boolean hasImmediateEffect) {
		this.hasImmediateEffect = hasImmediateEffect;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@Override
	public void write(Json json) {
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		super.read(json, jsonData);
		this.duration = json.readValue("duration", Float.class, jsonData);
		this.hasImmediateEffect = json.readValue("hasImmediateEffect", Boolean.class, jsonData);
		this.isActivated = json.readValue("isActivated", Boolean.class, jsonData);
	}

}
