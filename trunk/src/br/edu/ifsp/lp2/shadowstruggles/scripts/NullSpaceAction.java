package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class NullSpaceAction extends CardAction{
	@Override
	public void doAction(BattlePlatform platform, Image img) {
		super.doAction(platform, img);
		for(Array<Array<Card>> lanes: platform.getMap().getTiles()){
			for(Array<Card> tiles: lanes){
				for (Card card:tiles){
					if(card.getClass().equals(Fighter.class)){
						//TODO remover todos os fighters
						
					}
				}
			}
		}
	}

	@Override
	public CardAction copy() {
		return new NullSpaceAction();
	}
}
