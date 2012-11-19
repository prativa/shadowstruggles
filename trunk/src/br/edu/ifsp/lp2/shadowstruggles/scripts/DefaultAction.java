package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * The default behavior of a fighter card. It walks towards the opposing base
 * with the objective of destroying it, attacking any enemy fighters along the
 * way.
 */
public class DefaultAction extends CardAction {

	// TODO: Acrescentar efeito sonoro de luta enquanto houver colisão com um
	// lutador ou a base inimiga.
	@Override
	public void doAction(BattlePlatform platform, Image img) {
		Fighter2D f = (Fighter2D) img;
		f.setAttacking(false);
		if (f.getFighter().getHealth() > 0) {
			attack(f, platform);

			if (!f.isAttacking())
				attackBase(f, platform);

			if (!f.isAttacking())
				move(f, platform);

		} else {
			f.getController().removeCard(f.getFighter());
		}

	}

	/**
	 * Verifies if there's an enemy within the fighter's range and deals damage
	 * to it.
	 * 
	 * @param f
	 *            The fighter's visual representation.
	 * @param platform
	 *            The match's state variables.
	 */
	private void attack(Fighter2D f, BattlePlatform platform) {
		for (int i = 0; i <= f.getFighter().range
				&& f.getFighter().getTile() + f.getFighter().getDirection() * i >= 0
				&& f.getFighter().getTile() + f.getFighter().getDirection() * i <= 35; i++) {
			if (platform
					.getMap()
					.getTiles()
					.get(f.getFighter().getTile()
							+ f.getFighter().getDirection() * i)
					.get(f.getFighter().getLane()).size > 0) {
				for (Card c : platform
						.getMap()
						.getTiles()
						.get(f.getFighter().getTile()
								+ f.getFighter().getDirection() * i)
						.get(f.getFighter().getLane())) {
					if (c.getClass().equals(Fighter.class)) {
						if (((Fighter) c).getDirection() != f.getFighter()
								.getDirection()) {
							if (f.getFighter().getDelay() <= 0.0f) {
								((Fighter) c).setHealth(((Fighter) c)
										.getHealth() - f.getFighter().damage);
								f.getFighter().setDelay(
										f.getFighter().getAttackDelay());
							} else
								f.getFighter().setDelay(
										f.getFighter().getDelay() - (float)(1/BattleScreen.FPS));

							
							//f.setRegion(f.getCurrentFrame());
							f.setAttacking(true);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Moves the fighter towards the opposing base, if it's possible to do so.
	 * 
	 * @param f
	 *            The fighter's visual representation.
	 * @param platform
	 *            The match's state variables.
	 */

	private void move(Fighter2D f, BattlePlatform platform) {
		f.x = f.x + f.getFighter().getSpeed() * f.getFighter().getDirection();
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
		
		//f.setRegion(f.getCurrentFrame());
	}

	/**
	 * Attempts to attack the opposing base.
	 * 
	 * @param f
	 *            The fighter's visual representation.
	 * @param platform
	 *            The match's state variables.
	 */
	
	private void attackBase(Fighter2D f, BattlePlatform platform) {
		if (f.getFighter().getDirection() == 1) {
			if (f.getFighter().getTile() >= 36-f.getFighter().getRange()) {
				if (f.getFighter().getDelay() <= 0.0f) {
					f.getController().enemyLifeChanged(
							-f.getFighter().getDamage());
					f.getFighter().setDelay(f.getFighter().getAttackDelay());
				} else
					f.getFighter().setDelay(f.getFighter().getDelay() - (float)(1/BattleScreen.FPS));
				/*f.setStateTime(Gdx.graphics.getDeltaTime() + f.getStateTime());
				f.setCurrentFrame(f.getAttackAnimation().getKeyFrame(
						f.getStateTime(), true));
				f.setRegion(f.getCurrentFrame());*/
				f.setAttacking(true);
			}
		} else {
			if (f.getFighter().getTile() <= -1 +f.getFighter().getRange()) {
				if (f.getFighter().getDelay() <= 0.0f) {
					f.getController().playerLifeChanged(
							-f.getFighter().getDamage());
					f.getFighter().setDelay(f.getFighter().getAttackDelay());
				} else
					f.getFighter().setDelay(f.getFighter().getDelay() - (float)(1/BattleScreen.FPS));
				
				
				f.setAttacking(true);
			}
		}
	}

	public void update(Image f1){
		Fighter2D f = (Fighter2D)f1;
		if(f.isAttacking()){
			f.setStateTime(Gdx.graphics.getDeltaTime()
					+ f.getStateTime());
			f.setCurrentFrame(f.getAttackAnimation()
					.getKeyFrame(f.getStateTime(), true));
			f.setRegion(f.getCurrentFrame());
			
		}else{
			f.setStateTime(Gdx.graphics.getDeltaTime()
					+ f.getStateTime());
			f.setCurrentFrame(f.getWalkAnimation()
					.getKeyFrame(f.getStateTime(), true));
			f.setRegion(f.getCurrentFrame());
		}
		
	}

	@Override
	public CardAction copy() {
		return new DefaultAction();
	}
}
