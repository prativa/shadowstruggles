package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Class for implementing the actions/effects for each card.
 * A CardAction object can only exist in the local memory, unable
 * to go under serialization.
 */
public abstract class CardAction {
	public void doAction(BattlePlatform platform, Image img) { }
	public void doAction(BattlePlatform platform, int lane, int tile){}
	public void update(Image f1){}
}
