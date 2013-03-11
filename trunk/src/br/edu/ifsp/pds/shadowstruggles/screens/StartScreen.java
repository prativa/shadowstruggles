package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class StartScreen extends BaseScreen {

	private Image background;
	private Texture texture;
	private TextButton continueGame;
	private TextButton newGame;

	public StartScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	public void initComponents() {
		stage.clear();

		background = new Image(new TextureRegion(game.getAssets().get(
				"data/images/objects/msbackground.png", Texture.class), 512,
				380));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		texture = new Texture(
				Gdx.files.internal("data/images/controls/button.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		continueGame = new TextButton(
				game.getManager().getMenuText().continueGame, super.getSkin());
		continueGame = ScreenUtils.defineButton(continueGame, 240, 384, 480, 215,
				super.getSkin());
		continueGame.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float arg1, float arg2) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new SaveLoadScreen(game,
						controller, "start", false));
			}
		});

		newGame = new TextButton(game.getManager().getMenuText().newGame,
				super.getSkin());
		newGame = ScreenUtils.defineButton(newGame, 240, 128, 480, 215,
				super.getSkin());
		
		newGame.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float arg1, float arg2) {
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
					game.getAudio().playSound("button_4");
					game.setScreenWithTransition(new MainScreen(game,
							controller));
				} catch (Exception e) {
					Profile newProfile = new Profile();
					ProfileDAO.createProfile(newProfile, game.getManager());
					game.setProfile(newProfile);
					game.getAudio().playSound("button_4");
					game.setScreenWithTransition(new MainScreen(game,
							controller));
				}

			}
		});

		stage.addActor(background);
		stage.addActor(continueGame);
		stage.addActor(newGame);

	}

}
