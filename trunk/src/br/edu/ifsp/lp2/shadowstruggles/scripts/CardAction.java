package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Class for implementing the actions/effects for each card.
 * A CardAction object can only exist in the local memory, unable
 * to go under serialization.
 */
public abstract class CardAction {

	/***
	 * Execution of the card action.
	 */
	public void doAction(BattlePlatform platform, Image img, float delta) { }
	public void doAction(BattlePlatform platform, int lane, int tile){}
	
	/***
	 * Updates the visual representation of the card.
	 */
	public void update(Image f1){}
	public abstract CardAction copy();
}
