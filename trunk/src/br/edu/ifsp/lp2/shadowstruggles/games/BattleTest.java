package br.edu.ifsp.lp2.shadowstruggles.games;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.battle.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.battle.Card;
import br.edu.ifsp.lp2.shadowstruggles.battle.DefaultRules;
import br.edu.ifsp.lp2.shadowstruggles.battle.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.battle.Map;
import br.edu.ifsp.lp2.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.lp2.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

/***
 * A sample battle for testing the key aspects of the game engine.
 */

public class BattleTest extends BattleScreen {

	private BattlePlatform platform;
	private static final String DECKNAME = "Deck 1";
	

	public BattleTest(ShadowStruggles game) {
		super(game, ProfileDAO.getProfile(1), game.getController(),
				new BattlePlatform(DeckDAO.getDeck(DECKNAME),
						DeckDAO.getDeck(DECKNAME), new Map("testmap"),
						new DefaultRules(game.getManager().retrieveSettings())));
		this.platform = super.battlePlatform;
		this.timeElapsed=0;
		// initDeck();
		summonCard();
	}

	public void draw() {
		Card card = platform.getEnemyDeck().draw();
		platform.addEnemyHandCard(card);

	}

	private void summonCard() {
		Card handCard = platform.getEnemyHandCards().pop();
		if (handCard.getClass().equals(Fighter.class)) {
			handCard.setLane(1);
			handCard.setTile(34);

			Fighter2D f2d = new Fighter2D((Fighter) handCard, controller);
			f2d.fighter.setDirection(-1);
			f2d.create();
			platform.getMap().addCard(handCard, 34, 1);
			controller.getCurrentScreen().addGameObject(f2d);
		}

	}
	

	

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		// dr.walk();
	}

	@Override
	public void show() {
		super.show();
	}

}
