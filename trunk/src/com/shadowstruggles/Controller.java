package com.shadowstruggles;

import com.badlogic.gdx.Gdx;
import com.shadowstruggles.battle.BattleLogic;
import com.shadowstruggles.battle.BattlePlatform;
import com.shadowstruggles.battle.Fighter;
import com.shadowstruggles.battle.Map;
import com.shadowstruggles.object2d.HandCard;
import com.shadowstruggles.screens.BaseScreen;
import com.shadowstruggles.screens.BattleScreen;

/***
 * Acts as a link between the visual elements and the logic ones. The visual
 * layer is a listener for the Controller, calling one of its methods when a
 * triggering event (such as input from the player) happens.
 */
public class Controller {
	private BaseScreen currentScreen;
	private Map map;
	private BattlePlatform platform;
	private BattleLogic logic;

	public Controller() {
		
	}

	public void mapClicked(float x, float y) {

		if (platform.getSelectedCard() != null) {
			HandCard hc = platform.getSelectedCard();
			switch (hc.getType()) {
			case 1:
				map.addCard(
						new Fighter(logic, 1, 1, hc.getSide(), hc.getName()),
						1, 1);
			}
		}
	}

	public void setLogic(BattleLogic logic) {
		this.logic = logic;
		this.platform = logic.getPlatform();
	}

	public void handCardClicked(HandCard handCard, boolean isSelected) {
		if (isSelected)
			platform.setSelectedCard(handCard);
	}

	public void initMap() {
		this.map = new Map();
	}

	public void deckClicked() {

	}

	public void setCurrentscreen(BaseScreen currentScreen) {
		this.currentScreen = currentScreen;
	}

	/***
	 * Updates the player life to the Battle Platform and the Life Bar.
	 * 
	 * @param amount
	 *            If negative, it means the player has taken the amount of
	 *            damage. If positive, it means the player was healed by the
	 *            specified amount.
	 */

	public void playerLifeChanged(int amount) {
		int currentLife = this.platform.rules.getPlayerHP();
		int newLife = currentLife + amount;

		/*
		 * If new life is in normal range, apply changes without correction. If
		 * new life is less than zero, set new life to zero. If new life is
		 * greater than maximum life, set new life to maximum life.
		 */

		if (newLife >= 0 && newLife <= this.platform.rules.getPlayerHPmax()) {
			this.platform.rules.setPlayerHP(newLife);
		} else if (newLife < 0) {
			this.platform.rules.setPlayerHP(0);
		} else if (newLife > this.platform.rules.getPlayerHPmax()) {
			this.platform.rules.setPlayerHP(this.platform.rules
					.getPlayerHPmax());
		}
		
		((BattleScreen) this.currentScreen).playerLife.percentage = this.platform.rules
				.getPlayerHpPercent();
	}

	/***
	 * Updates the enemy life to the Battle Platform and the Life Bar.
	 * 
	 * @param amount
	 *            If negative, it means the enemy has taken the amount of
	 *            damage. If positive, it means the enemy was healed by the
	 *            specified amount.
	 */

	public void enemyLifeChanged(int amount) {
		int currentLife = this.platform.rules.getEnemyHP();
		int newLife = currentLife + amount;

		if (newLife >= 0 && newLife <= 100) {
			((BattleScreen) this.currentScreen).enemyLife.percentage = newLife
					/ currentLife;
			this.platform.rules.setEnemyHP(newLife);
		} else if (newLife < 0) {
			((BattleScreen) this.currentScreen).enemyLife.percentage = 0;
		} else if (newLife > 100) {
			((BattleScreen) this.currentScreen).enemyLife.percentage = 1.0f;
		}
	}
}
