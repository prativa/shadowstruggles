package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.Profile;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.battle.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.battle.Card;
import br.edu.ifsp.lp2.shadowstruggles.object2d.CameraController;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Deck2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.EnergyBar;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.HandBackground;
import br.edu.ifsp.lp2.shadowstruggles.object2d.HandCard;
import br.edu.ifsp.lp2.shadowstruggles.object2d.LifeBar;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Map2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/***
 * Establishes the interaction between the game and the player. It shows the
 * items in the logic layer and acts as a listener for the {@link Controller}
 * class.
 */


public class BattleScreen extends BaseScreen {

	protected BattlePlatform battlePlatform;
	private Map2D map2d;
	private Deck2D deck;
	private HandBackground background;
	private CameraController right;
	private CameraController left;
	private boolean inicializado = false;
	public EnergyBar energyBar;
	public LifeBar playerLife;
	public LifeBar enemyLife;
	public final int FPS = 30;
	public float timeElapsed;
	public float time;
	public float timeDelay;

	/***
	 * A test for the 'DR-002' fighter card on the field.
	 */

	/***
	 * A test for the 'DR-002' fighter card on the player's hand.
	 */

	/***
	 * Initializes the visual elements.
	 * 
	 * @param game
	 *            A representation of the game itself.
	 * @param player
	 *            The current profile.
	 * @param controller
	 * @param battlePlatform
	 */

	public BattleScreen(ShadowStruggles game, Profile player,
			Controller controller, BattlePlatform battlePlatform) {
		super(game, controller);
		controller.setCurrentscreen(this);
		controller.setPlatform(battlePlatform);

		this.battlePlatform = battlePlatform;
		// testFighter = new Fighter(battlePlatform, 1, 1, 1, "DR-002");
		String mapPath = "data/images/maps/"
				+ battlePlatform.getMap().getName() + ".png";
		TextureRegion mapImage = new TextureRegion(new Texture(
				Gdx.files.internal(mapPath)), settings.backgroundWidth,
				settings.backgroundHeight);
		map2d = new Map2D(controller, mapImage);
		timeElapsed = 0;
		timeDelay=0;
		time = 0;
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (time >= 1 / FPS) {
			time = 0;
			keyInput(delta);
			this.timeDelay-=delta;
			this.timeElapsed += delta;
			for (Actor a : stage.getActors()) {
				if (a.getClass().equals(Fighter2D.class))
					((Fighter2D) (a)).render();
				else if (a.getClass().equals(HandCard.class))
					((HandCard) a).move(this.stage, CAMERA_INITIAL_X);
			}
			
			deck.move(this.stage, CAMERA_INITIAL_X);
			energyBar.move(this.stage, CAMERA_INITIAL_X);
			background.move(this.stage, CAMERA_INITIAL_X);
			right.move(this.stage, CAMERA_INITIAL_X);
			left.move(this.stage, CAMERA_INITIAL_X);
			playerLife.update();
			enemyLife.update();
			energyBar.update();
			enemyLife.drawLife(battlePlatform.getRules().getEnemyHP(),
					battlePlatform.getRules().getEnemyHPmax());
			playerLife.drawLife(battlePlatform.getRules().getPlayerHP(),
					battlePlatform.getRules().getPlayerHPmax());
			energyBar.drawEnergy(battlePlatform.getRules().getPlayerEnergy(),
					battlePlatform.getRules().getPlayerEnergyMax());
			camera.update();
		} else
			time += delta;
	}

	/***
	 * Manages input from the keyboard. LEFT KEY - Moves the camera to the left.
	 * RIGHT KEY - Moves the camera to the right.
	 */

	private void keyInput(float delta) {

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

			this.controller.playerEnergyChanged(-4);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

			this.controller.playerEnergyChanged(4);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			// if (delta > 0.02f)
			this.controller.playerLifeChanged(4);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			// if (delta > 0.02f)
			this.controller.playerLifeChanged(-4);
		}
	}

	/***
	 * Invoked by resizing events. It establishes the visual elements'
	 * disposition on the screen and adds the actors to the stage.
	 */

	private void initComponents() {
		map2d.width = settings.backgroundWidth;
		map2d.height = settings.backgroundHeight;
		if (!inicializado) {
			map2d.x = 0;
			map2d.y = BACKGROUND_Y;

			background = new HandBackground(0);
			background.y = 0;

			right = new CameraController(1);
			left = new CameraController(-1);
			deck = new Deck2D(controller, settings.deckX);
			deck.y = settings.bottomElementY;

			energyBar = new EnergyBar(settings.energyX - 40);
			energyBar.y = settings.bottomElementY;

			playerLife = new LifeBar();
			playerLife.x = settings.playerLifeX;
			playerLife.y = settings.lifeBarY;

			enemyLife = new LifeBar();
			enemyLife.x = settings.enemyLifeX;
			enemyLife.y = settings.lifeBarY;

			inicializado = true;

		}
		stage.addActor(background);
		stage.addActor(deck);
		stage.addActor(energyBar);
		stage.addActor(map2d);
		stage.addActor(playerLife);
		stage.addActor(enemyLife);
		stage.addActor(right);
		stage.addActor(left);
		right.init();
		left.init();
		enemyLife.drawLife(battlePlatform.getRules().getEnemyHP(),
				battlePlatform.getRules().getEnemyHPmax(), super.getSkin());
		playerLife.drawLife(battlePlatform.getRules().getPlayerHP(),
				battlePlatform.getRules().getPlayerHPmax(), super.getSkin());
		energyBar
				.initEnergy(battlePlatform.getRules().getPlayerEnergy(),
						battlePlatform.getRules().getPlayerEnergyMax(),
						super.getSkin());

		for (int i = 0; i < 5; i++) {
			Card temp = battlePlatform.getPlayerDeck().draw();
			battlePlatform.getPlayerHandCards().add(temp);
			HandCard h = new HandCard(controller, temp.getName(),
					settings.firstCardX + 130 * i, temp);
			h.y = settings.bottomElementY;
			stage.addActor(h);
		}
		for (int i = 0; i < 5; i++) {
			Card temp = battlePlatform.getEnemyDeck().draw();
			battlePlatform.getEnemyHandCards().add(temp);
		}

	}

	public void insertHandCard(Card card) {
		HandCard handCard = new HandCard(controller, card.getName(),
				settings.firstCardX + 130
						* (battlePlatform.getPlayerHandCards().size), card);
		handCard.y = settings.bottomElementY;
		stage.addActor(handCard);
	}

}
