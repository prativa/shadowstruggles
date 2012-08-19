package com.shadowstruggles.tools;

import com.badlogic.gdx.utils.ObjectMap;
import com.shadowstruggles.battle.Card;

public class CardDAO {
	private ObjectMap<String, Card> cards;
	
	public CardDAO() {		
		this.cards= new ObjectMap<String, Card>();		
	}
	
	public Card getCard(String key) {
		return this.cards.get(key);
	}	
	
	public ObjectMap<String, Card> getCards() {
		return cards;
	}
}
