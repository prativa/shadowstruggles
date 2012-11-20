package br.edu.ifsp.lp2.shadowstruggles.model;

import com.badlogic.gdx.math.MathUtils;

import br.edu.ifsp.lp2.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

public class Enemy {

	private float delay = 5;

	public Enemy() {
	}

	public void action(BattlePlatform platform, BattleScreen screen, float delta) {
		int tile;
		int lane;
		if (delay < 0) {
			Card card = null;
			if (platform.getMap().enemyBaseAttacked() > -1) {
				lane = platform.getMap().enemyBaseAttacked();
				tile = 35;
				if (platform.getMap().cardOnMap(
						new Card("Mineralogy", null, 0, "", 0, null), -1,
						Map.COMPUTER_PLAYER)) {
					if (platform.cardOnEnemyHand(new Card("Rock", null, 0, "",
							0, null)))
						card = platform.getCardFromEnemy("Rock");
				} else {
					if (platform.cardOnEnemyHand(new Card("Mineralogy", null,
							0, "", 0, null)))
						card = platform.getCardFromEnemy("Rock");
				}
			} else {
				tile = 35 - MathUtils.random(5);
				lane = MathUtils.random(3);
				if (platform.cardOnEnemyHand(new Card("DR-002", null, 0, "", 0,
						null)))
					card = platform.getCardFromEnemy("DR-002");
				else if (platform.cardOnEnemyHand(new Card("DR-003", null, 0,
						"", 0, null)))
					card = platform.getCardFromEnemy("DR-003");
				else if (platform.cardOnEnemyHand(new Card(
						"Eletric Current level 1", null, 0, "", 0, null)))
					card = platform.getCardFromEnemy("Eletric Current level 1");
				else if (platform.cardOnEnemyHand(new Card("Hacking", null, 0,
						"", 0, null)))
					card = platform.getCardFromEnemy("Hacking");
			}

			if (card != null && platform.getRules().getEnemyEnergy() > card.getEnergyCost()) {
				platform.getRules().setEnemyEnergy(platform.getRules().getEnemyEnergy()-card.getEnergyCost());
				summomCard(tile, lane, card, platform, screen);
			}else {
				if (platform.getEnemyDeck().getCards().size >= 0) {
					platform.getEnemyHandCards().add(
							platform.getEnemyDeck().draw());
				}
			}
			delay = 4;
		} else {
			delay = delay - delta;
		}
	}

	private void summomCard(int tile, int lane, Card card,
			BattlePlatform platform, BattleScreen screen) {
		
			platform.getEnemyHandCards().removeValue(card, true);
			card.setLane(lane);
			card.setTile(tile);
			card.setDirection(-card.getDirection());

			if (card.getClass().equals(Fighter.class)) {
				Fighter2D i2d = new Fighter2D((Fighter) card,
						screen.getController());
				i2d.create();
				card.setImage(i2d);
				platform.getMap().addCard(card, tile, lane);
				card.markLane = lane;
				screen.addGameObject(i2d);
			} else if (card.getClass().equals(Effect.class)) {
				Effect2D i2d = new Effect2D((Effect) card,
						screen.getController());
				i2d.create();
				card.setImage(i2d);
				platform.getMap().addCard(card, tile, lane);
				screen.addGameObject(i2d);
			} else if (card.getClass().equals(Trap.class)) {
				Trap2D i2d = new Trap2D((Trap) card, screen.getController());
				i2d.create();
				card.setImage(i2d);
				platform.getMap().addCard(card, tile, lane);
				screen.addGameObject(i2d);
			}
		
	}
}
