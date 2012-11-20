package br.edu.ifsp.lp2.shadowstruggles.model.enemies;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Effect;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.model.Trap;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

public abstract class Enemy {

	protected float delay = 5;

	public Enemy() {
	}

	public abstract void action(BattlePlatform platform, BattleScreen screen,
			float delta);

	protected void summomCard(int tile, int lane, Card card,
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
