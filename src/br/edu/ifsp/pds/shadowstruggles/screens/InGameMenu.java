package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InGameMenu extends BaseScreen {
	private Image background;
	private TextButton returnToGame;
	private TextButton exitGame;
	private TextButton config;
	private TextButton checkCards;
	private BattleScreen battleScreen;

	public InGameMenu(ShadowStruggles game, Controller controller,
			BattleScreen battleScreen) {
		super(game, controller);
		this.battleScreen = battleScreen;
		initComponents();

	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 200);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	private void initComponents() {
		background = new Image(new TextureRegion(game.getAssets().get("data/images/objects/msbackground.png", Texture.class), 512,
				380));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		returnToGame = new TextButton(
				game.getManager().getMenuText().returnToGame, getSkin());
		returnToGame = ScreenUtils.defineButton(returnToGame, 230, 500, 500, 100, super.getSkin());
		returnToGame.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(battleScreen);

			}
		});
		checkCards = new TextButton(game.getManager().getMenuText().checkCards,
				getSkin());
		checkCards = ScreenUtils.defineButton(checkCards, 230, 380, 500, 100, super.getSkin());
		final InGameMenu menu = this;
		checkCards.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new CheckCardsScreen(game,
						controller, menu));

			}
		});
		config = new TextButton(game.getManager().getMenuText().configurations,
				getSkin());
		config = ScreenUtils.defineButton(config, 230, 260, 500, 100, super.getSkin());

		config.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new ConfigurationScreen(game,
						controller, menu));

			}
		});

		exitGame = new TextButton(game.getManager().getMenuText().exit,
				getSkin());
		exitGame = ScreenUtils.defineButton(exitGame, 230, 80, 500, 100, super.getSkin());
		exitGame.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new MainScreen(game, controller));
			}

		});
		stage.addActor(background);
		stage.addActor(checkCards);
		stage.addActor(config);
		stage.addActor(exitGame);
		stage.addActor(returnToGame);
	}

	@Override
	public void loadLanguage() {
		checkCards.setText(game.getManager().getMenuText().checkCards);
		config.setText(game.getManager().getMenuText().configurations);
		exitGame.setText(game.getManager().getMenuText().exit);
		returnToGame.setText(game.getManager().getMenuText().returnToGame);
	}

	public BattleScreen getBattleScreen() {
		return battleScreen;
	}

	public void dispose() {

	}

}
