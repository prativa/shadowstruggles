package br.edu.ifsp.lp2.shadowstruggles;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class MyStage extends Stage {
	
	Array<Actor> currentActor;

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
