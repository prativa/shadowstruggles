package com.shadowstruggles.battle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Card{
	
	private int type; //1=character	2=effect	3=trap
	private int energyCost;
	private String description;
	private int buyCost;
	private int sellCost;
	private String name;
	private Sprite sprite;
	private Image cardIlustration;//Realmente necessário?
	
	public Card() {
		this.type = 0;
		this.energyCost = 0;
		this.description = "";
		this.buyCost = 0;
		this.sellCost = 0;
		this.name = "";
		this.sprite = null;
		this.cardIlustration = null;
	}
	
	


	public Card(int type, int energyCost, String description, int buyCost,
			int sellCost, String name, Sprite sprite, Image cardIlustration) {
		this.type = type;
		this.energyCost = energyCost;
		this.description = description;
		this.buyCost = buyCost;
		this.sellCost = sellCost;
		this.name = name;
		this.sprite = sprite;
		this.cardIlustration = cardIlustration;
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

	public int getSellCost() {
		return sellCost;
	}

	public void setSellCost(int sellCost) {
		this.sellCost = sellCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Image getCardIlustration() {
		return cardIlustration;
	}

	public void setCardIlustration(Image cardIlustration) {
		this.cardIlustration = cardIlustration;
	}
	
	
}
