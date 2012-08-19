package com.shadowstruggles.battle;

import com.badlogic.gdx.utils.Array;

public class BattlePlatform {
	
	private Enemy enemy;
	private Deck playerDeck;
	private Deck enemyDeck;
	private Map map;
	private DefaultRules rules;
	private Array<Card> playerDestroyedCards;
	private Array<Card> enemyDestroyedCards;
	
	public BattlePlatform(Enemy enemy, Deck playerDeck, Deck enemyDeck,
			Map map, DefaultRules rules) {
		super();
		this.enemy = enemy;
		this.playerDeck = playerDeck;
		this.enemyDeck = enemyDeck;
		this.map = map;
		this.rules = rules;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public Deck getPlayerDeck() {
		return playerDeck;
	}

	public void setPlayerDeck(Deck playerDeck) {
		this.playerDeck = playerDeck;
	}

	public Deck getEnemyDeck() {
		return enemyDeck;
	}

	public void setEnemyDeck(Deck enemyDeck) {
		this.enemyDeck = enemyDeck;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public DefaultRules getRules() {
		return this.rules;
	}

	public void setRules(DefaultRules rules) {
		this.rules = rules;
	}

	public Array<Card> getPlayerDestroyedCards() {
		return playerDestroyedCards;
	}

	public void setPlayerDestroyedCards(Array<Card> playerDestroyedCards) {
		this.playerDestroyedCards = playerDestroyedCards;
	}

	public Array<Card> getEnemyDestroyedCards() {
		return enemyDestroyedCards;
	}

	public void setEnemyDestroyedCards(Array<Card> enemyDestroyedCards) {
		this.enemyDestroyedCards = enemyDestroyedCards;
	}
	
	

}
