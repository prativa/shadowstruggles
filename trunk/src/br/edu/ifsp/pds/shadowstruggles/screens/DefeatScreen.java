package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DefeatScreen extends BaseScreen {

	private Image background;
	private Label text;
	private TextButton retryButton;
	private TextButton mainMenu;
	private BattleScreen battleScreen;

	public DefeatScreen(ShadowStruggles game, Controller controller,
			String message) {
		super(game, controller);
		initComponents(message);
	}

	public DefeatScreen(ShadowStruggles game, Controller controller,
			String message, BattleScreen battleScreen) {
		super(game, controller);
		initComponents(message);
		this.battleScreen=battleScreen;
	}

	public void initComponents(String message) {
		background = new Image(new TextureRegion(game.getAssets().get("data/images/objects/msbackground.png", Texture.class), 512,
				380));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		retryButton = new TextButton(
				game.getManager().getMenuText().retryButton, super.getSkin());
		retryButton = ScreenUtils.defineButton(retryButton, 100, 100, 300,
				100, super.getSkin());
		retryButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition((battleScreen).copy());
			}
		});
		mainMenu = new TextButton(
				game.getManager().getMenuText().mainMenuButton, super.getSkin());
		mainMenu = ScreenUtils.defineButton(mainMenu, 500, 100, game.getManager()
				.getMenuText().mainMenuButton.length() * 32, 100, super.getSkin());
		mainMenu.setClip(true);
		mainMenu.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new MainScreen(game, controller));
			}

		});
		this.text = new Label(message, super.getSkin());
		text.setStyle(new LabelStyle(super.getSkin().getFont("gg-font"),
				Color.YELLOW));
		
		text.setX((960 - text.getPrefWidth()) / 2);
		text.setY(400);

		this.getStage().addActor(background);
		this.getStage().addActor(text);
		this.getStage().addActor(retryButton);
		this.getStage().addActor(mainMenu);

	}
}
