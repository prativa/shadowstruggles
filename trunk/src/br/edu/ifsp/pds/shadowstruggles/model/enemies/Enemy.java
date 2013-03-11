package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.Trap;
import br.edu.ifsp.pds.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

/***
 * A representation of an enemy.
 */

public abstract class Enemy {

	protected float delay;

	public Enemy() {
		this.delay = 5;
	}

	public abstract void action(BattlePlatform platform, BattleScreen screen,
			float delta);

	/***
	 * Summom a card in the enemy field
	 */

	protected void summomCard(int tile, int lane, Card card,
			BattlePlatform platform, BattleScreen screen) {
		platform.getEnemyHandCards().removeValue(card, true);
		card.setLane(lane);
		card.setTile(tile);
		card.setDirection(-card.getDirection());

		if (card.getClass().equals(Fighter.class)) {
			Fighter2D i2d = new Fighter2D((Fighter) card,
					screen.getGame());
			i2d.create();
			card.setImage(i2d);
			platform.getMap().addCard(card, tile, lane);
			card.setMarkLane(lane);
			screen.addGameObject(i2d);
		} else if (card.getClass().equals(Effect.class)) {
			Effect2D i2d = new Effect2D((Effect) card, screen.getGame());
			i2d.create();
			card.setImage(i2d);
			platform.getMap().addCard(card, tile, lane);
			screen.addGameObject(i2d);
		} else if (card.getClass().equals(Trap.class)) {
			Trap2D i2d = new Trap2D((Trap) card, screen.getGame());
			i2d.create();
			card.setImage(i2d);
			platform.getMap().addCard(card, tile, lane);
			screen.addGameObject(i2d);
		}
	}
}
