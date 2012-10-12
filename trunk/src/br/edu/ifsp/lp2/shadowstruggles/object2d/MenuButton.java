package br.edu.ifsp.lp2.shadowstruggles.object2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

public class MenuButton extends FixedObject{

	public MenuButton(TextureRegion textureRegion, int initialX) {
		super(textureRegion, initialX);
		this.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				//setScreen(inGameMenu);
				
			}
		});
	}

}
