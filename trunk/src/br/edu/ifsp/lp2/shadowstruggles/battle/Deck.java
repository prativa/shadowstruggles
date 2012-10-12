package br.edu.ifsp.lp2.shadowstruggles.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

public class Deck extends Stack implements Serializable{
	private int minCapacity;
	private int maxCapacity;
	private Array<Card> cards;
	private Image deckImage;
	private String name;
	
	
	
	public Deck() {
		this.cards = new Array<Card>();
	}
	public Card draw(){		
		return cards.removeIndex(getCurrentQuantity()-1);
	}
	
	public void shuffle(){
		cards.shuffle();
	}
	
	public void addCard(Card card){
		cards.add(card);
		
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
	public String getName() {
		return name;
	}
public int getCurrentQuantity() {
	return cards.size;
}
	@Override
	public void write(Json json) {
		json.writeValue("name", this.name);
		json.writeValue("minCapacity", this.minCapacity);
		json.writeValue("maxCapacity", this.maxCapacity);
		json.writeValue("cards", this.cards);
		
				
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.name = json.readValue("name", String.class, jsonData);
		this.maxCapacity = json.readValue("maxCapacity", Integer.class, jsonData);
		this.minCapacity = json.readValue("minCapacity", Integer.class, jsonData);
		this.cards = json.readValue("cards", Array.class, jsonData);
	}
	
	
}


