package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.battle.Effect;

import com.badlogic.gdx.utils.Array;

public class EffectDAO {

	public static Effect getEffect(String key) {
		Effect card = null;
		
		Array<Effect> cards = new DataManager().getEffectList();
		for(Effect c : cards) {
			if(c.getName().equals(key))
				card = c;
		}
		
		return card;
	}	
	
	public static Array<Effect> getEffects() {
		return new DataManager().getEffectList();
	}
	
}
