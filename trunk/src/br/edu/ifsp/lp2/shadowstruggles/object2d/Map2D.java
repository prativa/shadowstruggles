package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Map2D extends Image{
	
	public Map2D(final Controller controller, TextureRegion tRegion) {
		super(tRegion);
		
		this.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				
				controller.mapClicked(x,y);				
			}
		});
	}

}
