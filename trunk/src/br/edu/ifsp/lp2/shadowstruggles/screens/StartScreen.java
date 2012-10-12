package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.Profile;
import br.edu.ifsp.lp2.shadowstruggles.Scene;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.ProfileDAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class StartScreen extends BaseScreen {

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
		texture = new Texture(
				Gdx.files.internal("data/images/controls/botao.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		continueGame = new TextButton(game.getManager().getMenuText().continueGame, super.getSkin());
		continueGame.width = 960 / 4 * 2;
		continueGame.height = 640 / 3;
		continueGame.x = 960 / 4;
		continueGame.y = (640 / 5) * 3;
		continueGame.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {

				game.setScreen(new SelectProfileScreen(game, controller));
			}
		});

		newGame = new TextButton(game.getManager().getMenuText().newGame, super.getSkin());
		newGame.width = 960 / 4 * 2;
		newGame.height = 640 / 3;
		newGame.x = 960 / 4;
		newGame.y = (640 / 5);
		newGame.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
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
				ProfileDAO.createProfile(new Profile(id, Scene.FIRST_SCENE));
				game.setScreen(game.getMainScreen());

			}
		});
		stage.addActor(continueGame);
		stage.addActor(newGame);

	}

}
