package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.FadeTo;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

public class Deck2D extends FixedObject implements InputProcessor {
	static final float SCALE_X = 2;
	static final float SCALE_Y = 2;
	static final int DECK_WIDTH = 64;
	static final int DECK_HEIGHT = 64;

	private boolean ready;
	private Controller controller;
	private boolean touched;

	public Deck2D(Controller controller, int initialX) {
		super(
				new TextureRegion(Assets.deckReady, 0, 0, DECK_WIDTH,
						DECK_HEIGHT), initialX);

		this.scaleX = SCALE_X;
		this.scaleY = SCALE_Y;
		this.setReady(true);
		this.controller = controller;
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
			this.setRegion(new TextureRegion(Assets.deckReady, 0, 0,
					DECK_WIDTH, DECK_HEIGHT));
			startBlink();
		}
		if (!ready) {
			this.setRegion(new TextureRegion(Assets.deckNotReady, 0, 0,
					DECK_WIDTH, DECK_HEIGHT));
		}
	}

	public boolean isReady() {
		return this.ready;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int invertY = (int)((controller.getCurrentScreen().getHeight() - y)*(float)((float)controller.getCurrentScreen().getSettings().screenHeight/(float)controller.getCurrentScreen().getHeight()));
		x = (int)(x*(float)((float)controller.getCurrentScreen().getSettings().screenWidth/(float)controller.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (controller.getCurrentScreen().camera.position.x - BaseScreen.CAMERA_INITIAL_X);

		if (x + deltaCamX >= this.x
				&& x + deltaCamX <= this.x + this.width * this.scaleX
				&& invertY >= this.y
				&& invertY <= this.y + this.height * this.scaleY) {
			touched = true;
			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int invertY = (int)((controller.getCurrentScreen().getHeight() - y)*(float)((float)controller.getCurrentScreen().getSettings().screenHeight/(float)controller.getCurrentScreen().getHeight()));
		x = (int)(x*(float)((float)controller.getCurrentScreen().getSettings().screenWidth/(float)controller.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (controller.getCurrentScreen().camera.position.x - BaseScreen.CAMERA_INITIAL_X);

		if (touched) {
			if (x + deltaCamX >= this.x
					&& x + deltaCamX <= this.x + this.width * this.scaleX
					&& invertY >= this.y
					&& invertY <= this.y + this.height * this.scaleY) {
				controller.deckClicked();
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
