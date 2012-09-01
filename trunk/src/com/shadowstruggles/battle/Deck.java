package com.shadowstruggles.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class Deck{
	private int minCapacity=30;
	private int maxCapacity=30;
	private Array<Card> cards;
	private Image deckImage;
	private int deckNumber;
	
	public Deck() {
		this.cards = null;
		this.deckImage = null;
	}
	

	public Deck(int minCapacity, int maxCapacity, Array<Card> cards, Image deckImage) {
		this.minCapacity = minCapacity;
		this.maxCapacity = maxCapacity;
		this.cards = cards;
		this.deckImage = deckImage;
	}

	public int getMinCapacity() {
		return minCapacity;
	}


	public void setMinCapacity(int minCapacity) {
		this.minCapacity = minCapacity;
	}


	public int getMaxCapacity() {
		return maxCapacity;
	}


	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}


	public Array<Card> getCards() {
		return cards;
	}


	public void setCards(Array<Card> cards) {
		this.cards = cards;
	}


	public Image getDeckImage() {
		return deckImage;
	}


	public void setDeckImage(Image deckImage) {
		this.deckImage = deckImage;
	}
	
	
}


