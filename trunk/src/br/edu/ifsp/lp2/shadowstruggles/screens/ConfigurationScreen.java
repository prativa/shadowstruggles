package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Entry;

public class ConfigurationScreen extends BaseScreen {
	private Image background;
	private Image volumePlus;
	private Image volumeMinus;
	private TextButton returnButton;
	private Label volume;
	private Label volumeLabel;
	private Image soundOnOff;
	private Array<TextButton> languages;
	private BaseScreen previousScreen;

	public ConfigurationScreen(ShadowStruggles game, Controller controller,
			BaseScreen screen) {
		super(game, controller);
		this.previousScreen = screen;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	private void initComponents() {
		background = new Image(new TextureRegion(game.getAssets().get("data/images/objects/msbackground.png", Texture.class), 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);
		volumePlus = new Image(new TextureRegion(game.getAssets().get("data/images/controls/Plus.png", Texture.class)));
		volumePlus.width = 100;
		volumePlus.height = 100;
		volumePlus.x = 290;
		volumePlus.y = 400;
		volumePlus.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {

				game.getAudio().setVolume(0.05f);
				updateVolume();
				game.getAudio().playSound("button_2");

			}
		});
		volumeMinus = new Image(new TextureRegion(game.getAssets().get("data/images/controls/Minus.png", Texture.class)));
		volumeMinus.width = 100;
		volumeMinus.height = 100;
		volumeMinus.x = 50;
		volumeMinus.y = 400;
		volumeMinus.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().setVolume(0.05f * (-1));
				updateVolume();
				game.getAudio().playSound("button_2");

			}
		});
		returnButton = new TextButton(
				game.getManager().getMenuText().returnToStart, super.getSkin());
		returnButton = ScreenUtils.initButton(returnButton, 100, 100, 300, 100, super.getSkin());
		returnButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().playSound("button_6");
				previousScreen.loadLanguage();
				game.setScreenWithTransition(previousScreen);

			}
		});

		volume = new Label(game.getAudio().getVolumeNumber(), super.getSkin());
		volume.width = 50;
		volume.height = 100;
		volume.x = 200;
		volume.y = 400;
		volume.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		volumeLabel = new Label(
				this.getGame().getManager().getMenuText().volume,
				super.getSkin());
		volumeLabel.width = 50;
		volumeLabel.height = 100;
		volumeLabel.x = 150;
		volumeLabel.y = 500;
		volumeLabel.setStyle(new LabelStyle(super.getSkin().getFont(
				"default-font"), Color.BLACK));

		soundOnOff = new Image(new TextureRegion(game.getAssets().get("data/images/controls/Mute.png", Texture.class)));
		soundOnOff.width = 100;
		soundOnOff.height = 100;
		soundOnOff.x = 20;
		soundOnOff.y = 500;
		soundOnOff.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().setVolume(-1);
				updateVolume();
			}
		});

		this.languages = new Array<TextButton>();
		int i = 0;
		stage.addActor(background);
		for (Entry<String, String> language : game.getManager().getLanguages()
				.entries()) {
			final String lang = language.value;
			TextButton button = new TextButton(language.key, super.getSkin());
			button = ScreenUtils.initButton(button, 500, 540 - (i * 90), 300,
					80, super.getSkin());
			button.setClickListener(new ClickListener() {

				@Override
				public void click(Actor actor, float x, float y) {
					game.getAudio().playSound("button_7");
					game.getManager().changeLanguage(lang);
					game.getProfile().setLanguage(lang);
					game.getProfile().setCurrentScene(
							SceneDAO.getScene(game.getProfile()
									.getCurrentScene().getId(),
									game.getManager()));
					game.getManager().writeProfile(game.getProfile());
					game.setScreenWithTransition(new ConfigurationScreen(game,
							controller, previousScreen));

				}
			});
			this.languages.add(button);
			i++;
			stage.addActor(button);
		}

		stage.addActor(returnButton);
		stage.addActor(volumeLabel);
		stage.addActor(volume);
		stage.addActor(volumeMinus);
		stage.addActor(volumePlus);
		stage.addActor(soundOnOff);
	}

	private void updateVolume() {
		this.volume.setText(game.getAudio().getVolumeNumber());

	}

}
