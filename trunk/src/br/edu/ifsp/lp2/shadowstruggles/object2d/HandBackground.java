package br.edu.ifsp.lp2.shadowstruggles.object2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/***
 * Represents the background of the player's hand on the screen.
 */

public class HandBackground extends FixedObject {

	public HandBackground(int initialX) {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/objects/background.png")), 0,
				0, 960, 160), initialX);
	}

}
