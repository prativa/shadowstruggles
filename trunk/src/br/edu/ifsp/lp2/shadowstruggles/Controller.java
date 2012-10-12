package br.edu.ifsp.lp2.shadowstruggles;

import br.edu.ifsp.lp2.shadowstruggles.battle.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.battle.Card;
import br.edu.ifsp.lp2.shadowstruggles.battle.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.object2d.*;
import br.edu.ifsp.lp2.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

/***
 * Acts as a link between the visual elements and the logic ones. The visual
 * layer is a listener for the Controller, calling one of its methods when a
 * triggering event (such as input from the player) happens.
 */
public class Controller {
	private BaseScreen currentScreen;
	private BattlePlatform platform;

	public void mapClicked(float x, float y) {

		if (platform.getSelectedCard() != null) {
			// settings.screenHeight - settings.backgroundHeight
			// + settings.tileHeight +
			// (this.fighter.getLane())*settings.tileHeight*3/2
			Card handCard = platform.getSelectedCard();
			if (handCard.getClass().equals(Fighter.class)) {
				handCard.setLane((int) ((y - 48) / 72));
				handCard.setTile((int) ((x - 96) / 48));
				Fighter2D f2d = new Fighter2D((Fighter) handCard, this);

				f2d.create();
				platform.getMap().addCard(handCard, (int) ((x - 96) / 48),
						(int) ((y - 48) / 72));
				currentScreen.addGameObject(f2d);
				platform.getPlayerHandCards().removeValue(
						platform.getSelectedCard(), true);
				platform.setSelectedCard(null);
				currentScreen.getStage().removeActor(handCard.getImage());
				for (Actor actor : currentScreen.getStage().getActors()) {
					if (actor.getClass().equals(HandCard.class)) {
						if (((HandCard) actor).getInitialX() > ((HandCard) handCard
								.getImage()).getInitialX()) {
							Sequence sequence = Sequence.$(MoveTo.$(
									actor.x - 130, actor.y, 0.25f));
							Delay delayAction = Delay.$(sequence, 0.1f);
							sequence = Sequence.$(sequence, delayAction);
							actor.action(Sequence.$(sequence));
							((HandCard) actor).setInitialX(((HandCard) actor)
									.getInitialX() - 130);
						}
					}
				}
			}
		}
	}

	public void setPlatform(BattlePlatform platform) {
		this.platform = platform;
	}

	public void handCardClicked(Card handCard, boolean isSelected) {
		if (isSelected) {
			for (Card card : platform.getPlayerHandCards()) {
				((HandCard) (card.getImage())).reset();
				if (!card.equals(handCard))
					((HandCard) (card.getImage())).setSelected(false);
			}
			platform.setSelectedCard(handCard);
		} else {
			for (Card card : platform.getPlayerHandCards()) {
				((HandCard) (card.getImage())).reset();
			}
			platform.setSelectedCard(null);
		}
	}

	public void deckClicked() {
		BattleScreen battleScreen = (BattleScreen) currentScreen;
		if (battleScreen.timeDelay <= 0)
			if (platform.getPlayerDeck().getCards().size > 1
					&& platform.getPlayerHandCards().size < 5) {
				Card card = platform.getPlayerDeck().draw();
				((BattleScreen) currentScreen).insertHandCard(card);
				platform.addPlayerHandCard(card);
				battleScreen.timeDelay = (float) battleScreen.getSettings().drawInterval;
			}
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

	public void playerEnergyChanged(int amount) {
		int currentEnergy = this.platform.rules.getPlayerEnergy();
		int newEnergy = currentEnergy + amount;

		/*
		 * If new energy is in normal range, apply changes without correction.
		 * If new energy is less than zero, set new energy to zero. If new
		 * energy is greater than maximum energy, set new energy to maximum
		 * energy.
		 */

		if (newEnergy >= 0
				&& newEnergy <= this.platform.rules.getPlayerEnergyMax()) {
			this.platform.rules.setPlayerEnergy(newEnergy);
		} else if (newEnergy < 0) {
			this.platform.rules.setPlayerEnergy(0);
		} else if (newEnergy > this.platform.rules.getPlayerEnergyMax()) {
			this.platform.rules.setPlayerEnergy(this.platform.rules
					.getPlayerEnergyMax());
		}

		((BattleScreen) this.currentScreen).energyBar.percentage = this.platform.rules
				.getPlayerEnergyPercent();

	}

	public void tileChanged(Fighter card) {
		this.platform.getMap().getTiles().get(card.getTile())
				.get(card.getLane()).removeValue(card, true);
		this.platform.getMap().addCard(card,
				card.getTile() + card.getDirection(), (card.getLane()));
	}

	public BaseScreen getCurrentScreen() {
		return currentScreen;
	}

	public BattlePlatform getPlatform() {
		return platform;
	}

}
