package com.shadowstruggles.object2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.shadowstruggles.Controller;
import com.shadowstruggles.ShadowStruggles;

public class LifeBar extends Image {

	private static int MAX_WIDTH = 128;

	/***
	 * The percentage of the remaining life.
	 */
	public float percentage = 1.0f;

	/***
	 * The relative path of the image used for the life bar. It depends on the
	 * specified player.
	 */
	private String imagePath;

	/***
	 * 
	 * @param player
	 *            An integer representing to which side this object belongs: 0
	 *            is the human player and otherwise is the machine. The default
	 *            value is 0.
	 */
	public LifeBar(int player) {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/objects/life100.png")), 0, 0,
				MAX_WIDTH, 36));
		this.scaleX = 0.8f;
		this.scaleY = 0.8f;

		if (player == 0)
			this.imagePath = "data/images/objects/life100.png";
		else
			this.imagePath = "data/images/objects/life100.png";

	}

	public void update() {
		TextureRegion tg = this.getRegion();
		tg.setRegionWidth((int) (MAX_WIDTH * percentage));
		this.setRegion(tg);
	}

}
