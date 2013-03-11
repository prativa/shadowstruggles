package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
		background.setScaleX (960f / 512f);
		background.setScaleY (640f / 380f);
		volumePlus = new Image(new TextureRegion(game.getAssets().get("data/images/controls/Plus.png", Texture.class)));
		volumePlus.setWidth (100);
		volumePlus.setHeight(100);
		volumePlus.setX( 290);
		volumePlus.setY(400);
		volumePlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				game.getAudio().setVolume(0.05f);
				updateVolume();
				game.getAudio().playSound("button_2");

			}
		});
		volumeMinus = new Image(new TextureRegion(game.getAssets().get("data/images/controls/Minus.png", Texture.class)));
		volumeMinus.setWidth(100);
		volumeMinus.setHeight ( 100);
		volumeMinus.setX( 50);
		volumeMinus.setY (400);
		volumeMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().setVolume(0.05f * (-1));
				updateVolume();
				game.getAudio().playSound("button_2");

			}
		});
		returnButton = new TextButton(
				game.getManager().getMenuText().returnToStart, super.getSkin());
		returnButton = ScreenUtils.defineButton(returnButton, 100, 100, 300, 100, super.getSkin());
		returnButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				previousScreen.loadLanguage();
				game.setScreenWithTransition(previousScreen);

			}
		});

		volume = new Label(game.getAudio().getVolumeNumber(), super.getSkin());
		volume.setWidth (50);
		volume.setHeight(100);
		volume.setX(200);
		volume.setY(400);
		volume.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		volumeLabel = new Label(
				this.getGame().getManager().getMenuText().volume,
				super.getSkin());
		volumeLabel.setWidth(50);
		volumeLabel.setHeight(100);
		volumeLabel.setX(150);
		volumeLabel.setY(500);
		volumeLabel.setStyle(new LabelStyle(super.getSkin().getFont(
				"default-font"), Color.BLACK));

		soundOnOff = new Image(new TextureRegion(game.getAssets().get("data/images/controls/Mute.png", Texture.class)));
		soundOnOff.setWidth(100);
		soundOnOff.setHeight(100);
		soundOnOff.setX(20);
		soundOnOff.setY(500);
		soundOnOff.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
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
			button = ScreenUtils.defineButton(button, 500, 540 - (i * 90), 300,
					80, super.getSkin());
			button.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
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
