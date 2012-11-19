package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.lp2.shadowstruggles.model.Profile;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

//TODO: implementar inputProcessor para ver outros perfis

public class SelectProfileScreen extends BaseScreen {
	private Image background;
	private TextButton returnButton;

	// private Group group;
	// private ScrollPane scrollPane;

	public SelectProfileScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();

	}

	private void initComponents() {

		background = new Image(new TextureRegion(Assets.mainBackground, 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);

		returnButton = new TextButton(
				game.getManager().getMenuText().returnToStart, super.getSkin());
		returnButton = ScreenUtils.initButton(returnButton, 20, 530, 200, 90);
		returnButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				Assets.buttonSound2.play(1);
				game.setScreenWithTransition(new StartScreen(game, controller));
			}
		});

		stage.addActor(background);
		stage.addActor(returnButton);
		// group.addActor(background);
		// group.addActor(returnButton);

		if (game.getManager().profileExists()) {
			int count = 0;
			Array<Profile> profiles = ProfileDAO.getProfiles();

			for (Profile profile : profiles) {
				String text = String.valueOf(profile.getId()) + " - "
						+ profile.getCurrentScene().getName();
				TextButton textButton = new TextButton(text, super.getSkin());
				textButton = ScreenUtils.initButton(textButton, 240,
						530 - count * 100, text.length() * 30, 90);
				textButton.setClip(true);
				textButton.setClickListener(new ClickListener() {

					@Override
					public void click(Actor actor, float x, float y) {
						Assets.buttonSound2.play(1);
						TextButton tx = (TextButton) actor;
						int id = Character.getNumericValue(tx.getText().charAt(
								0));
						game.setProfile(ProfileDAO.getProfile(id));
						game.getManager().changeLanguage(
								ProfileDAO.getProfile(id).getLanguage());
						game.setScreenWithTransition(new MainScreen(game,
								controller));
					}
				});
				stage.addActor(textButton);
				count++;
			}
		}

		// scrollPane.setWidget(group);
		// stage.addActor(scrollPane);

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

}
