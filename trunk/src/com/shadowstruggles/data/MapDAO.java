package com.shadowstruggles.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.shadowstruggles.Controller;
import com.shadowstruggles.battle.Map;

public class MapDAO {
	public MapDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Map getMap(Controller controller, String mapName) {
		Map map;
		TextureRegion mapImage = new TextureRegion(new Texture(
				Gdx.files.internal("data/images/maps/"+mapName+".png")), 0, 0, 1920, 480);
		//Array<Array<Array<Object>>> tiles= new Array<Array<Array<Object>>>();
		map= new Map();
		return map;

	}
	

}
