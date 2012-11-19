package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Effect2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class RecoveryAction extends CardAction {

	private boolean finished;
	private boolean used=false;
	@Override
	public void doAction(BattlePlatform platform, int lane, int tile) {

	}

	@Override
	public void doAction(BattlePlatform platform, Image img) {
		Effect2D e = (Effect2D)img;
		if (!finished) {
			if(!used){
			for (Array<Array<Card>> lanes : platform.getMap().getTiles()) {
					for (Array<Card> tiles : lanes) {
						for (Card card : tiles) {
							if (card.getClass().equals(Fighter.class)
									&& (((Fighter) card).getName().startsWith(
											"DR-") || ((Fighter) card)
											.getName().startsWith("RD-"))) {
								if (card.getDirection() == e.getEffect()
										.getDirection())
									fighterRecovery((Fighter) card);
							}
						}
					}
				}
				used = true;

			}
		} else {
			e.getController().removeCard(e.getEffect());
		}
	}

	private void fighterRecovery(Fighter fighter) {
		System.out.println("old hp:" + fighter.health);
		fighter.health += (fighter.maxHealth / 3);
		if (fighter.health > fighter.maxHealth)
			fighter.health = fighter.maxHealth;
		System.out.println("new hp:" + fighter.health);
	}

	@Override
	public void update(Image f1) {
		Effect2D f = (Effect2D) f1;
		f.setStateTime(Gdx.graphics.getDeltaTime() + f.getStateTime());
		f.setCurrentFrame(f.getAnimation().getKeyFrame(f.getStateTime(), true));
		f.setRegion(f.getCurrentFrame());
		if(f.getStateTime()>2f){
			finished=true;
		}
		
	}

	@Override
	public CardAction copy() {
		return new RecoveryAction();
	}
}
