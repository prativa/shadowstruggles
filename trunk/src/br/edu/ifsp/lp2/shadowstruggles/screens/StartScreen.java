package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.lp2.shadowstruggles.model.Profile;
import br.edu.ifsp.lp2.shadowstruggles.model.Scene;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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

		background = new Image(new TextureRegion(Assets.mainBackground, 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);

		texture = new Texture(
				Gdx.files.internal("data/images/controls/botao.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		continueGame = new TextButton(
				game.getManager().getMenuText().continueGame, super.getSkin());
		continueGame = ScreenUtils.initButton(continueGame, 240, 384, 480, 215);
		continueGame.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				Assets.buttonSound1.play(1);
				game.setScreenWithTransition(new SelectProfileScreen(game,
						controller));
			}
		});

		newGame = new TextButton(game.getManager().getMenuText().newGame,
				super.getSkin());
		newGame = ScreenUtils.initButton(newGame, 240, 128, 480, 215);
		newGame.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				try {
					Array<Profile> profiles = ProfileDAO.getProfiles();
					ObjectMap<Integer, Profile> prof = new ObjectMap<Integer, Profile>();
					for (Profile profile : profiles) {
						prof.put(profile.getId(), profile);
					}
					int id;
					for (id = 1; id < 100; id++) {
						if (prof.get(id) == null)
							break;
					}
					Profile newProfile = new Profile(id, Scene.FIRST_SCENE,
							"en_us", 0, 0, "");
					ProfileDAO.createProfile(newProfile);
					game.setProfile(newProfile);
				} catch (Exception e) {
					Profile newProfile = new Profile(1, Scene.FIRST_SCENE,
							"en_us", 0, 0, "");
					ProfileDAO.createProfile(newProfile);
					game.setProfile(newProfile);
				}
				Assets.buttonSound1.play(1);
				game.setScreenWithTransition(new MainScreen(game, controller));

			}
		});

		stage.addActor(background);
		stage.addActor(continueGame);
		stage.addActor(newGame);

	}

}
