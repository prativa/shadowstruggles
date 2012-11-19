package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Trap2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HackingAction extends CardAction {

	private boolean finished = false;
	private boolean activated = false;

	@Override
	public void doAction(BattlePlatform platform, Image img) {

		Trap2D f = (Trap2D) img;
		if (finished) {
			f.getController().removeCard(f.getTrap());
		} else {
			if (!activated) {
				for (Card c : platform.getMap().getTiles()
						.get(f.getTrap().getTile()).get(f.getTrap().getLane())) {
					if (c.getClass().equals(Fighter.class)
							&& c.getDirection() != f.getTrap().getDirection() && ((Fighter)c).getName().startsWith("DR-")) {
						((Fighter) c).setDirection(-((Fighter) c)
								.getDirection());
						((Fighter2D)(c.getImage())).create();
						activated = true;
						f.visible=true;
					}
				}
			}
		}

	}

	@Override
	public void update(Image f1) {
		Trap2D f = (Trap2D) f1;
		if (f.visible) {
			f.setStateTime(Gdx.graphics.getDeltaTime() + f.getStateTime());
			f.setCurrentFrame(f.getAnimation().getKeyFrame(f.getStateTime(),
					true));
			f.setRegion(f.getCurrentFrame());
			if (f.getStateTime() > 2f) {
				finished = true;
			}
		}
	}

	@Override
	public CardAction copy() {
		return new HackingAction();
	}

}
