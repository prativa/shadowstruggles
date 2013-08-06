package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.object2d.Arrow;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class EditDeckScreen extends BaseScreen {
	private BaseScreen previousScreen;

	private Image background;
	private Image box;
	private Label name;
	private Label description;
	private Label deckName;
	private Label availableCards;
	private Arrow right;
	private Arrow left;
	private TextButton exit;
	private TextButton newDeck;
	private TextButton moveCard;
	private Card selectedCard;
	private Deck selectedDeck;
	private Array<Image> cardImages;
	private Array<Card> trunk;
	private Array<Deck> playerDecks;
	private static EditDeckScreen instance;
	private SelectBox decks;

	public static EditDeckScreen getInstance(ShadowStruggles game,
			Controller controller, BaseScreen previousScreen) {
		if (instance != null)
			return instance;
		else {
			instance = new EditDeckScreen(game, controller, previousScreen);
			return instance;
		}
	}

	private EditDeckScreen(ShadowStruggles game, Controller controller,
			BaseScreen previousScreen) {
		super(game, controller);
		this.previousScreen = previousScreen;
	}

	public void setPreviousScreen(BaseScreen previousScreen) {
		this.previousScreen = previousScreen;
	}

	public void initComponents() {
		stage.clear();

		final BaseScreen menu = this.previousScreen;
		this.selectedDeck = game.getProfile().getSelectedDeck();
		this.trunk = new Array<Card>();
		for (Item i : game.getProfile().getInventory()) {
			if (i instanceof Card) {
				this.trunk.add((Card) i);
			}
		}

		Table menuTable = new Table();
		menuTable.defaults().padTop(10).width(160).height(50);

		if (game.getMode() == RunMode.DEBUG)
			menuTable.debug();

		deckName = new Label("", super.getSkin());
		deckName.setText("Select a Deck");
		deckName.setStyle(new LabelStyle(super.getSkin()
				.getFont("andalus-font"), Color.WHITE));

		newDeck = new TextButton(MenuTextDAO.getMenuText().newDeck,
				super.getSkin());
		newDeck = ScreenUtils
				.defineButton(newDeck, 0, 0, 0, 0, super.getSkin());
		newDeck.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				createDeck();
			}
		});

		addDecks();
		decks = new SelectBox(new String[] { "Deck A", "Deck B", "Deck C" },
				super.getSkin());

		exit = new TextButton(MenuTextDAO.getMenuText().returnToStart,
				super.getSkin());
		exit = ScreenUtils.defineButton(exit, 0, 0, 0, 0, super.getSkin());
		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(menu);
				DataManager.getInstance().writeProfile(game.getProfile());
			}
		});

		menuTable.add(deckName);
		menuTable.row();
		menuTable.add(decks);
		menuTable.row();
		menuTable.add().height(320);
		menuTable.row();
		menuTable.add(newDeck);
		menuTable.row();
		menuTable.add(exit);

		menuTable.setPosition(100, 300);

		Table deckTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			deckTable.debug();
		box = new Image(game.getTextureRegion("box", "game_ui_images"));
		deckTable.defaults().width(600).height(400);
		box.setWidth(600);
		box.setHeight(400);

		deckTable.add(box);

		deckTable.setPosition(580, 380);

		Table leftButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			leftButtonTable.debug();

		leftButtonTable.defaults().width(100).height(100);

		availableCards = new Label("", super.getSkin());
		availableCards.setText("Available Cards:");
		availableCards.setStyle(new LabelStyle(super.getSkin().getFont(
				"andalus-font"), Color.WHITE));

		left = new Arrow(-1, this.getSkin());
		left.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(-1);

			}
		});

		Table rightButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			rightButtonTable.debug();

		rightButtonTable.defaults().width(100).height(100);

		right = new Arrow(1, this.getSkin());
		right.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(1);

			}
		});

		int count = 0;
		cardImages = new Array<Image>();
		for (Card card : trunk) {
			Image cardImage = new Image(game.getAssets()
					.get("data/images/cards/cards.atlas", TextureAtlas.class)
					.findRegion(card.getName().toLowerCase()));
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

		leftButtonTable.add(availableCards).height(50);
		leftButtonTable.row();
		leftButtonTable.add(left).left();
		rightButtonTable.add(right);

		leftButtonTable.setPosition(250, 105);
		rightButtonTable.setPosition(900, 80);

		stage.addActor(menuTable);
		stage.addActor(deckTable);
		stage.addActor(leftButtonTable);
		stage.addActor(rightButtonTable);

	}

	@Override
	public Array<Asset> texturesToLoad() {
		Array<Asset> assets = new Array<Asset>();
		// Array for keeping track of cards, making sure that there are no
		// duplicates.
		Array<String> previousCards = new Array<String>();

		for (Card c : trunk) {
			if (!previousCards.contains(c.getName(), false)) {
				assets.add(new Asset(c.getName() + ".png", "cards"));
				previousCards.add(c.getName());
			}
		}
		for (Deck d : playerDecks) {
			for (Card c : d.getCards()) {
				if (!previousCards.contains(c.getName(), false)) {
					assets.add(new Asset(c.getName() + ".png", "cards"));
					previousCards.add(c.getName());
				}
			}
		}

		return assets;
	}

	private void createDeck() {
		Deck newDeck = new Deck();
		// TODO: implementar criacao de deck
		// (criar deck com a letra seguinte e mostrar na lista)
	}

	private void addDecks() {

		// TODO adicionar decks do usuario na lista da esquerda
		// (pegar a Array de decks do profile e listar na lista da esquerda)
		// (carregar Decks em que o Owner Id seja o mesmo que o Id do profile)

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
				card.setX(card.getX() - 120 * side);
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

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(stage);
	}
}
