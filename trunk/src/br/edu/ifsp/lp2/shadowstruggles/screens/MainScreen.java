package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainScreen extends BaseScreen {

	private TextButton campaign;
	private TextButton freePlay;
	private TextButton deck;
	private TextButton shop;
	private TextButton config;
	private TextButton help;
	private TextButton but;

	public MainScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	private void initComponents() {

		campaign = new TextButton("Campaign", super.getSkin());
		campaign.width = 480;
		campaign.height = 106;
		campaign.x = 240;
		campaign.y = 512;
		campaign.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.setScreen(new CampaignScreen(game, game.getController()));
			}
		});
		freePlay = new TextButton("Free Play", super.getSkin());
		freePlay.width = 960 / 4 * 2;
		freePlay.height = 640 / 6;
		freePlay.x = 960 / 4;
		freePlay.y = (640 / 5) * 3;
		freePlay.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				System.out.println("freePlay");
			}
		});
		deck = new TextButton("My Deck", super.getSkin());
		deck.width = 960 / 2;
		deck.height = 640 / 6;
		deck.x = 960 / 4;
		deck.y = 640 / 5 * 2;
		deck.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				System.out.println("deck");
			}
		});
		shop = new TextButton("Shop", super.getSkin());
		shop.width = 960 / 2;
		shop.height = 640 / 6;
		shop.x = 960 / 4;
		shop.y = 640 / 5;
		shop.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				System.out.println("shop");
			}
		});
		stage.addActor(deck);
		stage.addActor(shop);
		stage.addActor(campaign);
		stage.addActor(freePlay);
	}

	@Override
	public void show() {
		super.show();
	}
}
