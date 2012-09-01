package com.shadowstruggles;


import com.badlogic.gdx.utils.Array;
import com.shadowstruggles.battle.Card;
import com.shadowstruggles.battle.Deck;

public class Profile {
	
	private String name;
	private Array<Deck> decks = new Array<Deck>();
	private Array<Card> cards = new Array<Card>();
	private int currentDeck;
	private int money;
	private int progress;
	private int baseHPmax;
	private int energyMax;
	
	
	
	public Profile(String name, Array<Deck> decks, Array<Card> cards,
			int currentDeck, int money, int progress) {
		super();
		this.name = name;
		this.decks = decks;
		this.cards = cards;
		this.currentDeck = currentDeck;
		this.money = money;
		this.progress = progress;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Array<Deck> getDecks() {
		return decks;
	}



	public void setDecks(Array<Deck> decks) {
		this.decks = decks;
	}



	public Array<Card> getCards() {
		return cards;
	}



	public void setCards(Array<Card> cards) {
		this.cards = cards;
	}



	public int getCurrentDeck() {
		return currentDeck;
	}



	public void setCurrentDeck(int currentDeck) {
		this.currentDeck = currentDeck;
	}



	public int getMoney() {
		return money;
	}



	public void setMoney(int money) {
		this.money = money;
	}



	public int getProgress() {
		return progress;
	}



	public void setProgress(int progress) {
		this.progress = progress;
	}

	

}
