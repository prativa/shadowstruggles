package br.edu.ifsp.lp2.shadowstruggles.model;

import com.sun.opengl.impl.mipmap.Image;

import br.edu.ifsp.lp2.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

public class Enemy {

	private float delay = 3;

	public Enemy() {
	}

	public void action(BattlePlatform platform, BattleScreen screen,float delta) {

		if (delay < 0) {
			Card card = null;

			int lane = platform.getMap().nextAvailableLane(Map.COMPUTER_PLAYER), tile = platform
					.getMap().nextAvailableTile(lane, Map.COMPUTER_PLAYER);

			if (platform.getMap().enemyBaseAttacked()) {
				if (platform.getMap().cardOnMap(
						new Card("Mineralogy", null, 0, "", 0, null), -1,
						Map.COMPUTER_PLAYER)) {
					if (platform.cardOnEnemyHand(new Card("Rock", null, 0, "", 0,
							null)))
						card = platform.getCardFromEnemy("Rock");
				} else {
					if (platform.cardOnEnemyHand(new Card("Mineralogy", null, 0, "",
							0, null)))
						card = platform.getCardFromEnemy("Rock");
				}
			} else {
				if (platform
						.cardOnEnemyHand(new Card("DR-002", null, 0, "", 0, null)))
					card = platform.getCardFromEnemy("DR-002");
				else if (platform.cardOnEnemyHand(new Card("DR-003", null, 0, "", 0,
						null)))
					card = platform.getCardFromEnemy("DR-002");
				else if (platform.cardOnEnemyHand(new Card(
						"Eletric Current level 1", null, 0, "", 0, null)))
					card = platform.getCardFromEnemy("Eletric Current level 1");
				else if (platform.cardOnEnemyHand(new Card("Hacking", null, 0, "", 0,
						null)))
					card = platform.getCardFromEnemy("Hacking");
			}

			if (card != null)
				summomCard(tile, lane, card, platform, screen);
			else
				platform.getEnemyHandCards()
						.add(platform.getEnemyDeck().draw());
			delay=3;
		}else{
			delay = delay - delta;
		}
	}

	private void summomCard(int tile, int lane, Card card,
			BattlePlatform platform, BattleScreen screen) {
		card.setDirection(-card.getDirection());

		if (card.getClass().equals(Fighter.class)) {
			Fighter2D i2d = new Fighter2D((Fighter) card,
					screen.getController());
			i2d.create();
			card.setImage(i2d);
			platform.getMap().addCard(card, tile, lane);
			screen.addGameObject(i2d);
		} else if (card.getClass().equals(Effect.class)) {
			Effect2D i2d = new Effect2D((Effect) card, screen.getController());
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
