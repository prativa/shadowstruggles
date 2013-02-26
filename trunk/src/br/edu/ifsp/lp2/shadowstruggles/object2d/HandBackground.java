package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/***
 * Represents the background of the player's hand on the screen.
 */

public class HandBackground extends FixedObject {

	public HandBackground(int initialX, ShadowStruggles game) {
		super(new TextureRegion(game.getAssets().get("data/images/objects/background.png", Texture.class), 0,
				0, 960, 160), initialX);
	}

}
