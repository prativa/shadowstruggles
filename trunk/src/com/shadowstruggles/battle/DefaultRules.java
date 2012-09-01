package com.shadowstruggles.battle;

public class DefaultRules {

	//private Time timer = 15:00
	private int playerHP;
	private int enemyHP;
	private int playerHPmax=50;
	private int enemyHPmax=50;
	private int playerRemainingCards = 30;
	private int enemyRemainingCards = 30;
	private int playerEnergy=45;
	private int enemyEnergy=45;
	
	public DefaultRules(){
	}

	public DefaultRules(int playerHP, int enemyHP, int playerRemainingCards,
			int enemyRemainingCards, int playerEnergy, int enemyEnergy) {
		super();
		this.playerHP = playerHPmax;
		this.enemyHP = enemyHPmax;
		this.playerRemainingCards = playerRemainingCards;
		this.enemyRemainingCards = enemyRemainingCards;
		this.playerEnergy = playerEnergy;
		this.enemyEnergy = enemyEnergy;
	}

	public int getPlayerHP() {
		return playerHP;
	}
	
	public int getPlayerHpPercent(){
		return playerHP*10/playerHPmax*10;
	}

	public void setPlayerHP(int playerHP) {
		this.playerHP = playerHP;
	}

	public int getEnemyHP() {
		return enemyHP;
	}

	public void setEnemyHP(int enemyHP) {
		this.enemyHP = enemyHP;
	}

	public int getPlayerRemainingCards() {
		return playerRemainingCards;
	}

	public void setPlayerRemainingCards(int playerRemainingCards) {
		this.playerRemainingCards = playerRemainingCards;
	}

	public int getEnemyRemainingCards() {
		return enemyRemainingCards;
	}

	public void setEnemyRemainingCards(int enemyRemainingCards) {
		this.enemyRemainingCards = enemyRemainingCards;
	}

	public int getPlayerEnergy() {
		return playerEnergy;
	}

	public void setPlayerEnergy(int playerEnergy) {
		this.playerEnergy = playerEnergy;
	}

	public int getEnemyEnergy() {
		return enemyEnergy;
	}

	public void setEnemyEnergy(int enemyEnergy) {
		this.enemyEnergy = enemyEnergy;
	}
	
	
	
	
	
}
