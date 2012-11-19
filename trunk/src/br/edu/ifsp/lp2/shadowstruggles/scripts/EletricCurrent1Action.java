package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Trap2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class EletricCurrent1Action extends CardAction {

	private boolean finished = false;
	private boolean started = false;
	private float initialSpeed;
	private int count = 0;
	private Card aflicted = null;

	@Override
	public void doAction(BattlePlatform platform, Image img) {

		Trap2D f = (Trap2D) img;

		if (!started) {
			img.x += 24;
			started = true;
		}
		if (aflicted != null) {
			if (((Fighter) aflicted).getHealth() > 0) {
				for (Card c : platform
						.getMap()
						.getTiles()
						.get(f.getTrap().getTile())
						.get(f.getTrap().getLane())) {
					if (c.getClass().equals(Fighter.class)
							&& c.getDirection() != f.getTrap().getDirection()) {
						if (((Fighter) c).speed > 0) {
							f.visible = true;
							initialSpeed = ((Fighter) c).speed;
							((Fighter) c).speed = 0;
						} else {
							if (finished) {
								((Fighter) c).speed = initialSpeed;
								f.getController().removeCard(f.getTrap());
								break;
							} else if (((int) f.getStateTime()) == count) {
								((Fighter) c).setHealth(((Fighter) c)
										.getHealth() - 2);
								count++;
								System.out.println(((Fighter) c).getHealth());
							}

						}

					}
				}
			} else {
				f.getController().removeCard(f.getTrap());
			}
		}else{
			for (Card c : platform
					.getMap()
					.getTiles()
					.get(f.getTrap().getTile())
					.get(f.getTrap().getLane())) {
				if (c.getClass().equals(Fighter.class)
						&& c.getDirection() != f.getTrap().getDirection()) {
					if (((Fighter) c).speed > 0) {
						f.visible = true;
						initialSpeed = ((Fighter) c).speed;
						((Fighter) c).speed = 0;
						aflicted = c;
					} else {
						if (finished) {
							((Fighter) c).speed = initialSpeed;
							f.getController().removeCard(f.getTrap());
							break;
						} else if (((int) f.getStateTime()) == count) {
							((Fighter) c).setHealth(((Fighter) c)
									.getHealth() - 2);
							count++;
							System.out.println(((Fighter) c).getHealth());
						}

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
			if (f.getStateTime() > 5f) {
				finished = true;
			}
		}
	}

	@Override
	public CardAction copy() {
		return new EletricCurrent1Action();
	}
}
