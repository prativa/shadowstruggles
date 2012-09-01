package com.shadowstruggles.games;

import com.badlogic.gdx.utils.Array;
import com.shadowstruggles.Profile;
import com.shadowstruggles.ShadowStruggles;
import com.shadowstruggles.battle.BattleLogic;
import com.shadowstruggles.battle.BattlePlatform;
import com.shadowstruggles.battle.Map;
import com.shadowstruggles.object2d.Map2D;
import com.shadowstruggles.screens.BattleScreen;

/***
 * A sample battle for testing the key aspects of the game engine.
 */

public class BattleTest extends BattleScreen{
	
	
	public BattleTest(ShadowStruggles game) {		
		super(game, "data/images/maps/testmap.png",new Profile(null,null,null,0,0,0), game.getController(), new BattleLogic(new BattlePlatform(null, null, null, new Map(),null),game.getController()));
	}
	
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		//dr.walk();
	}
	
	@Override
	public void show() {		
		super.show();		
	}

}
