package br.edu.ifsp.lp2.shadowstruggles.games;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.lp2.shadowstruggles.model.Map;
import br.edu.ifsp.lp2.shadowstruggles.model.enemies.TutorialEnemy;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

public class Tutorial extends BattleScreen {

	public Tutorial(ShadowStruggles game) {
		super(game, game.getProfile(), game.getController(),
				new BattlePlatform(DeckDAO.getDeck("Tutorial Deck Player",
						game.getManager()), DeckDAO.getDeck(
						"Tutorial Deck Enemy", game.getManager()), new Map(
						"cena1"), new DefaultRules(game.getManager()
						.getSettings()), new TutorialEnemy()), game
						.getManager().getMenuText().tutorialBattle);
		game.getAudio().stop();
		game.getAudio().setMusic("s4");
	}

}
