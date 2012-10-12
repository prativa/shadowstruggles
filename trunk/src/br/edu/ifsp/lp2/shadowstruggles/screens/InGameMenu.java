package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;


public class InGameMenu extends BaseScreen{
	
	private ImageButton returnToGame;
	private ImageButton exitGame;
	private ImageButton config;

	public InGameMenu(ShadowStruggles game, Controller controller) {
		super(game, controller);
		
	}
	
	@Override
	public void render(float delta) {
	}

}
