package br.edu.ifsp.lp2.shadowstruggles.battle;


import br.edu.ifsp.lp2.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;

public class Trap extends Card {

	private int duration;
	private boolean hasImediateEffect;
	private boolean isActivated = false;

	public Trap(BattlePlatform platform, int lane, int tile, boolean sentido,
			String name, CardAction action, Image img) {
		super(platform, lane, tile, name, action, img);
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

	@Override
	public void write(Json json) {
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
	}

}
