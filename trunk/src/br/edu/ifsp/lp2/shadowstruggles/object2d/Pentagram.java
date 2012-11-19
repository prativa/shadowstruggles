package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * A Pentagram object is shown when the human player selects a card, in order to
 * inform him where it's possible to place his cards.
 */
public class Pentagram extends Image {

	public Pentagram(float x, float y, ShadowStruggles game) {
		super(new TextureRegion(Assets.pentagram, 0, 0, 128, 128));
		this.scaleX = ((float) game.getManager().getSettings().tileWidth*2 / (float) 128);
		this.scaleY = ((float) game.getManager().getSettings().tileHeight / (float) 128);
		this.x = x;
		this.y = y;
		this.visible = false;
	}

	public void update() {

	}

}
