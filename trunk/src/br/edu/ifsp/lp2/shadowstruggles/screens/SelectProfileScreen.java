package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.Profile;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.ProfileDAO;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

public class SelectProfileScreen extends BaseScreen {
	private Array<TextButton> profileButton;
	private TextButton returnButton;

	public SelectProfileScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);

	}

	private void initComponents() {
		Array<Profile> profiles = ProfileDAO.getProfiles();
		this.profileButton = new Array<TextButton>();
		int count = 0;
		returnButton = new TextButton(game.getManager().getMenuText().returnToStart, super.getSkin());
		returnButton.width = 200;
		returnButton.height = 90;
		returnButton.x = 20;
		returnButton.y = 530;
		returnButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.setScreen(new StartScreen(game, controller));
			}
		});
		stage.addActor(returnButton);
		for (Profile profile : profiles) {
			TextButton textButton = new TextButton(game.getManager().getMenuText().profile
					+ String.valueOf(profile.getId()), super.getSkin());
			textButton.width = 480;
			textButton.height = 90;
			textButton.x = 240;
			textButton.y = 530 - count * 100;
			textButton.setClickListener(new ClickListener() {

				@Override
				public void click(Actor actor, float x, float y) {
					TextButton tx = (TextButton) actor;
					int id = Character.getNumericValue(tx.getText().charAt(
							tx.getText().length() - 1));
					game.setProfile(ProfileDAO.getProfile(id));
					game.setScreen(game.getMainScreen());
				}
			});
			profileButton.add(textButton);
			stage.addActor(textButton);
			count++;
		}

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

}
