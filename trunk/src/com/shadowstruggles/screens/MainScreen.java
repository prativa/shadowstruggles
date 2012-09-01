package com.shadowstruggles.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shadowstruggles.Controller;
import com.shadowstruggles.ShadowStruggles;
import com.shadowstruggles.data.DataManager;


public class MainScreen extends BaseScreen {
	private Texture texture;
	private TextButton campaign;
	private TextButton freePlay;
	private TextButton deck;
	private TextButton shop;
	private TextButton config;
	private TextButton help;
	private TextButton but;

	public MainScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}
	
	

	private void initComponents() {
		
		campaign = new TextButton("Campaign", super.getSkin());
		campaign.width = width / 4 * 2;
		campaign.height = height / 6;
		campaign.x = width / 4;
		campaign.y = (height / 5) * 4;
		campaign.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.setScreen(new CampaignScreen(game, game.getController()));
				System.out.println("campaign");
			}
		});
		freePlay = new TextButton("Free Play", super.getSkin());
		freePlay.width = width / 4 * 2;
		freePlay.height = height / 6;
		freePlay.x = width / 4;
		freePlay.y = (height / 5) * 3;
		freePlay.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				System.out.println("freePlay");
			}
		});
		deck = new TextButton("My Deck", super.getSkin());
		deck.width = width / 2;
		deck.height = height / 6;
		deck.x = width / 4;
		deck.y = height / 5 * 2;
		deck.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				System.out.println("deck");
			}
		});
		shop = new TextButton("Shop", super.getSkin());
		shop.width = width / 2;
		shop.height = height / 6;
		shop.x = width / 4;
		shop.y = height / 5;
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
		// TODO Auto-generated method stub
		super.show();
		System.out.println("profile show");
	}
}
