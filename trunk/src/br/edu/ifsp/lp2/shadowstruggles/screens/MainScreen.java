package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

//TODO: Adicionar opção de salvar o jogo.

public class MainScreen extends BaseScreen {

	private Image background;
	private ImageButton changeProfile;
	private TextButton campaign;
	private TextButton freePlay;
	private TextButton config;
	private TextButton shop;
	private TextButton editDeck;

	public MainScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	private void initComponents() {
		if (!game.getAudio().getMusicName().equals("intro")) {
			game.getAudio().stop();
			game.getAudio().setMusic("intro");
		}

		background = new Image(new TextureRegion(game.getAssets().get(
				"data/images/objects/msbackground.png", Texture.class), 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);

		campaign = new TextButton(game.getManager().getMenuText().campaign,
				super.getSkin());
		campaign = ScreenUtils.initButton(campaign, 240, 525, 480, 110,
				super.getSkin());
		campaign.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new SceneScreen(game, game
						.getController()));

			}
		});

		freePlay = new TextButton(game.getManager().getMenuText().freePlay,
				super.getSkin());
		freePlay = ScreenUtils.initButton(freePlay, 240, 405, 480, 110,
				super.getSkin());
		freePlay.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new FreePlayScreen(game, game
						.getController()));
			}
		});

		editDeck = new TextButton(game.getManager().getMenuText().editDeck,
				super.getSkin());
		editDeck = ScreenUtils.initButton(editDeck, 240, 285, 480, 110,
				super.getSkin());
		final MainScreen screen = this;
		editDeck.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new EditDeckScreen(game,
						controller, screen));
			}

		});

		shop = new TextButton(game.getManager().getMenuText().shop,
				super.getSkin());
		shop = ScreenUtils
				.initButton(shop, 240, 165, 480, 110, super.getSkin());

		shop.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new ShopScreen(game, controller,
						screen));
			}

		});

		config = new TextButton(game.getManager().getMenuText().configurations,
				super.getSkin());
		config = ScreenUtils.initButton(config, 240, 45, 480, 110,
				super.getSkin());

		config.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new ConfigurationScreen(game, game
						.getController(), screen));
			}
		});
		changeProfile = new ImageButton(new TextureRegion(game.getAssets().get(
				"data/images/controls/Profiles.png", Texture.class)));
		changeProfile.x = 30;
		changeProfile.y = 20;
		changeProfile.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new SaveLoadScreen(game,
						controller, "main", false));
			}

		});

		stage.addActor(background);
		stage.addActor(campaign);
		stage.addActor(freePlay);
		stage.addActor(editDeck);
		stage.addActor(shop);
		stage.addActor(config);
		stage.addActor(changeProfile);

	}

	@Override
	public void loadLanguage() {
		campaign.setText(game.getManager().getMenuText().campaign);
		freePlay.setText(game.getManager().getMenuText().freePlay);
		editDeck.setText(game.getManager().getMenuText().editDeck);
		shop.setText(game.getManager().getMenuText().shop);
		config.setText(game.getManager().getMenuText().configurations);

	}

	@Override
	public void show() {
		super.show();
	}
}
