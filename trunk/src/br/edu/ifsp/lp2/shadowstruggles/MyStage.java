package br.edu.ifsp.lp2.shadowstruggles;

import br.edu.ifsp.lp2.shadowstruggles.screens.BattleScreen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * A specific stage for the {@link BattleScreen}, providing access
 * to a list of Actors in order to deal mainly with resizing and
 * manipulation of 2D objects.
 */
public class MyStage extends Stage {
	
	private Array<Actor> currentActor;

	public MyStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		currentActor = new Array<Actor>();
	}

	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
		currentActor.add(actor);
	}
	public void insertActor(Actor actor){
		super.addActor(actor);
	}
	
		
	@Override
	public void removeActor(Actor actor) {
		super.removeActor(actor);
		currentActor.removeValue(actor,true);
	}
	
	public Array<Actor> getCurrentActor() {
		return currentActor;
	}
	
	public void setCurrentActor(Array<Actor> currentActor) {
		this.currentActor = currentActor;
	}
}
