package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.data.Assets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class LifeBar extends FixedObject {

	private static int MAX_WIDTH = 128;

	/***
	 * The percentage of the remaining life.
	 */
	public float percentage = 1.0f;

	/***
	 * The relative path of the image used for the life bar. It depends on the
	 * specified player.
	 */

	private Label life;

	public LifeBar(int initialX) {
		super(new TextureRegion(Assets.lifeBar, 0, 0,
				MAX_WIDTH, 36), initialX);
		this.scaleX = 0.8f;
		this.scaleY = 0.8f;

	}

	public void update(int currentLife, int maxLife) {
		percentage = (float)((float)currentLife/(float)maxLife);
		//this.setRegion(new TextureRegion(Assets.lifeBar, 0, 0, percentage, 36));
		this.scaleX = 0.8f * percentage;
	}

	public void drawLife(int currentLife, int maxLife, Skin skin) {
		String lifeString = String.valueOf(currentLife) + "/"
				+ String.valueOf(maxLife);
		this.life = new Label(lifeString, skin);
		this.life.x = this.x + 20;
		this.life.y = this.y;
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
		this.life.x = this.x + 20;
		this.life.y = this.y;
	}

}
