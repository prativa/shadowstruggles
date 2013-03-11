package br.edu.ifsp.lp2.shadowstruggles;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Effect;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.model.Trap;
import br.edu.ifsp.lp2.shadowstruggles.object2d.BackCard;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.HandCard;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.lp2.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;
import br.edu.ifsp.lp2.shadowstruggles.screens.InGameMenu;

import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.actions.Delay;
//import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
//import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

/***
 * Acts as a link between the visual elements and the logic ones. The visual
 * layer is a listener for the Controller, calling one of its methods when a
 * triggering event (such as input from the player) happens.
 */
public class Controller {
	private BaseScreen currentScreen;
	private BattlePlatform platform;

	public void mapClicked(float x, float y) {
		int lane = (int) ((y - 48) / 72);
		int tile = ((int) ((x - 96) / 48) / 2) * 2;

		if (platform.getSelectedCard() != null) {

			Card handCard = platform.getSelectedCard();
			int playerEnergy = platform.getRules().getPlayerEnergy();

			// Verifica energia suficiente
			if (playerEnergy - handCard.getEnergyCost() >= 0
					&& handCard.readyToSummom(platform)) {

				// Verifica se foi clicado no pentagrama
				if (x >= 96 && tile <= 9 && y >= 48 && lane <= 3) {
					// Verifica se clicou em uma backCard
					if (platform.getPlayerField().getTiles().get(tile / 2)
							.get(lane) == null) {
						pentagramClicked(lane, tile);
					} else
						backCardClicked(lane / 2, tile);
				} else {
					returnHandCard();
				}
			} else {
				returnHandCard();
			}

		}
	}

	private void activateEffect(Card handCard, int lane, int tile) {
		handCard.setLane(lane);
		handCard.setTile(tile);
		((BattleScreen) currentScreen).getBackcards().get(
				(handCard.getTile() / 2) * 4 + handCard.getLane()).setVisible(true);
		((Effect) handCard).setMarkLane(lane);
		((Effect) handCard).setMarkTile(tile);
		platform.getPlayerField().getTiles().get(handCard.getTile() / 2)
				.set(handCard.getLane(), handCard);
		float initialX = 0;
		if ((handCard.getImage()).getClass().equals(HandCard.class))
			initialX = ((HandCard) handCard.getImage()).getInitialX();
		Effect2D f2d = new Effect2D((Effect) handCard, this.currentScreen.getGame());

		f2d.create();
		platform.getMap().addCard(handCard, tile, lane);
		currentScreen.addGameObject(f2d);
		platform.getPlayerHandCards().removeValue(platform.getSelectedCard(),
				true);
		platform.setSelectedCard(null);

		handCard.getImage().markToRemove(true);
		((MyStage) currentScreen.getStage()).getCurrentActor().removeValue(
				handCard.getImage(), true);
		handCard.setImage(f2d);
		f2d.getEffect().action(platform, f2d,0);
		rearrangeCards(initialX);
	}

	private void summonFighter(Card handCard, int lane, int tile) {
		handCard.setLane(lane);
		handCard.setTile(tile);
		((BattleScreen) currentScreen).getBackcards().get(
				(handCard.getTile() / 2) * 4 + handCard.getLane()).setVisible(true);
		((Fighter) handCard).setMarkLane(lane);
		((Fighter) handCard).setMarkTile(tile);
		platform.getPlayerField().getTiles().get(handCard.getTile() / 2)
				.set(handCard.getLane(), handCard);
		float initialX = 0;

		if ((handCard.getImage()).getClass().equals(HandCard.class))
			initialX = ((HandCard) handCard.getImage()).getInitialX();

		Fighter2D f2d = new Fighter2D((Fighter) handCard, currentScreen.getGame());

		f2d.create();
		platform.getMap().addCard(handCard, tile, lane);
		currentScreen.addGameObject(f2d);
		platform.getPlayerHandCards().removeValue(platform.getSelectedCard(),
				true);
		platform.setSelectedCard(null);
		// ((BattleScreen)currentScreen).getRemovedCards().add(handCard.getImage());
		handCard.getImage().markToRemove(true);
		((MyStage) currentScreen.getStage()).getCurrentActor().removeValue(
				handCard.getImage(), true);
		handCard.setImage(f2d);
		currentScreen.getGame().getAudio().playSound("button_7");

		rearrangeCards(initialX);
	}

	private void putTrap(Card handCard, int lane, int tile) {
		handCard.setLane(lane);
		handCard.setTile(tile);
		((BattleScreen) currentScreen).getBackcards().get(
				(handCard.getTile() / 2) * 4 + handCard.getLane()).setVisible(true);
		((Trap) handCard).setMarkLane(lane);
		((Trap) handCard).setMarkTile(tile);
		platform.getPlayerField().getTiles().get(handCard.getTile() / 2)
				.set(handCard.getLane(), handCard);
		float initialX = 0;

		if ((handCard.getImage()).getClass().equals(HandCard.class))
			initialX = ((HandCard) handCard.getImage()).getInitialX();

		Trap2D f2d = new Trap2D((Trap) handCard, currentScreen.getGame());

		f2d.create();
		platform.getMap().addCard(handCard, tile, lane);
		currentScreen.addGameObject(f2d);
		platform.getPlayerHandCards().removeValue(platform.getSelectedCard(),
				true);
		platform.setSelectedCard(null);
		// ((BattleScreen)currentScreen).getRemovedCards().add(handCard.getImage());
		handCard.getImage().markToRemove(true);
		((MyStage) currentScreen.getStage()).getCurrentActor().removeValue(
				handCard.getImage(), true);
		handCard.setImage(f2d);

		rearrangeCards(initialX);
	}

