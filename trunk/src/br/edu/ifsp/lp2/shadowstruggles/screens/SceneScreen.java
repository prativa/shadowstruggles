package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.lp2.shadowstruggles.games.Tutorial;
import br.edu.ifsp.lp2.shadowstruggles.model.Scene;
import br.edu.ifsp.lp2.shadowstruggles.object2d.TransitionControl;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

//TODO: Botão para menu.
public class SceneScreen extends BaseScreen implements InputProcessor {
	private Scene scene;
	private int touchY;
	private ShadowStruggles game;
	private boolean touched;
	private boolean justTouched;
	
	private Image background;
	private Label text;
	private Image next;
	private TextButton[] choices;
	private Image box;
	private TextButton menu;
	
	public InputMultiplexer inputSources;

	public SceneScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		this.game = game;
		this.scene = game.getProfile().getCurrentScene();
		inputSources = new InputMultiplexer();
		initComponents();
		// newFont.setScale(1.5f, 1.5f); aumentar e diminuir a fonte
	}

	private void initComponents() {
		this.text = new Label(scene.getScript(), super.getSkin());
		if (!scene.getBackgroundImage().equals("")) {
			if (scene.getBackgroundImage().contains("/maps/")) {
				background = new Image(new TextureRegion(new Texture(
						Gdx.files.internal(scene.getBackgroundImage())),
						settings.backgroundWidth, settings.backgroundHeight));
			} else {
				background = new Image(new Texture(Gdx.files.internal(scene
						.getBackgroundImage())));
			}
		} else
			background = new Image();

		background.x = 0;
		background.y = 0;
		background.width = 960;
		background.height = 640;
		text.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.GREEN));
		text.width = 800;
		text.setWrap(true);
		text.height = text.getPrefHeight();
		text.x = 80;
		text.y = 640 - text.height - 50;

		box = new Image(new Texture(
				Gdx.files.internal("data/images/objects/box.png")));
		box.width = text.width + 50;
		box.height = text.height + 50;
		box.x = 55;
		box.y = text.y - 25;

		next = new TransitionControl(1) {
			@Override
			public boolean touchDown(float x, float y, int pointer) {
				nextScreen();
				return super.touchDown(x, y, pointer);
			}
		};

		next.scaleY = 6.0f;

		menu = new TextButton("M", getSkin());
		menu = ScreenUtils.initButton(menu, next.x - 15, 70, 50, 50);
		menu.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.setScreenWithTransition(new MainScreen(game, controller));
			}
			
		});
		
		if (!scene.getBackgroundMusic().equals("")) {
			game.getAudio().stop();
			game.getAudio().setMusic(scene.getBackgroundMusic());
		}
		inputSources.addProcessor(this.stage);
		inputSources.addProcessor(this);

		stage.addActor(background);
		stage.addActor(box);
		stage.addActor(text);
		stage.addActor(next);
		stage.addActor(menu);

		if (scene.getChoices().length > 1) {
			choices = new TextButton[scene.getChoices().length];
			for (int i = 0; i < scene.getChoices().length; i++) {
				final int j = i;
				choices[i] = new TextButton(scene.getChoices()[i],
						super.getSkin());

				choices[i] = ScreenUtils.initButton(choices[i], box.x
						+ (i * 220), box.y - 100, 200, 80);
				choices[i].setClickListener(new ClickListener() {

					@Override
					public void click(Actor actor, float x, float y) {
						if (choices[j].getText().equals(scene.getChoices()[0])) {
							game.getProfile().setCurrentScene(
									SceneDAO.getScene(scene.getNextId(),
											game.getManager()));

						} else {
							game.getProfile().setCurrentScene(
									SceneDAO.getScene(scene.getNextId() + 1,
											game.getManager()));
						}

					}
				});
				stage.addActor(choices[i]);
			}
		} else
			stage.addActor(next);
	}

	public void nextScreen() {
		if (scene.getId() == 1000) {
			game.setScreenWithTransition(new Tutorial(game));
		} else {
			game.getProfile().setCurrentScene(
					SceneDAO.getScene(scene.getNextId(), game.getManager()));
			game.getManager().writeProfile(game.getProfile());
			game.setScreenWithTransition(new SceneScreen(game, controller));
		}
	}

	@Override
	public void resize(int width, int height) {

		super.resize(width, height);

		Gdx.input.setInputProcessor(inputSources);
		// initComponents();

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		background.y = this.camera.position.y - CAMERA_INITIAL_Y;
		next.y = this.camera.position.y - CAMERA_INITIAL_Y + 320;
		menu.y = this.camera.position.y - CAMERA_INITIAL_Y + 20;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));

		touched = true;

		if (!justTouched) {
			justTouched = true;
			touchY = y;
		}

		return true;

	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));

		if (touched) {

			/*
			 * controller.mapClicked(x +
			 * controller.getCurrentScreen().camera.position.x - 480, invertY -
			 * 160);
			 */

			touched = false;
		}

		justTouched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));
		if (touched) {

			if (controller.getCurrentScreen().camera.position.y - touchY + y < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y - touchY
							+ y > +640 - text.height) {
				controller.getCurrentScreen().camera.position.y -= touchY - y;

				// Render the screen again to avoid blinking.
				this.render(1 / BattleScreen.FPS);

			}
			this.touchY = y;
			return true;

		}

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		this.nextScreen();
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
