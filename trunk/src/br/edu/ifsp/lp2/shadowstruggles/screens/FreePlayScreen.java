package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

public class FreePlayScreen extends BaseScreen {
	private Image background;
	private ShadowStruggles game;
	private Array<TextButton> battles;
	private TextButton returnButton;

	public FreePlayScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		this.game = game;
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	private void initComponents() {
		background = new Image(new TextureRegion(Assets.mainBackground, 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);
		stage.addActor(background);

		battles = new Array<TextButton>();

		for (int id : game.getProfile().getBattlesFought()) {
			TextButton button = new TextButton(getSkin());
			final BattleScreen battle = null;

			switch (id) {
			case -1:
				button.setText(game.getManager().getMenuText().tutorialBattle);
				button = ScreenUtils.initButton(button, 100, 300, 300, 100);
				break;
			}

			button.setClickListener(new ClickListener() {

				@Override
				public void click(Actor arg0, float arg1, float arg2) {
					game.setScreenWithTransition(battle);
				}

			});
			
			stage.addActor(button);
			battles.add(button);
		}

		returnButton = new TextButton(
				game.getManager().getMenuText().returnToStart, super.getSkin());
		returnButton = ScreenUtils.initButton(returnButton, 100, 100, 250, 100);
		returnButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				Assets.buttonSound7.play(1);
				game.setScreenWithTransition(new MainScreen(game, controller));

			}
		});

		stage.addActor(returnButton);
	}
}
