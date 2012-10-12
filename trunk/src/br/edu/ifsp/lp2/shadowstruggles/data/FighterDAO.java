package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.battle.Fighter;

import com.badlogic.gdx.utils.Array;

public class FighterDAO {

	public static Fighter getFighter(String key) {
		Fighter fighter = null;
		
		Array<Fighter> fighters = new DataManager().getFighterList();
		for(Fighter f : fighters) {
			if(f.getName().equals(key))
				fighter = f;
		}
		
		return fighter;
	}	
	
	public static Array<Fighter> getFighters() {
		return new DataManager().getFighterList();
	}
	
}
