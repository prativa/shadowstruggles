package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.FadeTo;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

/***
 * A visual representation of the player deck.
 */

public class Deck2D extends FixedObject implements InputProcessor {
	private static final float SCALE_X = 2;
	private static final float SCALE_Y = 2;
	private static final int DECK_WIDTH = 64;
	private static final int DECK_HEIGHT = 64;

	private boolean ready;
	private boolean touched;
	private ShadowStruggles game;

	public Deck2D(ShadowStruggles game, int initialX) {
		super(
				new TextureRegion(game.getAssets().get("data/images/objects/deck.png", Texture.class), 0, 0, DECK_WIDTH,
						DECK_HEIGHT), initialX);

		this.setScaleX(SCALE_X);
		this.setScaleY( SCALE_Y);
		this.game = game;
		this.setReady(true);
	}

	public void startBlink() {
		Sequence sAction = Sequence.$();
		FadeTo fIn = FadeTo.$(255f, 0.25f);
		FadeOut fOut = FadeOut.$(0.25f);
		Delay delayAction = Delay.$(fIn, 0.25f);
		sAction = Sequence.$(fOut, delayAction);
		action(Forever.$(sAction));
	}

	public void stopBlink() {
		this.clearActions();
		Sequence sAction = Sequence.$();
		FadeTo fIn = FadeTo.$(255f, 0.25f);
		Delay delayAction = Delay.$(fIn, 0.25f);
		sAction = Sequence.$(fIn, delayAction);
		action(com.badlogic.gdx.scenes.scene2d.actions.Sequence.$(sAction));
	}

	public void setReady(boolean ready) {
		this.ready = ready;

		if (ready) {
			this.setRegion(new TextureRegion(game.getAssets().get("data/images/objects/deck.png", Texture.class), 0, 0,
					DECK_WIDTH, DECK_HEIGHT));
			startBlink();
		}
		if (!ready) {
			this.setRegion(new TextureRegion(game.getAssets().get("data/images/objects/deckNotReady.png", Texture.class), 0, 0,
					DECK_WIDTH, DECK_HEIGHT));
		}
	}

	public boolean isReady() {
		return this.ready;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int invertY = (int) ((game.getController().getCurrentScreen().getHeight() - y) * (float) ((float) game.getController()
				.getCurrentScreen().getSettings().screenHeight / (float) game.getController()
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) game.getController().getCurrentScreen()
				.getSettings().screenWidth / (float) game.getController()
				.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (game.getController().getCurrentScreen().getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);

		if (x + deltaCamX >= this.getX()
				&& x + deltaCamX <= this.getX() + this.getWidth() * this.getScaleX()
				&& invertY >= this.getY()
				&& invertY <= this.getY() + this.getHeight() * this.getScaleY()) {
			touched = true;
			game.getAudio().playSound("button_4");
			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int invertY = (int) ((game.getController().getCurrentScreen().getHeight() - y) * (float) ((float) game.getController()
				.getCurrentScreen().getSettings().screenHeight / (float) game.getController()
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) game.getController().getCurrentScreen()
				.getSettings().screenWidth / (float) game.getController()
				.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (game.getController().getCurrentScreen().getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);

		if (touched) {
			if (x + deltaCamX >= this.getX()
					&& x + deltaCamX <= this.getX() + this.getWidth() * this.getScaleX()
					&& invertY >= this.getY()
					&& invertY <= this.getY() + this.getHeight() * this.getScaleY()) {
				game.getController().deckClicked();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {

		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {

		return false;
	}

}
