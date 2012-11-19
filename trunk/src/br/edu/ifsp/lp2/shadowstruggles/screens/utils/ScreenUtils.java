package br.edu.ifsp.lp2.shadowstruggles.screens.utils;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Useful methods for building the screens.
 */
public class ScreenUtils {

	/**
	 * Builds a TextButton according to the specified parameters, encapsulating
	 * its initialization.
	 */
	public static TextButton initButton(TextButton actor, float x, float y,
			float width, float height) {
		actor.x = x;
		actor.y = y;
		actor.width = width;
		actor.height = height;
		return actor;
	}

}
