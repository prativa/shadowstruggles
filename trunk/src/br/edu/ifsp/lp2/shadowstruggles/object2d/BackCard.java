package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Visual representation of a card when it's placed on the field turned down.
 */

public class BackCard extends Image implements InputProcessor {

	private Card card;

	public BackCard(float x, float y, ShadowStruggles game) {
		super(new TextureRegion(game.getAssets().get(
				"data/images/objects/back_card.png", Texture.class), 0, 0, 64,
				48));
		this.setScaleX( game.getManager().getSettings().tileWidth / this.getWidth());
		this.setX(x);
		this.setY(y);
		this.setVisible(false);
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
