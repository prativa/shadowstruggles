package br.edu.ifsp.lp2.shadowstruggles.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.lp2.shadowstruggles.games.BattleTest;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

public class PosGameScreen extends BaseScreen {

	private Image background;
	private Label text;
	private TextButton continueButton;
	private TextButton mainMenu;
	private BattleScreen bt;

	public PosGameScreen(ShadowStruggles game, Controller controller,
			String message) {
		super(game, controller);
		initComponents(message);
	}

	public PosGameScreen(ShadowStruggles game, Controller controller,
			String message, BattleScreen bt) {
		super(game, controller);
		initComponents(message);
		this.bt = bt;
	}

	public void initComponents(String message) {
		background = new Image(new TextureRegion(Assets.mainBackground, 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);

		continueButton = new TextButton(
				game.getManager().getMenuText().continueButton, super.getSkin());
		continueButton = ScreenUtils.initButton(continueButton, 100, 100, 300,
				100);
		continueButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getProfile().setCurrentScene(
						SceneDAO.getScene(game.getProfile().getCurrentScene()
								.getNextId(), game.getManager()));
				if(bt.getClass() == BattleTest.class) {
					if(!game.getProfile().getBattlesFought().contains(-1, true)) {
						game.getProfile().getBattlesFought().add(-1);
					}
				}
				game.setScreen(new SceneScreen(game, controller));

			}
		});
		mainMenu = new TextButton(
				game.getManager().getMenuText().mainMenuButton, super.getSkin());
		mainMenu = ScreenUtils.initButton(mainMenu, 500, 100, game.getManager()
				.getMenuText().mainMenuButton.length() * 32, 100);
		mainMenu.setClip(true);
		mainMenu.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.setScreenWithTransition(new MainScreen(game, controller));
			}

		});
		this.text = new Label(message, super.getSkin());
		text.setStyle(new LabelStyle(super.getSkin().getFont("default-font"),
				Color.YELLOW));
		text.x = (960 - text.getPrefWidth()) / 2;
		text.y = 300;

		this.getStage().addActor(background);
		this.getStage().addActor(text);
		this.getStage().addActor(continueButton);
		this.getStage().addActor(mainMenu);

	}
}
