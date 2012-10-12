package br.edu.ifsp.lp2.shadowstruggles.object2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/***
 * Abstract class inherited by all fixed objects on the screen, that is,
 * the objects which remain on the same horizontal position relative to
 * the camera.
 */

public abstract class FixedObject extends Image{
	public int initialX;
	
	public FixedObject(int initialX) {
		this.initialX = initialX;
	}
	
	public FixedObject(TextureRegion textureRegion, int initialX) {
		super(textureRegion);
		this.initialX = initialX;
		this.x = initialX;
	}

	/***
	 * This should be called whenever the camera is updated. The object's
	 * position is adjusted accordingly.
	 * @param st The stage which contains the camera.
	 * @param cameraInitialX The initial position of the camera on the screen.
	 */
	
	public void move(Stage st, int cameraInitialX) {
		this.x = this.initialX + st.getCamera().position.x - cameraInitialX;
	}
	
	public int getInitialX() {
		return initialX;
	}
	
	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}
}
