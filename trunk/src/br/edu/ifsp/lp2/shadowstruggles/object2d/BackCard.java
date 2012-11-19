package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Visual representation of a card when it's placed on the field turned down.
 */

public class BackCard extends Image implements InputProcessor {

	private Card card;

	public BackCard(float x, float y, ShadowStruggles game) {
		super(new TextureRegion(Assets.backcard, 0, 0, 64, 48));
		this.scaleX = game.getManager().getSettings().tileWidth/this.width;
		//this.scaleY = ((float) 8 / (float) 10);
		this.x = x;
		this.y = y;
		this.visible = false;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
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
