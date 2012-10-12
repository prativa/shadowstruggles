package br.edu.ifsp.lp2.shadowstruggles.battle;

import br.edu.ifsp.lp2.shadowstruggles.data.Settings;

/***
 * Basic set of rules for any match. Matches with special conditions
 * may extend this class.
 */
public class DefaultRules {

	private Settings settings;
	
	// private Time timer = 15:00
	private int playerHP;
	private int enemyHP;
	private int playerHPmax;
	private int enemyHPmax;
	private int playerRemainingCards;
	private int enemyRemainingCards;
	private int playerEnergy;
	private int enemyEnergy;
	private int playerEnergyMax;
	private int enemyEnergyMax;
	
	public DefaultRules(Settings settings) {
		this.settings = settings;
		
		this.playerHPmax = settings.playerMaxHP;
		this.enemyHPmax = settings.enemyMaxHP;
		this.playerRemainingCards = settings.deckSize;
		this.enemyRemainingCards = settings.deckSize;
		this.playerEnergy = settings.playerInitialEnergy;
		this.enemyEnergy = settings.enemyInitialEnergy;
		this.playerEnergyMax = settings.playerMaxEnergy;
		this.enemyEnergyMax = settings.enemyMaxEnergy;
		
		this.playerHP = playerHPmax;
		this.enemyHP = enemyHPmax;
	}

	public DefaultRules(int playerHP, int enemyHP, int playerRemainingCards,
			int enemyRemainingCards, int playerEnergy, int enemyEnergy, Settings settings) {
		
		this.playerHPmax = playerHP;
		this.enemyHPmax = enemyHP;
		this.playerHP = playerHP;
		this.enemyHP = enemyHP;
		this.playerRemainingCards = playerRemainingCards;
		this.enemyRemainingCards = enemyRemainingCards;
		this.playerEnergy = playerEnergy;
		this.enemyEnergy = enemyEnergy;
		
		this.settings = settings;
		
		this.playerEnergyMax = settings.playerMaxEnergy;
		this.enemyEnergyMax = settings.enemyMaxEnergy;
	}

	public int getPlayerHP() {
		return playerHP;
	}

	public int getPlayerHPmax() {
		return this.playerHPmax;
	}

	public float getPlayerHpPercent() {
		return (float)playerHP/ (float)playerHPmax;
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

	public float getEnemyHpPercent() {
		return (float)enemyHP / (float)enemyHPmax ;
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
	public int getPlayerEnergyMax() {
		return playerEnergyMax;
	}
	
	public int getEnemyEnergyMax() {
		return enemyEnergyMax;
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
	
	public float getPlayerEnergyPercent() {
		return (float)playerEnergy/ (float)playerEnergyMax;
	}
	public float getEnemyEnergyPercent() {
		return (float)enemyEnergy/ (float)enemyEnergyMax;
	}

}
