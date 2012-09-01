package com.shadowstruggles.object2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.shadowstruggles.Controller;

public class LifeBar extends Image{
	private Controller controller;
	
	public LifeBar(Controller controller) {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/objects/life100.png")), 0, 0, 128, 36));
		this.controller=controller;
		this.scaleX=0.8f;
		this.scaleY=0.8f;
	}
	
	
	public void setLife(){
		
	}

}
