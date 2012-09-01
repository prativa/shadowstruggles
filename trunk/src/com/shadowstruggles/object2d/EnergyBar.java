package com.shadowstruggles.object2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.shadowstruggles.Controller;

public class EnergyBar extends FixedObject{
	private Controller controller;	
	private boolean isReady;
	
	public EnergyBar(Controller controller, int initialX) {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/objects/energy100.png")), 0, 0, 64, 64), initialX);
		this.controller=controller;
		this.scaleX=1.3f;
		this.scaleY=1.3f;
		
	}
	
	public void setEnergy(){
		//mudar a imagem de acordo com a quantidade de energia da classe Energy
	}
	
	
}
