package com.shadowstruggles.screens;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.shadowstruggles.Controller;
import com.shadowstruggles.Profile;
import com.shadowstruggles.ShadowStruggles;
import com.shadowstruggles.battle.BattleLogic;
import com.shadowstruggles.battle.BattlePlatform;
import com.shadowstruggles.battle.DefaultRules;
import com.shadowstruggles.battle.Fighter;
import com.shadowstruggles.battle.Map;
import com.shadowstruggles.characters.BaseCharacter;
import com.shadowstruggles.object2d.Deck2D;
import com.shadowstruggles.object2d.EnergyBar;
import com.shadowstruggles.object2d.HandBackground;
import com.shadowstruggles.object2d.HandCard;
import com.shadowstruggles.object2d.LifeBar;
import com.shadowstruggles.object2d.Map2D;

/***
 * Establishes the interaction between the game and the player.
 * It shows the items in the logic layer and acts as a listener for the 
 * {@link Controller} class.
 */

public class BattleScreen extends BaseScreen {

	static final int BACKGROUND_WIDTH = 1920;
	static final int BACKGROUND_HEIGHT = 480;
	static final int BACKGROUND_Y = 160;
	static final int CAMERA_INITIAL_X = 480;
	
	private OrthographicCamera camera;
	private TextureRegion texture;
	private BattleLogic battleLogic;
	private Map2D map;
	private EnergyBar energyBar;
	private Deck2D deck;
	private HandBackground background;
	
	public LifeBar playerLife;
	public LifeBar enemyLife;
	
	/***
	 * A test for the 'DR-002' fighter card on the field.
	 */
	
	private Fighter testFighter;
	
	/***
	 * A test for the 'DR-002' fighter card on the player's hand.
	 */
	private HandCard handCard;

	/***
	 * Initializes the visual elements.
	 * 
	 * @param game
	 *            A representation of the game itself.
	 * @param mapFile
	 *            A file string specifying the map image to be loaded.
	 * @param player
	 *            The current profile.
	 * @param controller
	 *
	 * @param battleLogic
	 */

	public BattleScreen(ShadowStruggles game, String mapFile, Profile player,
			Controller controller, BattleLogic battleLogic) {
		super(game, controller);
		controller.setCurrentscreen(this);
		controller.setLogic(battleLogic);
		this.camera = new OrthographicCamera(this.width, this.height);
		this.camera.position.set(CAMERA_INITIAL_X, 160, 0);
		stage.setCamera(camera);
		
		this.battleLogic = battleLogic;
		testFighter = new Fighter(battleLogic, 1, 1, 1, "DR-002");
		// bl.getPlatform().getMap().getTiles().get(1).get(1).add(teste);
		texture = new TextureRegion(new Texture(Gdx.files.internal(mapFile)),
				BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		handCard = new HandCard(controller, "DR-002", 200);
		map=new Map2D(controller, texture);
	}

	public void drawCard(ArrayList<BaseCharacter> playerDeck,
			ArrayList<BaseCharacter> handCards) {

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		initComponents();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		keyInput(delta);
		
		testFighter.action();
		handCard.move(this.stage, CAMERA_INITIAL_X);
		deck.move(this.stage, CAMERA_INITIAL_X);
		energyBar.move(this.stage, CAMERA_INITIAL_X);
		background.move(this.stage, CAMERA_INITIAL_X);
		
		playerLife.update();
		
		camera.update();
//		ShapeRenderer sp = new ShapeRenderer(); // Rectangle for the bottom UI.
//		/* ShapeRenderer sp2= new ShapeRenderer(); */
//		sp.setProjectionMatrix(camera.combined);
//		sp.begin(ShapeType.FilledRectangle);
//		
//		// Update the rectangle's position so that it remains fixed on the screen.
//		for (float i = 0; i < 100; i++) {
//			sp.filledRect(40 + i, 500f, 1, 20);
//		}
//		
//		sp.end();

		/*
		 * sp2.begin(ShapeType.FilledRectangle); sp2.filledRect(10f, 10f, 940f,
		 * 140f); sp2.end();
		 */
	}

	private BattlePlatform getBattlePlatform() {
		return battleLogic.getPlatform();
	}

	private DefaultRules getRules() {
		return getBattlePlatform().getRules();
	}

	/***
	 * Manages input from the keyboard.
	 * LEFT KEY - Moves the camera to the left.
	 * RIGHT KEY - Moves the camera to the right.
	 */
	
	private void keyInput(float delta) {

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (camera.position.x > 480)
				camera.translate(-3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (camera.position.x < 1440)
				camera.translate(3, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if(delta > 0.02f)
				this.controller.playerLifeChanged(4);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			if(delta > 0.02f)
				this.controller.playerLifeChanged(-4);
		}
	}

	/***
	 * Invoked by resizing events. It establishes the background properties and 
	 * adds the actors to the stage.
	 */
	
	private void initComponents() {
		map.width = BACKGROUND_WIDTH;
		map.height = BACKGROUND_HEIGHT;
		map.x = 0;
		map.y = BACKGROUND_Y;
		background= new HandBackground(0);
		background.y=0;
		deck = new Deck2D(controller, 830);
		deck.y=20;
		energyBar = new EnergyBar(controller, 100);
		energyBar.y=50;
		playerLife = new LifeBar(1);
		playerLife.x=20;
		playerLife.y=500;
		stage.addActor(background);
		stage.addActor(deck);
		stage.addActor(energyBar);
		stage.addActor(map);		
		stage.addActor(handCard);
		stage.addActor(playerLife);
		
	}

	private void cardClicked() {
		// TODO Auto-generated method stub

	}

	private void deckClicked() {
		// TODO Auto-generated method stub

	}

	private void menuClicked() {
		// TODO Auto-generated method stub

	}

	private void pauseClicked() {
		// TODO Auto-generated method stub

	}

	private void muteDesmuteClicked() {
		// TODO Auto-generated method stub

	}

	private void cardDragged() {
		// TODO Auto-generated method stub

	}

	private void cardDropped() {
		// TODO Auto-generated method stub

	}

	private void tileClicked() {
		// TODO Auto-generated method stub

	}

}
