package br.edu.ifsp.lp2.shadowstruggles.screens.utils;

import br.edu.ifsp.lp2.shadowstruggles.screens.BaseScreen;

public class TransitionEffect {

	protected float alpha;
	protected float duration;
	protected boolean finished;
	// returns a value between 0 and 1 representing the level of completion of the transition.
	protected float getAlpha() {
		return alpha;
	}

	public void update(float delta) {
		
		
	} 

	public void render(BaseScreen current, BaseScreen next,float delta)
	{System.out.println("oi");}

	boolean isFinished() {
		return finished;
	}

	public TransitionEffect(float duration) {
		this.duration=duration;
	}
}
