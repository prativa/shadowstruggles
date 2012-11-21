package br.edu.ifsp.lp2.shadowstruggles;

import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.data.DataManager;
import br.edu.ifsp.lp2.shadowstruggles.data.SoundManager;
import br.edu.ifsp.lp2.shadowstruggles.model.Profile;
import br.edu.ifsp.lp2.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.lp2.shadowstruggles.screens.StartScreen;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.FadeInTransitionEffect;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.FadeOutTransitionEffect;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.TransitionEffect;
import br.edu.ifsp.lp2.shadowstruggles.screens.utils.TransitionScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

/***
 * Represent the application structure, responsible for the change of screens
 * and other general operations related to the game.
 */

public class ShadowStruggles extends Game {
	public static final String LOG = ShadowStruggles.class.getName();
	private Controller controller;
	private Profile profile;
	private DataManager manager;
	private SoundManager audio;
	

	/**
	 * When the application is in debug mode, the error messages are thrown into
	 * the standard output in order to make exception handling easier to the
	 * developer. Otherwise (in the versions released to the public), the
	 * exceptions are encapsulated into an user-friendly error screen. The
	 * default value is true.
	 */
	private boolean debugMode = true;

	@Override
	public void create() {
		this.manager = new DataManager();
		Assets.load(this.manager);
		audio = new SoundManager();
		audio.setMusic("scene1");
		this.controller = new Controller();
		this.setScreen(new StartScreen(this, controller));
		
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	public void setScreen(Screen screen) {
			super.setScreen(screen);
			controller.setCurrentscreen((BaseScreen) screen);
	}
	
	public void setScreenWithTransition(Screen screen) {		
			
			Array<TransitionEffect> effects = new Array<TransitionEffect>();

			effects.add(new FadeOutTransitionEffect(0.7f));
			effects.add(new FadeInTransitionEffect(0.5f));

			BaseScreen transitionScreen = new TransitionScreen(this,
					(BaseScreen) this.getScreen(), (BaseScreen) screen,
					effects, this.controller);
			setScreen(screen);
			super.setScreen(transitionScreen);
			controller.setCurrentscreen((BaseScreen) screen);
		
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

	public SoundManager getAudio() {
		return audio;
	}
}
