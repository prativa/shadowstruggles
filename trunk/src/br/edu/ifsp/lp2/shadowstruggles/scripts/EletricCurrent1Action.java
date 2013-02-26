package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Trap2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class EletricCurrent1Action extends CardAction {

	private boolean finished;
	private boolean started;
	private float initialSpeed;
	private int count;
	private Card aflicted;

	public EletricCurrent1Action() {
		this.finished = false;
		this.started = false;
		this.count = 0;
		this.aflicted = null;
	}

	@Override
	public void doAction(BattlePlatform platform, Image img,float delta) {

		Trap2D f = (Trap2D) img;

		if (!started) {
			img.setX(img.getX() + 24);
			started = true;
		}
		if (aflicted != null) {
			if (((Fighter) aflicted).getHealth() > 0) {
				for (Card c : platform.getMap().getTiles()
						.get(f.getTrap().getTile()).get(f.getTrap().getLane())) {
					if (c.getClass().equals(Fighter.class)
							&& c.getDirection() != f.getTrap().getDirection()) {
						if (((Fighter) c).getSpeed() > 0) {
							f.setVisible(true);
							initialSpeed = ((Fighter) c).getSpeed();
							((Fighter) c).setSpeed(0);
						} else {
							if (finished) {
								((Fighter) c).setSpeed(initialSpeed);
								f.getController().removeCard(f.getTrap());
								break;
							} else if (((int) f.getStateTime()) == count) {
								((Fighter) c).setHealth(((Fighter) c)
										.getHealth() - 2);
								count++;

							}

						}

					}
				}
			} else {
				f.getController().removeCard(f.getTrap());
			}
		} else {
			for (Card c : platform.getMap().getTiles()
					.get(f.getTrap().getTile()).get(f.getTrap().getLane())) {
				if (c.getClass().equals(Fighter.class)
						&& c.getDirection() != f.getTrap().getDirection()) {
					if (((Fighter) c).getSpeed() > 0) {
						f.setVisible(true);
						initialSpeed = ((Fighter) c).getSpeed();
						((Fighter) c).setSpeed(0);
						aflicted = c;
					} else {
						if (finished) {
							((Fighter) c).setSpeed(initialSpeed);
							f.getController().removeCard(f.getTrap());
							break;
						} else if (((int) f.getStateTime()) == count) {
							((Fighter) c)
									.setHealth(((Fighter) c).getHealth() - 2);
							count++;

						}

					}

				}
			}
		}

	}

	@Override
	public void update(Image f1) {
		Trap2D trap = (Trap2D) f1;
		if (trap.isVisible()) {
			trap.setStateTime(Gdx.graphics.getDeltaTime() + trap.getStateTime());
			trap.setCurrentFrame(trap.getAnimation().getKeyFrame(
					trap.getStateTime(), true));
			trap.setRegion(trap.getCurrentFrame());
			if (trap.getStateTime() > 5f) {
				finished = true;
			}
		}
	}

	@Override
	public CardAction copy() {
		return new EletricCurrent1Action();
	}
}
