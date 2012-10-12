package br.edu.ifsp.lp2.shadowstruggles.object2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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
	

	private Label life;
	/***
	 * 
	 * @param player
	 *            An integer representing to which side this object belongs: 0
	 *            is the human player and otherwise is the machine. The default
	 *            value is 0.
	 */
	public LifeBar() {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/objects/life100.png")), 0, 0,
				MAX_WIDTH, 36));
		this.scaleX = 0.8f;
		this.scaleY = 0.8f;
		
		
		
		
		

	}

	public void update() {
		this.scaleX = 0.8f * percentage;
	}
	
	public void drawLife(int currentLife, int maxLife, Skin skin) {
		String lifeString = String.valueOf(currentLife)+"/"+String.valueOf(maxLife);
		this.life= new Label(lifeString,skin);
		this.life.x=this.x+20;
		this.life.y=this.y;
		this.getStage().addActor(life);		
	}
	public void drawLife(int currentLife, int maxLife) {
		String lifeString = String.valueOf(currentLife)+"/"+String.valueOf(maxLife);
		this.life.setText(lifeString);		
	}

}
