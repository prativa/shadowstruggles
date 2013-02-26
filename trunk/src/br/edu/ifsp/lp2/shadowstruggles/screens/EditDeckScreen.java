package br.edu.ifsp.lp2.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Deck;
import br.edu.ifsp.lp2.shadowstruggles.model.Shop;
import br.edu.ifsp.lp2.shadowstruggles.object2d.TransitionControl;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.ScreenUtils;

public class EditDeckScreen extends BaseScreen{
	private BaseScreen previousScreen;		
	
	private Image background;
	private Image box;
	private Label name;
	private Label description;
	private Label decks;
	private TransitionControl right;
	private TransitionControl left;
	private TextButton exit;
	private TextButton moveCard;	
	private Card selectedCard;
	private Deck selectedDeck;
	private Array<Image> cardImages;
	private Array<Card> trunk;
	
	
	public EditDeckScreen(ShadowStruggles game, Controller controller, BaseScreen previousScreen) {
		super(game,controller);
		this.previousScreen=previousScreen;		
		initComponents();
		
	}
	
	private void initComponents() {
		final BaseScreen menu = this.previousScreen;
		this.selectedDeck=game.getProfile().getDeck(game.getManager());
		this.trunk=game.getProfile().getTrunk();
		background = new Image(new TextureRegion(game.getAssets().get(
				"data/images/objects/msbackground.png", Texture.class), 512, 380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);

		name = new Label(super.getSkin());
		name.x = 410;
		name.width = 500;
		name.height = 50;
		name.setWrap(true);
		name.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		description = new Label(super.getSkin());
		description.x = 410;
		description.width = 500;
		description.setWrap(true);
		description.setStyle(new LabelStyle(super.getSkin().getFont(
				"andalus-font"), Color.BLACK));
		
		decks= new Label(super.getSkin());
		decks.setText("Decks:");
		decks.x=30;
		decks.y=590;
		decks.width=200;
		decks.height=50;
		decks.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
						Color.BLACK));	
		

		box = new Image(new Texture(
				Gdx.files.internal("data/images/objects/box.png")));
		box.width = 780;
		box.height = 600;
		box.x = 180;
		box.y = 177;
		box.scaleX = 0.9f;
		box.scaleY = 0.76f;

		right = new TransitionControl(1, game);
		right.y = 20;
		right.x = 900;
		right.scaleY = 4f;
		right.scaleX = 1.5f;
		right.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(1);

			}
		});
		left = new TransitionControl(-1, game);
		left.y = 20;
		left.x = 120;
		left.scaleY = 4f;
		left.scaleX = 1.5f;
		left.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(-1);

			}
		});

		exit = new TextButton("M", getSkin());
		exit = ScreenUtils.initButton(exit, 15, 15, 80, 80, super.getSkin());
		exit.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(menu);
				game.getManager().writeProfile(game.getProfile());
			}
		});
		
		moveCard = new TextButton("Move", getSkin());//TODO: string Move p/ arquivo
		moveCard = ScreenUtils.initButton(moveCard, 15, 180, 170, 70, super.getSkin());
		moveCard.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				game.getAudio().playSound("button_6");
				
			}
		});

		stage.addActor(background);
		stage.addActor(box);
		stage.addActor(name);
		stage.addActor(description);
		stage.addActor(exit);
		stage.addActor(right);
		stage.addActor(decks);		
		int count = 0;
		cardImages = new Array<Image>();
		for (Card card : trunk) {
			Image cardImage = new Image(new TextureRegion(game.getAssets().get(
					"data/images/sprites/" + card.getName() + "/card.png",
					Texture.class), 360, 480));
			cardImage.y = 5;
			cardImage.x = 180 + count * 120;
			cardImage.scaleX = 0.3f;
			cardImage.scaleY = 0.3f;
			final Card card2 = card;
			cardImage.setClickListener(new ClickListener() {

				@Override
				public void click(Actor actor, float x, float y) {
					game.getAudio().playSound("button_2");
					changeCard(card2);
				}
			});
			cardImages.add(cardImage);
			if (cardImage.x >= 180 && cardImage.x < 900)
				stage.addActor(cardImage);
			count++;
		}		
		stage.addActor(right);
		stage.addActor(left);
		stage.addActor(moveCard);
	}

	private void moveCards(int side) {
		boolean movableToRight = false, movableToLeft = false;
		for (Image card : cardImages) {
			if (card.x < 180)
				movableToRight = true;
			if (card.x > 780)
				movableToLeft = true;
		}

		if ((side > 0 && movableToLeft) || (side < 0 && movableToRight))
			for (Image card : cardImages) {
				card.x -= 120 * side;
				if (card.x >= 120 && card.x < 900)
					stage.addActor(card);
				else {
					try {
						stage.removeActor(card);

					} catch (Exception e) {
					}
				}
			}
		for (Image card : cardImages) {
			System.out.println(card.name + " - " + card.x);
		}

	}

	private void changeCard(Card card) {
		this.selectedCard = card;
		
		
	}
}
