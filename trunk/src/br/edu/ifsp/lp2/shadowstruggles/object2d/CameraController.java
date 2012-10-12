package br.edu.ifsp.lp2.shadowstruggles.object2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

public class CameraController extends FixedObject {
	private int side;
	public CameraController(int side) {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/controls/right.png")), 32, 32),
				480 + 440 * side);
		if(side==-1) this.getRegion().flip(true, false);
		this.y = 340;
		this.width = 32;
		this.height = 32;
		this.side=side;
		
	}
	
	public void init(){
		final OrthographicCamera camera = (OrthographicCamera) this.getStage()
				.getCamera();
		this.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				if (side == 1){
					if (camera.position.x != 1440)
						camera.position.x += 480;
				}else if (side==-1)
					if(camera.position.x!=480)
						camera.position.x-=480;
			}
		});
	}

}
