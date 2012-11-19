package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.model.Trap;

import com.badlogic.gdx.utils.Array;

public class TrapDAO {

	public static Trap getTrap(String key, DataManager manager) {
		Trap card = null;
		
		Array<Trap> cards = manager.getTrapList();
		for(Trap c : cards) {
			if(c.getName().equals(key)) {
				/* This makes a deep copy of the effect so that similar effects
				don't share the same memory blocks. */
				card = new Trap(c.getName(),c.nameVisualization, c.getEnergyCost(),c.getDescription(),c.getBuyCost(),c.getAction(),c.getDuration(),c.isHasImmediateEffect());
			}
		}
		
		return card;
	}	
	
	public static Array<Trap> getTraps(DataManager manager) {
		return manager.getTrapList();
	}
}
