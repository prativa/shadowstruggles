package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.lp2.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.lp2.shadowstruggles.games.Practice;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PosGameScreen extends BaseScreen {

	private Image background;
	private Label text;
	private TextButton continueButton;
	private TextButton mainMenu;
	private BattleScreen battleScreen;

	/**
	 * Informs if the player has accessed the battle through the campaign or,
	 * otherwise, free play. If he has accessed it through free play, it doesn't
	 * show the option to continue and doesn't advance the plot.
	 */
	private boolean isInCampaign;

	public PosGameScreen(ShadowStruggles game, Controller controller,
			String message, boolean isInCampaign) {
		super(game, controller);
		this.isInCampaign = isInCampaign;
		initComponents(message);
	}

	public PosGameScreen(ShadowStruggles game, Controller controller,
			String message, BattleScreen battleScreen, boolean isInCampaign) {
		super(game, controller);
		this.isInCampaign = isInCampaign;
		this.battleScreen = battleScreen;
		initComponents(message);

	}

	public void initComponents(String message) {
		background = new Image(new TextureRegion(game.getAssets().get("data/images/objects/msbackground.png", Texture.class), 512,
				380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);

		mainMenu = new TextButton(
				game.getManager().getMenuText().mainMenuButton, super.getSkin());
		mainMenu = ScreenUtils.initButton(mainMenu, 500, 100, game.getManager()
				.getMenuText().mainMenuButton.length() * 32, 100, super.getSkin());
		mainMenu.setClip(true);
		mainMenu.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new MainScreen(game, controller));
			}

		});
		this.text = new Label(message, super.getSkin());
		text.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.YELLOW));
		text.x = (960 - text.getPrefWidth()) / 2;
		text.y = 300;

		this.getStage().addActor(background);
		this.getStage().addActor(text);
		this.getStage().addActor(mainMenu);

		if (isInCampaign) {
			continueButton = new TextButton(
					game.getManager().getMenuText().continueButton, super.getSkin());
			continueButton = ScreenUtils.initButton(continueButton, 100, 100, 300,
					100, super.getSkin());
			continueButton.setClickListener(new ClickListener() {

				@Override
				public void click(Actor actor, float x, float y) {
					game.getAudio().playSound("button_4");
					game.setScreenWithTransition(new SceneScreen(game, controller));

				}
			});
			
			this.getStage().addActor(continueButton);
			
			//TODO: Perguntar ao jogador se ele gostaria de salvar ao invés de save automático.
			
			// Moves the plot forward to the next scene.
			game.getProfile().setCurrentScene(
					SceneDAO.getScene(game.getProfile().getCurrentScene()
							.getNextId(), game.getManager()));
			//ProfileDAO.createProfile(game.getProfile(), game.getManager());

			// Adds the battles to the array of fought battles.
			if (battleScreen.getClass() == Practice.class) {
				if (!game.getProfile().getBattlesFought().contains(1f, true)) {
					game.getProfile().getBattlesFought().add(1f);
					//game.getManager().writeProfile(game.getProfile());
				}
			}
		}
	}
}
