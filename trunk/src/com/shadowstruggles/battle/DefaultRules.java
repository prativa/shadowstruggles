package com.shadowstruggles.battle;

public class DefaultRules {

	//private Time timer = 15:00
	private int playerHP;
	private int enemyHP;
	private int playerHPmax=100;
	private int enemyHPmax=100;
	private int playerRemainingCards = 30;
	private int enemyRemainingCards = 30;
	private int playerEnergy=45;
	private int enemyEnergy=45;
	
	public DefaultRules(){
		this.playerHP = playerHPmax;
		this.enemyHP = enemyHPmax;
	}

	public DefaultRules(int playerHP, int enemyHP, int playerRemainingCards,
			int enemyRemainingCards, int playerEnergy, int enemyEnergy) {
		super();
		this.playerHPmax = playerHP;
		this.enemyHPmax = enemyHP;
		this.playerHP = playerHP;
		this.enemyHP = enemyHP;
		this.playerRemainingCards = playerRemainingCards;
		this.enemyRemainingCards = enemyRemainingCards;
		this.playerEnergy = playerEnergy;
		this.enemyEnergy = enemyEnergy;
	}

	public int getPlayerHP() {
		return playerHP;
	}
	
	public int getPlayerHPmax() {
		return this.playerHPmax;
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
	
	public int getEnemyHPmax() {
		return this.enemyHPmax;
	}
	
	public int getEnemyHpPercent(){
		return enemyHP*10/enemyHPmax*10;
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
