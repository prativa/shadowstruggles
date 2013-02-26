package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.lp2.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.lp2.shadowstruggles.model.Profile;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class SaveLoadScreen extends BaseScreen implements InputProcessor {
	private Image background;
	private TextButton returnButton;
	private Array<TextButton> slots;

	private int touchY;
	private boolean touched;
	private boolean justTouched;

	private InputMultiplexer inputSources;
	private boolean saveMode;

	/**
	 * Indicates which screen the return button should reference: "start" - A
	 * StartScreen; "main" - A MainScreen; "scene" - A SceneScreen.
	 */
	private String returnScreen;

	public SaveLoadScreen(ShadowStruggles game, Controller controller,
			String returnScreen, boolean saveMode) {
		super(game, controller);

		this.returnScreen = returnScreen;
		this.saveMode = saveMode;
		this.slots = new Array<TextButton>();
		this.inputSources = new InputMultiplexer();

		initComponents();
	}

	private void initComponents() {

		background = new Image(new TextureRegion(game.getAssets().get(
				"data/images/objects/msbackground.png", Texture.class), 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);

		returnButton = new TextButton(
				game.getManager().getMenuText().returnToStart, super.getSkin());
		returnButton = ScreenUtils.initButton(returnButton, 10, 530, 220, 90,
				super.getSkin());
		returnButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().playSound("button_6");
				if (returnScreen.toLowerCase().equals("start"))
					game.setScreenWithTransition(new StartScreen(game,
							controller));
				else if (returnScreen.toLowerCase().equals("main"))
					game.setScreenWithTransition(new MainScreen(game,
							controller));
				else if (returnScreen.toLowerCase().equals("scene"))
					game.setScreenWithTransition(new SceneScreen(game,
							controller));
			}
		});

		stage.addActor(background);
		stage.addActor(returnButton);

		if (game.getManager().profileExists()) {
			Array<Profile> profiles = ProfileDAO.getProfiles(game.getManager());

			for (Profile profile : profiles) {
				String text = String.valueOf(profile.getId()) + " - "
						+ profile.getCurrentScene().getName();
				TextButton textButton = new TextButton(text, super.getSkin());
				textButton = ScreenUtils.initButton(textButton, 240,
						630 - profile.getId() * 100, text.length() * 30, 90,
						super.getSkin());
				textButton.setClip(true);
				textButton.setClickListener(new ClickListener() {

					@Override
					public void click(Actor actor, float x, float y) {
						game.getAudio().playSound("button_2");
						
						if (saveMode) {
							TextButton tx = (TextButton) actor;
							game.getProfile().setId(Character.getNumericValue(tx.getText()
									.charAt(0)));
							ProfileDAO.createProfile(game.getProfile(), game.getManager());
							game.setScreenWithTransition(new SaveLoadScreen(game, controller, returnScreen, true));
						} else {
							TextButton tx = (TextButton) actor;
							int id = Character.getNumericValue(tx.getText()
									.charAt(0));
							game.setProfile(ProfileDAO.getProfile(id, game.getManager()));
							game.getManager().changeLanguage(
									ProfileDAO.getProfile(id, game.getManager()).getLanguage());
							game.getProfile().setCurrentScene(
									SceneDAO.getScene(game.getProfile()
											.getCurrentScene().getId(),
											game.getManager()));
							game.setScreenWithTransition(new MainScreen(game,
									controller));
						}
					}
				});

				this.slots.add(textButton);
				stage.addActor(textButton);
			}
		}

		if (this.saveMode) {
			String text = "Empty slot";
			TextButton textButton = new TextButton(text, super.getSkin());

			float lastY = 530;
			if (slots.size > 0)
				lastY = slots.peek().y;
			textButton = ScreenUtils.initButton(textButton, 240, lastY - 100,
					text.length() * 30, 90, super.getSkin());

			textButton.setClip(true);

			textButton.setClickListener(new ClickListener() {

				@Override
				public void click(Actor actor, float x, float y) {
					game.getAudio().playSound("button_2");

					try {
						Array<Profile> profiles = ProfileDAO.getProfiles(game.getManager());
						ObjectMap<Integer, Profile> prof = new ObjectMap<Integer, Profile>();

						for (Profile profile : profiles) {
							prof.put(profile.getId(), profile);
						}

						int id;
						for (id = 1; id < 100; id++) {
							if (prof.get(id) == null)
								break;
						}

						Profile newProfile = new Profile(id);
						ProfileDAO.createProfile(newProfile, game.getManager());
						game.setProfile(newProfile);
						game.setScreenWithTransition(new SaveLoadScreen(game,
								controller, returnScreen, true));
					} catch (Exception e) {
						Profile newProfile = new Profile();
						ProfileDAO.createProfile(newProfile, game.getManager());
						game.setProfile(newProfile);
						game.setScreenWithTransition(new SaveLoadScreen(game,
								controller, returnScreen, true));
					}

				}

			});

			this.slots.add(textButton);
			stage.addActor(textButton);
		}

		inputSources.addProcessor(this.stage);
		inputSources.addProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		Gdx.input.setInputProcessor(inputSources);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		background.y = this.camera.position.y - CAMERA_INITIAL_Y;
		returnButton.y = this.camera.position.y - CAMERA_INITIAL_Y + 530;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.DOWN) {
			if (controller.getCurrentScreen().camera.position.y - 4 < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y - 4 > slots
							.peek().height) {
				controller.getCurrentScreen().camera.position.y -= 4;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			return true;
		}

		if (keycode == Keys.UP) {
			if (controller.getCurrentScreen().camera.position.y + 4 < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y + 4 > slots
							.peek().height) {
				controller.getCurrentScreen().camera.position.y += 4;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			return true;
		}
		return false;
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
							+ y > slots.peek().height) {
				controller.getCurrentScreen().camera.position.y -= touchY - y;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			this.touchY = y;
			return true;

		}

		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

}