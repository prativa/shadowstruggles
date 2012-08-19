package com.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shadowstruggles.Controller;
import com.shadowstruggles.ShadowStruggles;


public class StartScreen extends BaseScreen{
	
	private Texture texture;	
	private TextButton continueGame;
	private TextButton newGame;
	
	public StartScreen(ShadowStruggles game, Controller controller) {	
		super(game, controller);		
	}	
	
	
	@Override
	public void resize(int width, int height) {		
		super.resize(width, height);		
		initComponents();
	}
	
	public void initComponents(){
		stage.clear();		
		texture=new Texture(Gdx.files.internal("data/images/controls/botao.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		continueGame = new TextButton("Continuar",super.getSkin());
		continueGame.width = width/4*2;
		continueGame.height = height/3;			
		continueGame.x=width/4;
		continueGame.y=(height/5)*3;
		continueGame.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor arg0, float arg1, float arg2) {				
				game.setScreen(game.getPrfl());
			}
		});
		newGame = new TextButton("Novo jogo",super.getSkin());
		newGame.width = width/4*2;
		newGame.height = height/3;			  
		newGame.x=width/4;
		newGame.y=(height/5);
		newGame.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor arg0, float arg1, float arg2) {				
				game.setScreen(game.getStrt());
			}
		});       
        stage.addActor( continueGame );
        stage.addActor(newGame);
		
	}	
	

}
