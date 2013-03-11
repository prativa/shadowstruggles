package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
		SequenceAction sAction = new SequenceAction();
		
		AlphaAction fIn = new AlphaAction();
		fIn.setAlpha(255f);
		AlphaAction fOut = new AlphaAction();
		fOut.setAlpha(0.25f);
		DelayAction delayAction = new DelayAction();
		delayAction.setDuration(0.25f);
		
		sAction.addAction(fIn);
		sAction.addAction(fOut);
		sAction.addAction(delayAction);
		
		this.addAction(sAction);
	}

	public void stopBlink() {
		this.clearActions();
	}

	public void setReady(boolean ready) {
		this.ready = ready;

		if (ready) {
			this.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getAssets().get("data/images/objects/deck.png", Texture.class), 0, 0,
					DECK_WIDTH, DECK_HEIGHT)));
			startBlink();
		}
		if (!ready) {
			this.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getAssets().get("data/images/objects/deckNotReady.png", Texture.class), 0, 0,
					DECK_WIDTH, DECK_HEIGHT)));
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
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}

}