	public void backCardClicked(int lane, int tile) {
		Card handCard = platform.getSelectedCard();
		if (handCard.getClass().equals(Effect.class)
				&& ((Effect) handCard).isOnFighter()) {
			activateEffect(handCard, lane, tile);
			platform.setSelectedCard(null);
			((BattleScreen) currentScreen).changePentagram(false);
		} else
			returnHandCard();
	}

	@SuppressWarnings("unused")
	private int nextAvailableSlot(int lane) {
		int next = 4;
		for (BackCard backCard : ((BattleScreen) currentScreen).getBackcards()) {
			if (backCard.isVisible()
					&& ((int) ((backCard.getY() - 48) / 72)) - 2 == lane) {
				next--;
			}
		}
		return next;
	}

	private void returnHandCard() {
		for (Actor actor : currentScreen.getStage().getActors())
			if (actor.getClass().equals(HandCard.class))
				if (((HandCard) actor).getX() != ((HandCard) actor).getInitialX()
						|| ((HandCard) actor).getY() != currentScreen.getSettings().bottomElementY)
					((HandCard) actor).resetPosition();
	}

	private void rearrangeCards(float initialX) {
		for (Actor actor : currentScreen.getStage().getActors()) {
			if (actor.getClass().equals(HandCard.class)) {
				if (((HandCard) actor).getInitialX() > initialX) {
					Sequence sequence = Sequence.$(MoveTo.$(actor.x - 130,
							actor.y, 0.25f));
					Delay delayAction = Delay.$(sequence, 0.1f);
					sequence = Sequence.$(sequence, delayAction);
					actor.action(Sequence.$(sequence));
					((HandCard) actor).setInitialX(((HandCard) actor)
							.getInitialX() - 130);
				}
			}
		}
	}

	public void pentagramClicked(int lane, int tile) {
		BattleScreen battleScreen = (BattleScreen) currentScreen;
		Card handCard = platform.getSelectedCard();
		this.playerEnergyChanged(-handCard.getEnergyCost());
		battleScreen.changePentagram(false);
		if (handCard.getClass().equals(Fighter.class)) {
			summonFighter(handCard, lane, tile);

		} else if (handCard.getClass().equals(Effect.class)
				&& !((Effect) handCard).isOnFighter()) {
			activateEffect(handCard, lane, tile);
		} else if (handCard.getClass().equals(Trap.class)) {
			putTrap(handCard, lane, tile);
		}
		platform.setSelectedCard(null);
	}

	public void handCardClicked(Card handCard, boolean isSelected) {
		if (isSelected) {
			for (Card card : platform.getPlayerHandCards()) {
				if (card.getImage().getClass().equals(HandCard.class)) {
					((HandCard) (card.getImage())).resetBlink();
					if (!card.equals(handCard))
						((HandCard) (card.getImage())).setSelected(false);
				}
			}
			platform.setSelectedCard(handCard);
		} else {
			for (Card card : platform.getPlayerHandCards()) {
				if (card.getImage().getClass().equals(HandCard.class)) {
					((HandCard) (card.getImage())).resetBlink();
				}
			}
			platform.setSelectedCard(null);
		}
		((BattleScreen) currentScreen).changePentagram(isSelected);

	}

	public void deckClicked() {
		BattleScreen battleScreen = (BattleScreen) currentScreen;
		if (battleScreen.getTimeDelay() <= 0) {
			if (platform.getPlayerDeck().getCards().size > 0
					&& platform.getPlayerHandCards().size < 5) {
				Card card = platform.getPlayerDeck().draw();

				((BattleScreen) currentScreen).insertHandCard(card);
				platform.addPlayerHandCard(card);
				battleScreen
						.setTimeDelay((float) battleScreen.getSettings().drawInterval);
				battleScreen.getDeck().setReady(false);
				battleScreen.getDeck().stopBlink();
			}
		}
	}

	/***
	 * Updates the player life to the BattlePlatform and LifeBar objects.
	 * 
	 * @param amount
	 *            If negative, it means the player has taken the amount of
	 *            damage. If positive, it means the player was healed by the
	 *            specified amount.
	 */

