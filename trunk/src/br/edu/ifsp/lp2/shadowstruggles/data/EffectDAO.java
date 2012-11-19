package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.model.Effect;
import com.badlogic.gdx.utils.Array;

public class EffectDAO {

	public static Effect getEffect(String key, DataManager manager) {
		Effect card = null;
		
		Array<Effect> cards = manager.getEffectList();
		for(Effect c : cards) {
			if(c.getName().equals(key)) {
				/* This makes a deep copy of the effect so that similar effects
				don't share the same memory blocks. */
				card = new Effect(c.getName(), c.nameVisualization, c.energyCost,
						c.description, c.buyCost, c.getAction(),
						c.getDuration(), c.isImmediateEffect(),c.isOnFighter());
			}
		}
		
		return card;
	}	
	
	public static Array<Effect> getEffects(DataManager manager) {
		return manager.getEffectList();
	}
	
}
