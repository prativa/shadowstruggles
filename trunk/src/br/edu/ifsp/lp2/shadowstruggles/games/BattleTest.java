package br.edu.ifsp.lp2.shadowstruggles.games;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.lp2.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.lp2.shadowstruggles.model.Map;
import br.edu.ifsp.lp2.shadowstruggles.model.enemies.TutorialEnemy;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

/***
 * A sample battle for testing the key aspects of the game engine.
 */

public class BattleTest extends BattleScreen {

	private BattlePlatform platform;
	private static final String DECKNAME = "Deck 1";
	private static final String ENEMY_DECKNAME = "Deck Tutorial";
	
	private float drawDelay;

	public BattleTest(ShadowStruggles game) {
		super(game, ProfileDAO.getProfile(1), game.getController(),
				new BattlePlatform(
						DeckDAO.getDeck(DECKNAME, game.getManager()),
						DeckDAO.getDeck(ENEMY_DECKNAME, game.getManager()), new Map(
								"cena1"), new DefaultRules(game.getManager()
								.getSettings()), new TutorialEnemy()), "Test");
		this.platform = super.battlePlatform;
		this.drawDelay = 0;
		
		this.timeElapsed = 0;
		game.getAudio().stop();
		game.getAudio().setMusic("s4");
		
		
		
	}

	public void draw(float delta) {
		if (drawDelay <= 0) {
			if (platform.getEnemyHandCards().size < 5
					&& platform.getEnemyDeck().getCards().size > 0) {
				
				drawDelay = 10;
				Card card = platform.getEnemyDeck().draw();
				platform.addEnemyHandCard(card);
				
			}
		} else
			drawDelay -= delta;
	}

	/*private void summonCard(float delta) {
		
		if (summonDelay <= 0) {
			
			
			if (platform.getEnemyHandCards().size > 0) {
				int tile=35-MathUtils.random(5);
				int lane=MathUtils.random(3);
				Card handCard = platform.getEnemyHandCards().pop();
				if(handCard.energyCost<=platform.rules.getEnemyEnergy()){
				if (handCard.getClass().equals(Fighter.class)) {
					handCard.setLane(lane);
					handCard.setTile(tile);

					Fighter2D f2d = new Fighter2D((Fighter) handCard,
							controller);
					f2d.getFighter().setDirection(-1);
					f2d.create();
					handCard.setImage(f2d);
					platform.getMap().addCard(handCard, tile, lane);
					controller.getCurrentScreen().addGameObject(f2d);
					controller.enemyEnergyChanged(-(handCard.energyCost));
					summonDelay = 3;
				}
				}
			}
		} else
			summonDelay -= delta;

	}*/

	@Override
	public void render(float delta) {
		super.render(delta);
		//time=(int)super.timeElapsed;
		/*draw(delta);
		summonCard(delta);*/
		
	}
	public static String getDeckname() {
		return DECKNAME;
	}

}
