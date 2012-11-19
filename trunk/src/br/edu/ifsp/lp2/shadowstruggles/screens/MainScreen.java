package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainScreen extends BaseScreen {

	private Image background;
	private TextButton campaign;
	private TextButton freePlay;
	private TextButton config;

	public MainScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	private void initComponents() {
		if (!game.getAudio().getMusicName().equals("scene1")) {
			game.getAudio().stop();
			game.getAudio().setMusic("scene1");
		}

		background = new Image(new TextureRegion(Assets.mainBackground, 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);

		campaign = new TextButton(game.getManager().getMenuText().campaign,
				super.getSkin());
		campaign = ScreenUtils.initButton(campaign, 240, 512, 480, 106);
		campaign.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				Assets.buttonSound3.play(1);
				game.setScreenWithTransition(new SceneScreen(game, game
						.getController()));
				// game.setScreenWithTransition(new BattleTest(game));
			}
		});

		freePlay = new TextButton(game.getManager().getMenuText().freePlay,
				super.getSkin());
		freePlay = ScreenUtils.initButton(freePlay, 240, 384, 480, 106);
		freePlay.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				Assets.buttonSound4.play(1);
				game.setScreenWithTransition(new FreePlayScreen(game, game
						.getController()));
				// game.setScreenWithTransition(new PosGameScreen(game,
				// controller, game.getManager().getMenuText().victory));
			}
		});

		config = new TextButton(game.getManager().getMenuText().configurations,
				super.getSkin());
		config = ScreenUtils.initButton(config, 240, 256, 480, 106);
		final MainScreen screen = this;
		config.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				Assets.buttonSound5.play(1);
				game.setScreenWithTransition(new ConfigurationScreen(game, game
						.getController(), screen));
			}
		});

		stage.addActor(background);
		stage.addActor(config);
		stage.addActor(campaign);
		stage.addActor(freePlay);

	}
	
	@Override
	public void loadLanguage() {
		config.setText(game.getManager().getMenuText().configurations);
		campaign.setText(game.getManager().getMenuText().campaign);
		freePlay.setText(game.getManager().getMenuText().freePlay);
	}

	@Override
	public void show() {
		super.show();
	}
}
