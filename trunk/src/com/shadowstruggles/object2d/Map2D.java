package com.shadowstruggles.object2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.shadowstruggles.Controller;

public class Map2D extends Image{
	private Controller controller;	
	
	public Map2D(final Controller controller, TextureRegion tRegion) {
		super(tRegion);
		this.controller=controller;	
		controller.initMap();
		this.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				
				controller.mapClicked(x,y);				
			}
		});
	}

}