	public void playerLifeChanged(int amount) {
		int currentLife = this.platform.getRules().getPlayerHP();
		int newLife = currentLife + amount;

		/*
		 * If new life is in normal range, apply changes without correction. If
		 * new life is less than zero, set new life to zero. If new life is
		 * greater than maximum life, set new life to maximum life.
		 */

		if (newLife >= 0
				&& newLife <= this.platform.getRules().getPlayerHPmax()) {
			this.platform.getRules().setPlayerHP(newLife);
		} else if (newLife < 0) {
			this.platform.getRules().setPlayerHP(0);
		} else if (newLife > this.platform.getRules().getPlayerHPmax()) {
			this.platform.getRules().setPlayerHP(
					this.platform.getRules().getPlayerHPmax());
		}

		((BattleScreen) this.currentScreen).getPlayerLife().setPercentage(
				this.platform.getRules().getPlayerHpPercent());

	}

	/***
	 * Updates the enemy life to the BattlePlatform and LifeBar objects.
	 * 
	 * @param amount
	 *            If negative, it means the enemy has taken the amount of
	 *            damage. If positive, it means the enemy was healed by the
	 *            specified amount.
	 */

	public void enemyLifeChanged(int amount) {
		int currentLife = this.platform.getRules().getEnemyHP();
		int newLife = currentLife + amount;

		/*
		 * If new life is in normal range, apply changes without correction. If
		 * new life is less than zero, set new life to zero. If new life is
		 * greater than maximum life, set new life to maximum life.
		 */
		if (newLife >= 0 && newLife <= 100) {
			((BattleScreen) this.currentScreen).getEnemyLife().setPercentage(
					newLife / currentLife);
			this.platform.getRules().setEnemyHP(newLife);
		} else if (newLife < 0) {
			this.platform.getRules().setEnemyHP(0);
		} else if (newLife > 100) {
			this.platform.getRules().setEnemyHP(
					this.platform.getRules().getEnemyHP());
		}
		((BattleScreen) this.currentScreen).getEnemyLife().setPercentage(
				this.platform.getRules().getEnemyHpPercent());
	}

	public void playerEnergyChanged(int amount) {
		int currentEnergy = this.platform.getRules().getPlayerEnergy();
		int newEnergy = currentEnergy + amount;

		/*
		 * If new energy is in normal range, apply changes without correction.
		 * If new energy is less than zero, set new energy to zero. If new
		 * energy is greater than maximum energy, set new energy to maximum
		 * energy.
		 */

		if (newEnergy >= 0
				&& newEnergy <= this.platform.getRules().getPlayerEnergyMax()) {
			this.platform.getRules().setPlayerEnergy(newEnergy);
		} else if (newEnergy < 0) {
			this.platform.getRules().setPlayerEnergy(0);
		} else if (newEnergy > this.platform.getRules().getPlayerEnergyMax()) {
			this.platform.getRules().setPlayerEnergy(
					this.platform.getRules().getPlayerEnergyMax());
		}

		((BattleScreen) this.currentScreen).getEnergyBar().setPercentage(
				this.platform.getRules().getPlayerEnergyPercent());

	}

	public void enemyEnergyChanged(int amount) {
		int currentEnergy = this.platform.getRules().getEnemyEnergy();
		int newEnergy = currentEnergy + amount;

		/*
		 * If new energy is in normal range, apply changes without correction.
		 * If new energy is less than zero, set new energy to zero. If new
		 * energy is greater than maximum energy, set new energy to maximum
		 * energy.
		 */

		if (newEnergy >= 0
				&& newEnergy <= this.platform.getRules().getEnemyEnergyMax()) {
			this.platform.getRules().setEnemyEnergy(newEnergy);
		} else if (newEnergy < 0) {
			this.platform.getRules().setEnemyEnergy(0);
		} else if (newEnergy > this.platform.getRules().getEnemyEnergyMax()) {
			this.platform.getRules().setEnemyEnergy(
					this.platform.getRules().getEnemyEnergyMax());
		}

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

	public void setCurrentscreen(BaseScreen currentScreen) {
		this.currentScreen = currentScreen;
	}

	public BattlePlatform getPlatform() {
		return platform;
	}

	public void setPlatform(BattlePlatform platform) {
		this.platform = platform;
	}

	public void updateTimer(float delta) {
		this.platform.getRules().update(delta);

		float time = this.platform.getRules().getTimer();
		((BattleScreen) this.currentScreen).getTimer().setTime(time);
	}

	public void removeCard(Card c) {
		this.platform.getMap().getTiles().get(c.getTile()).get(c.getLane())
				.removeValue(c, true);
		(c.getImage()).markToRemove(true);
		((MyStage) currentScreen.getStage()).getCurrentActor().removeValue(
				c.getImage(), true);

		for (int i = 0; i < platform.getPlayerField().getTiles().size; i++) {
			for (int j = 0; j < platform.getPlayerField().getTiles().get(i).size; j++) {
				if (platform.getPlayerField().getTiles().get(i).get(j) != null)
					if (platform.getPlayerField().getTiles().get(i).get(j)
							.equals(c)) {
						platform.getPlayerField().getTiles().get(i)
								.set(j, null);
						((BattleScreen) currentScreen).getBackcards().get(
								4 * i + j).setVisible(false);
					}
			}
		}

	}

	public void menuButtonClicked(ShadowStruggles game) {
		game.setScreenWithTransition(new InGameMenu(game, game.getController(),
				(BattleScreen) game.getScreen()));
	}
}
