package com.shadowstruggles.battle;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.shadowstruggles.Controller;

public class Map {	
	private Array<Array<Array<Object>>> tiles;	
	
	public Map() {				
		this.tiles = new Array<Array<Array<Object>>>();
		for(int i = 0; i< 50; i++){
			tiles.add(new Array<Array<Object>>());
			for(int j = 0; j < 3; j++){
				tiles.get(i).add(new Array<Object>());
			}
		}
		
	}	
	
	
	public Array<Array<Array<Object>>> getTiles() {
		return tiles;
	}
	public void setTiles(Array<Array<Array<Object>>> tiles) {
		this.tiles = tiles;
	}
	
	public void addCard(Card card, int x, int y){
		tiles.get(x).get(y).add(card);
	}
	

	
}
