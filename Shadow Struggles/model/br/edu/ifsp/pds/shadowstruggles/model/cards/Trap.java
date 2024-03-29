package br.edu.ifsp.pds.shadowstruggles.model.cards;

import br.edu.ifsp.pds.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/***
 * A representation of an trap card on the field.
 */

public class Trap extends Card implements Serializable {

	private float duration;
	private boolean hasImmediateEffect;
	private boolean isActivated;

	public Trap() {
		this.isActivated = false;
	}

	public Trap(String name, String nameVisualization, int energyCost,
			String description, int buyCost, CardAction action, float duration,
			boolean hasImmediateEffect) {
		super(name, nameVisualization, energyCost, description, buyCost, action);
		this.duration = duration;
		this.hasImmediateEffect = hasImmediateEffect;
		this.isActivated = false;
		this.direction = 1;
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
		super.write(json);

		json.writeValue("duration", this.duration);
		json.writeValue("hasImmediateEffect", this.hasImmediateEffect);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		this.duration = json.readValue("duration", Float.class, jsonData);
		this.hasImmediateEffect = json.readValue("hasImmediateEffect",
				Boolean.class, jsonData);
	}

}
