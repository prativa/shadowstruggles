package br.edu.ifsp.lp2.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.SoundManager;
import br.edu.ifsp.lp2.shadowstruggles.model.Effect;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.model.Trap;

/**
 * The game assets are loaded in this screen, which shows a progress bar to the
 * player indicating how much of the resources have been loaded.
 */
public class LoadingScreen extends BaseScreen {
	private static int BAR_MAX_WIDTH = 1024; // The width of the progress bar.

	private class LoadingBar extends Image {
		private float percentage = 0.0f;
		private Label percentageLbl;

		public LoadingBar() {
			super(new TextureRegion(new Texture(
					"data/images/objects/loading_bar.png"), 0, 0,
					BAR_MAX_WIDTH, 36));
			this.scaleX = 0.8f;
			this.scaleY = 1.9f;
		}

		public void update(float currentProgress) {
			percentage = currentProgress;
			this.scaleX = 0.8f * percentage;
		}

		public void drawLabel(Skin skin) {
			if (percentageLbl != null)
				this.getStage().removeActor(percentageLbl);

			String lifeString = String.valueOf((int) (percentage * 100)) + "%";
			this.percentageLbl = new Label(lifeString, skin);
			this.percentageLbl.x = this.x + 400;
			this.percentageLbl.y = this.y + 20;
			this.percentageLbl.setStyle(new LabelStyle(skin
					.getFont("andalus-font"), Color.WHITE));
			this.getStage().addActor(percentageLbl);
		}
	}

	private Image background;
	private ShadowStruggles game;
	private LoadingBar bar;

	public LoadingScreen(ShadowStruggles game) {
		super(game);

		// Load main textures.
		
		game.getAssets().load("data/images/controls/right.png", Texture.class);
		game.getAssets().load("data/images/controls/Minus.png", Texture.class);
		game.getAssets().load("data/images/controls/Plus.png", Texture.class);
		game.getAssets().load("data/images/controls/Mute.png", Texture.class);
		game.getAssets().load("data/images/controls/Profiles.png",
				Texture.class);
		game.getAssets().load("data/images/objects/deck.png", Texture.class);
		game.getAssets().load("data/images/objects/deckNotReady.png",
				Texture.class);
		game.getAssets().load("data/images/objects/energy100.png",
				Texture.class);
		game.getAssets().load("data/images/objects/background.png",
				Texture.class);
		game.getAssets().load("data/images/objects/msbackground.png",
				Texture.class);
		game.getAssets().load("data/images/objects/life100.png", Texture.class);
		game.getAssets().load("data/images/objects/pentagram.png",
				Texture.class);
		game.getAssets().load("data/images/objects/back_card.png",
				Texture.class);

		// Load music and sound effects.
		
		game.getAssets().load("data/audio/battle.ogg", Music.class);
		game.getAssets().load("data/audio/button_1.ogg", Sound.class);
		game.getAssets().load("data/audio/button_2.ogg", Sound.class);
		game.getAssets().load("data/audio/button_3.ogg", Sound.class);
		game.getAssets().load("data/audio/button_4.ogg", Sound.class);
		game.getAssets().load("data/audio/button_5.ogg", Sound.class);
		game.getAssets().load("data/audio/button_6.ogg", Sound.class);
		game.getAssets().load("data/audio/button_7.ogg", Sound.class);
		game.getAssets().load("data/audio/button_8.ogg", Sound.class);
		game.getAssets().load("data/audio/intro.ogg", Music.class);

		// Load textures for each card: fighter, effect and trap.
		
		for (Fighter f : game.getManager().getFighterList()) {
			game.getAssets().load(
					"data/images/sprites/" + f.getName()
							+ "/walk/animation_sheet.png", Texture.class);

			game.getAssets().load(
					"data/images/sprites/" + f.getName()
							+ "/attack/animation_sheet.png", Texture.class);

			game.getAssets().load(
					"data/images/sprites/" + f.getName() + "/card.png",
					Texture.class);
		}

		for (Effect e : game.getManager().getEffectList()) {
			game.getAssets().load(
					"data/images/sprites/" + e.getName() + "/card.png",
					Texture.class);

			game.getAssets().load(
					"data/images/sprites/" + e.getName()
							+ "/animation_sheet.png", Texture.class);
		}

		for (Trap t : game.getManager().getTrapList()) {
			game.getAssets().load(
					"data/images/sprites/" + t.getName() + "/card.png",
					Texture.class);

			game.getAssets().load(
					"data/images/sprites/" + t.getName()
							+ "/animation_sheet.png", Texture.class);
		}

		this.game = game;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	public void initComponents() {
		stage.clear();

		background = new Image(
				new TextureRegion(
						new Texture(
								Gdx.files
										.internal("data/images/objects/logo_shadow_struggles.png")),
						512, 512));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 512f);
		background.y += 100;

		bar = new LoadingBar();
		bar.x = 80;
		bar.y = 50;

		stage.addActor(background);
		stage.addActor(bar);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (game.getAssets().update()) {
			game.setAudio(new SoundManager(game.getAssets()));
			game.getAudio().setMusic("intro");
			game.setScreenWithTransition(new StartScreen(game, game
					.getController()));
		} else {
			bar.update(game.getAssets().getProgress());
			bar.drawLabel(super.getSkin());
		}

	}

}
