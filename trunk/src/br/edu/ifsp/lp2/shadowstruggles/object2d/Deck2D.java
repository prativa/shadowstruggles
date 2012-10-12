package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

public class Deck2D extends FixedObject {
	static final float SCALE_X = 2;
	static final float SCALE_Y = 2;
	static final int DECK_WIDTH = 64;
	static final int DECK_HEIGHT = 64;
	
	public Deck2D(final Controller controller, int initialX) {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/objects/deck.png")), 0, 0, DECK_WIDTH,
				DECK_HEIGHT), initialX);
		
		this.scaleX = SCALE_X;
		this.scaleY = SCALE_Y;
		
		this.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				controller.deckClicked();
			}
		});
	}
}
