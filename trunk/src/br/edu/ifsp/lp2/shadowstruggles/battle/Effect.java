package br.edu.ifsp.lp2.shadowstruggles.battle;


import br.edu.ifsp.lp2.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;

public class Effect extends Card{
	private float duration;
	private boolean isImmediateEffect;
	private boolean isActivated=false;
	
	public Effect() {
		
	}
	
	public Effect(BattlePlatform platform,int lane,int tile, String name, CardAction action, Image img) {		
		super(platform, lane, tile, name, action,img);				
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
	
	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("duration", this.duration);
		json.writeValue("isImmediateEffect", this.isImmediateEffect);
		json.writeValue("isActivated", this.isActivated);
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		super.read(json, jsonData);
		
		this.duration = json.readValue("duration", Float.class, jsonData);
		this.isImmediateEffect = json.readValue("isImmediateEffect", Boolean.class, jsonData);
		this.isActivated = json.readValue("isActivated", Boolean.class, jsonData);
	}
}
