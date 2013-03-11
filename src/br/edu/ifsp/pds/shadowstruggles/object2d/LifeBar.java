package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/***
 * A visual representation of the player/enemy current life.
 */

public class LifeBar extends FixedObject {

	private static int MAX_WIDTH = 128;

	/***
	 * The percentage of the remaining life.
	 */
	private float percentage = 1.0f;

	/***
	 * The relative path of the image used for the life bar. It depends on the
	 * specified player.
	 */

	private Label life;

	public LifeBar(int initialX, ShadowStruggles game) {
		super(new TextureRegion(game.getAssets().get("data/images/objects/life100.png", Texture.class), 0, 0, MAX_WIDTH, 36), initialX);
		this.setScaleX(0.8f);
		this.setScaleY( 0.8f);

	}

	public void update(int currentLife, int maxLife) {
		percentage = (float) ((float) currentLife / (float) maxLife);
		this.setScaleX( 0.8f * percentage);
	}

	public void drawLife(int currentLife, int maxLife, Skin skin) {
		String lifeString = String.valueOf(currentLife) + "/"
				+ String.valueOf(maxLife);
		this.life = new Label(lifeString, skin);
		this.life.setX(this.getX() + 20);
		this.life.setY(this.getY());
		this.getStage().addActor(life);
	}

	public void drawLife(int currentLife, int maxLife) {
		String lifeString = String.valueOf(currentLife) + "/"
				+ String.valueOf(maxLife);
		this.life.setText(lifeString);
	}

	@Override
	public void move(Stage st, int cameraInitialX) {
		super.move(st, cameraInitialX);
		this.life.setX( this.getX() + 20);
		this.life.setY(this.getY());
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public float getPercentage() {
		return percentage;
	}

}
