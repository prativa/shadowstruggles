package br.edu.ifsp.lp2.shadowstruggles.scripts;

import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Effect2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MineralogyAction extends CardAction {

	private boolean finished;
	private boolean started;

	public MineralogyAction() {
		this.finished = false;
		this.started = false;
	}

	@Override
	public void doAction(BattlePlatform platform, Image img,float delta) {

		Effect2D f = (Effect2D) img;

		if (finished) {
			f.getController().removeCard(f.getEffect());
		}
		if (!started) {
			img.setX(img.getX() + 24);
			started = true;
		}
	}

	@Override
	public void update(Image f1) {
		Effect2D f = (Effect2D) f1;
		f.setStateTime(Gdx.graphics.getDeltaTime() + f.getStateTime());
		f.setCurrentFrame(f.getAnimation().getKeyFrame(f.getStateTime(), true));
		f.setRegion(f.getCurrentFrame());
		if (f.getStateTime() > 120f) {
			finished = true;
		}
	}

	@Override
	public CardAction copy() {
		return new MineralogyAction();
	}
}
