package com.shadowstruggles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.shadowstruggles.screens.BattleScreen;
import com.shadowstruggles.screens.MainScreen;
import com.shadowstruggles.screens.StartScreen;

public class ShadowStruggles extends Game{
	private StartScreen strt;
	private MainScreen prfl;
	private BattleScreen teste;
	public static final String LOG = ShadowStruggles.class.getSimpleName();
	private FPSLogger fpsLogger;
	private Controller controller;
	
	@Override
	public void create() {
		Gdx.app.log( ShadowStruggles.LOG, "Creating game" );
        fpsLogger = new FPSLogger();	
        this.controller=new Controller();
		this.strt= new StartScreen(this, controller);
		this.prfl=new MainScreen(this, controller);
		this.setScreen(strt);		
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log( ShadowStruggles.LOG, "Resizing game to: " + width + " x " + height );
	}
	
	
	@Override
	public void render() {
		super.render();		
        fpsLogger.log(); 
        
	}
	
	@Override
	public void setScreen(Screen screen) {
		Gdx.app.log(ShadowStruggles.LOG,"Switching to Screen: "+screen.getClass().getSimpleName());
		super.setScreen(screen);
	}
	public Controller getController() {
		return controller;
	}
	
	@Override
	public void pause() {
		super.pause();
		Gdx.app.log( ShadowStruggles.LOG, "Pausing game" );
	}
	
	
	@Override
	public void resume() {
		super.resume();
		Gdx.app.log( ShadowStruggles.LOG, "Resuming game" );
	}
	
	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.log( ShadowStruggles.LOG, "Disposing game" );
	}
	
	public StartScreen getStrt() {
		return strt;
	}
	public MainScreen getPrfl() {
		return prfl;
	}
	public BattleScreen getTeste() {
		return teste;
	}

	

}
