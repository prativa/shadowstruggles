package com.shadowstruggles.object2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.FadeTo;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.shadowstruggles.Controller;
import com.shadowstruggles.battle.BattleLogic;

/***
 * Specifies how the cards look and behave on the player's hand.
 */

public class HandCard extends FixedObject{
	
	static final float SCALE_X = 0.3f;
	static final float SCALE_Y = 0.3f;
	
	private BattleLogic battleLogic;
	private TextureRegion illustration;
	private int type=1;
	private int energyCost;
	private boolean isSelected=false;
	private HandCard handCard=this;
	private int side=1;
	private String name;
	
	public HandCard(final Controller controller, String name, int initialX) {
		super(new TextureRegion(new Texture(Gdx.files.internal("data/images/sprites/DR-002/Card.png")),0,0,360,480), initialX);
		/*this.illustration=new TextureRegion(new Texture(Gdx.files.internal("data/images/sprites/DR-002/Card.png")),0,0,360,480);
		super.setRegion(illustration);*/
		this.scaleX = SCALE_X;
		this.scaleY = SCALE_Y;
		this.name=name;
				
		this.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {				
				if(!isSelected)
					isSelected=true;
				else isSelected=false;
				controller.handCardClicked(handCard, isSelected);
//				Sequence sAction = Sequence.$();
//				if(isSelected){
//					FadeTo fIn = FadeTo.$(255f , 0.25f);
//					FadeOut fOut=FadeOut.$(0.25f);
//					Delay delayAction = Delay.$(fIn, 0.25f);		
//					sAction = Sequence.$(fOut, delayAction);
//					action(Forever.$(sAction));
//				}
//				if(!isSelected)Forever.$(sAction).finish();
//				System.out.println(isSelected);
							
			}
		});
	}
	
	public int getType() {
		return type;
	}
	
	public int getSide() {
		return side;
	}
	
	public String getName() {
		return name;
	}
	
	
	

}
