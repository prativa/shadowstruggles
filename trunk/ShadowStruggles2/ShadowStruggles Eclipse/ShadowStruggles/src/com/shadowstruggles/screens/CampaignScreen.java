package com.shadowstruggles.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shadostruggles.games.Test;
import com.shadowstruggles.Controller;
import com.shadowstruggles.ShadowStruggles;


public class CampaignScreen extends BaseScreen {
	private ShadowStruggles game;
	private TextButton test;

	public CampaignScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		this.game = game;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	private void initComponents() {
		test = new TextButton("Fase teste", getSkin());
		test.x = 100;
		test.y = 300;
		test.width = 200;
		test.height = 100;
		test.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				game.setScreen(new Test(game));

			}
		});
		stage.addActor(test);
	}

}
