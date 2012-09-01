package com.shadowstruggles.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/***
 * Template for the behavior of the cards on the field. This class only concerns
 * itself with the logic aspects of the card, not data.
 */

public class Card extends Image {

	static final float SCALE_X = 0.3f;
	static final float SCALE_Y = 0.3f;

	private int type; // 1=character 2=effect 3=trap
	private int energyCost;
	private String description;
	private int buyCost;
	private String name;
	protected TextureRegion[] walk = new TextureRegion[10];
	private TextureRegion[] attack = new TextureRegion[8];
	protected int walkIndex = 1;
	protected int attackIndex = 0;
	private int range;
	private boolean isMoving = true;
	private BattleLogic bl;
	protected int lane;
	protected int tile;
	protected int count = 0;
	protected int x1;

	public Card(BattleLogic bl, int lane, int Tile, String name) {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/sprites/" + name
						+ "/Walk/0001.png")), 0, 0, 64, 64));
		this.scaleX = SCALE_X;
		this.scaleY = SCALE_Y;
		this.y = 20;
		for (int i = 0; i < walk.length; i++) {
			walk[i] = new TextureRegion(new Texture(
					Gdx.files.internal("data/images/sprites/" + name
							+ "/Walk/000" + String.valueOf(i + 1) + ".png")),
					0, 0, 64, 64);
		}
		
		this.name = name;
		this.bl = bl;
		this.lane = lane;
		this.tile = Tile;
		this.x = 288 + (Tile - 1) * 48;

	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBuyCost() {
		return buyCost;
	}

	public void setBuyCost(int buyCost) {
		this.buyCost = buyCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
