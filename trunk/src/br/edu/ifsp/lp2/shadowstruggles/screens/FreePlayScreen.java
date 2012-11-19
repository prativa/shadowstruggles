package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.games.BattleTest;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class FreePlayScreen extends BaseScreen {
	private Image background;
	private ShadowStruggles game;
	private TextButton test;
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

		test = new TextButton("Fase teste", getSkin());
		test = ScreenUtils.initButton(test, 100, 300, 300, 100);
		test.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				Assets.buttonSound8.play(1);
				game.setScreenWithTransition(new BattleTest(game));

			}
		});

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

		stage.addActor(background);
		stage.addActor(returnButton);
		stage.addActor(test);
	}

}
