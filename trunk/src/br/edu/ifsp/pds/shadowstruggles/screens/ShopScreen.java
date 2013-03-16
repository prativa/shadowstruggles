package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Shop;
import br.edu.ifsp.pds.shadowstruggles.object2d.TransitionControl;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class ShopScreen extends BaseScreen {
	
	private BaseScreen previousScreen;	
	private Shop shop;	
	private Image selectedImage;
	private Image background;
	private Image box;
	private Label name;
	private Label description;
	private Label moneyLabel;
	private TransitionControl right;
	private TransitionControl left;
	private TextButton exit;
	private TextButton buyButton;	
	private Card selectedCard;
	private Array<Card> cards;
	private Array<Image> cardImages;
	
	
	

	public ShopScreen(ShadowStruggles game, Controller controller,
			BaseScreen previousScreen) {
		super(game, controller);
		this.previousScreen = previousScreen;
		this.shop= new Shop(game);
		initComponents();
	}

	private void initComponents() {
		final BaseScreen menu = this.previousScreen;
		background = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY((640f / 380f));

		name = new Label("",super.getSkin());
		name.setX(410);
		name.setWidth(500);
		name.setHeight(50);
		name.setWrap(true);
		name.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		description = new Label("" , super.getSkin());
		description.setX(410);
		description.setWidth(500);
		description.setWrap(true);
		description.setStyle(new LabelStyle(super.getSkin().getFont(
				"andalus-font"), Color.BLACK));
		
		moneyLabel= new Label("",super.getSkin());
		moneyLabel.setText("Money: $ "+String.valueOf(shop.getPlayerMoney()));//TODO: string Money para arquivo
		moneyLabel.setX(30);
		moneyLabel.setY(590);
		moneyLabel.setWidth(200);
		moneyLabel.setHeight(50);
		moneyLabel.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
						Color.BLACK));	
		

		box = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("box"));
		box.setWidth(600);
		box.setHeight(600);
		box.setX(390);
		box.setY(177);
		box.setScaleX(0.9f);
		box.setScaleY(0.76f);

		right = new TransitionControl(1, this.getSkin());
		right.setY(20);
		right.setX(900);
		right.setScaleY(4f);
		right.setScaleX(1.5f);
		right.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(1);
			}
		});
		
		left = new TransitionControl(-1, this.getSkin());
		left.setY(20);
		left.setX(120);
		left.setScaleY(4f);
		left.setScaleX(1.5f);
		left.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(-1);

			}
		});

		exit = new TextButton("M", getSkin());
		exit = ScreenUtils.defineButton(exit, 15, 15, 80, 80, super.getSkin());
		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(menu);
				game.getManager().writeProfile(game.getProfile());
			}
		});
		
		buyButton = new TextButton("Buy", getSkin());//TODO: string Buy p/ arquivo
		buyButton = ScreenUtils.defineButton(buyButton, 70, 180, 288, 70, super.getSkin());
		buyButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				shop.buyCard(selectedCard);
				moneyLabel.setText("Money: $ "+String.valueOf(shop.getPlayerMoney()));//TODO: string Money para arquivo
			}
		});

		stage.addActor(background);
		stage.addActor(box);
		stage.addActor(name);
		stage.addActor(description);
		stage.addActor(exit);
		stage.addActor(right);
		stage.addActor(moneyLabel);
		cards = shop.getAvailableCards();
		int count = 0;
		cardImages = new Array<Image>();
		for (Card card : cards) {
			Image cardImage = new Image(new TextureRegion(game.getAssets().get(
					"data/images/sprites/" + card.getName() + "/card.png",
					Texture.class), 360, 480));
			cardImage.setY(5);
			cardImage.setX(180 + count * 120);
			cardImage.setScaleX(0.3f);
			cardImage.setScaleY(0.3f);
			final Card card2 = card;
			cardImage.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.getAudio().playSound("button_2");
					changeCard(card2);
				}
			});
			cardImages.add(cardImage);
			if (cardImage.getX() >= 180 && cardImage.getX() < 900)
				stage.addActor(cardImage);
			count++;
		}
		changeCard(cards.get(0));
		stage.addActor(right);
		stage.addActor(left);
		stage.addActor(buyButton);
	}

	private void moveCards(int side) {
		boolean movableToRight = false, movableToLeft = false;
		for (Image card : cardImages) {
			if (card.getX() < 180)
				movableToRight = true;
			if (card.getX() > 780)
				movableToLeft = true;
		}

		if ((side > 0 && movableToLeft) || (side < 0 && movableToRight))
			for (Image card : cardImages) {
				card.setX( card.getX()-120 * side);
				if (card.getX() >= 120 && card.getX() < 900)
					stage.addActor(card);
				else {
					try {
						stage.removeActor(card);

					} catch (Exception e) {
					}
				}
			}

	}

	private void changeCard(Card card) {
		this.selectedCard = card;
		description.setText(card.getDescription());
		description.setHeight(description.getPrefHeight());
		description.setY(590 - description.getHeight());
		try {
			stage.removeActor(selectedImage);
		} catch (Exception e) {
		}
		selectedImage = new Image(new TextureRegion(game.getAssets().get(
				"data/images/sprites/" + card.getName() + "/card.png",
				Texture.class), 360, 480));
		selectedImage.setX(75);
		selectedImage.setY(256);
		selectedImage.setScaleX(0.7f);
		selectedImage.setScaleY(0.7f);
		name.setText(selectedCard.getNameVisualization());
		name.setY(590);
		stage.addActor(selectedImage);
		buyButton.setText("Buy ($ "+card.getBuyCost()+")");
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 200);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}
}
