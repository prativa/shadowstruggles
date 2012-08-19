package com.shadowstruggles.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.shadowstruggles.battle.Map;

public class MapDAO {
	public MapDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Map getMap(String mapName) {
		Map map;
		Image mapImage = new Image(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/maps/"+mapName+".png")), 0, 0, 1920, 480));
		Array<Array<Object>> tiles= new Array<Array<Object>>();
		map= new Map(mapImage, tiles);
		return map;

	}
	

}
