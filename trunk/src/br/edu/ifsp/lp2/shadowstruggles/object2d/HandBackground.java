package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.data.Assets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/***
 * Represents the background of the player's hand on the screen.
 */

public class HandBackground extends FixedObject {

	public HandBackground(int initialX) {
		super(new TextureRegion(Assets.handBackground, 0,
				0, 960, 160), initialX);
	}

}
