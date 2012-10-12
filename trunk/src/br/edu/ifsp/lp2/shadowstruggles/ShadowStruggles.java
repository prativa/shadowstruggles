package br.edu.ifsp.lp2.shadowstruggles;

import br.edu.ifsp.lp2.shadowstruggles.data.DataManager;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;
import br.edu.ifsp.lp2.shadowstruggles.screens.MainScreen;
import br.edu.ifsp.lp2.shadowstruggles.screens.StartScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/***
 * Represent the application structure, responsible for the change of screens
 * and other general operations related to the game.
 */

public class ShadowStruggles extends Game {
	private StartScreen startScreen;
	private MainScreen mainScreen;
	private BattleScreen teste;
	public static final String LOG = ShadowStruggles.class.getSimpleName();
	private Controller controller;
	private Profile profile;
	private DataManager manager;
	
	/**
	 * When the application is in debug mode, the error messages are thrown into the
	 * standard output in order to make exception handling easier to the developer.
	 * Otherwise (in the versions released to the public), the exceptions are encapsulated
	 * into an user-friendly error screen. The default value is true.
	 */
	private boolean debugMode = true;

	@Override
	public void create() {
		this.manager = new DataManager();
		this.controller = new Controller();
		this.startScreen = new StartScreen(this, controller);
		this.mainScreen = new MainScreen(this, controller);
		this.setScreen(startScreen);
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	public Controller getController() {
		return controller;
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public StartScreen getStartScreen() {
		return startScreen;
	}

	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public BattleScreen getTeste() {
		return teste;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Profile getProfile() {
		return profile;
	}
	
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	public boolean isDebugMode() {
		return this.debugMode;
	}
	
	public DataManager getManager() {
		return this.manager;
	}

}
