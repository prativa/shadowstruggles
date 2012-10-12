package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.battle.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.battle.Card;
import br.edu.ifsp.lp2.shadowstruggles.battle.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DefaultAction extends CardAction {

	@Override
	public void doAction(BattlePlatform platform, Image img) {
		Fighter2D f = (Fighter2D) img;
		boolean attacking = false;
		if (platform.getMap().getTiles()
				.get(f.getFighter().getTile() + f.getFighter().getDirection())
				.get(f.getFighter().getLane()).size > 0) {
			for (Card c : platform
					.getMap()
					.getTiles()
					.get(f.getFighter().getTile()
							+ f.getFighter().getDirection())
					.get(f.getFighter().getLane())) {
				if (c.getClass().equals(Fighter.class)) {
					if (((Fighter) c).getDirection() != f.getFighter()
							.getDirection()) {
						((Fighter) c).setHealth(((Fighter) c).getHealth() - f.getFighter().damage);
						if(((Fighter) c).getHealth()<0){
							System.out.println("Morreu");
						}
						f.setStateTime(Gdx.graphics.getDeltaTime()
								+ f.getStateTime());
						f.setCurrentFrame(f.getAttackAnimation().getKeyFrame(
								f.getStateTime(), true));
						f.setRegion(f.getCurrentFrame());
						attacking = true;
						break;
					}
				}
			}
		}
		// System.out.println("colidiu");
		if (!attacking) {
			f.x = f.x + f.getFighter().getSpeed()
					* f.getFighter().getDirection();
			if (f.getFighter().getDirection() == 1) {
				if ((int) ((f.x - 96) / 48) > f.getFighter().tile) {
					f.getController().tileChanged(f.getFighter());
					f.getFighter().tile++;
				}
			} else {
				if ((int) ((f.x - 48) / 48) < f.getFighter().tile) {
					f.getController().tileChanged(f.getFighter());
					f.getFighter().tile--;
				}

			}
			f.setStateTime(Gdx.graphics.getDeltaTime() + f.getStateTime());
			f.setCurrentFrame(f.getWalkAnimation().getKeyFrame(
					f.getStateTime(), true));
			f.setRegion(f.getCurrentFrame());
		}

	}

}
