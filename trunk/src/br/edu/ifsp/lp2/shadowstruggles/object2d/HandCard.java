package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.battle.Card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.FadeTo;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

/***
 * Specifies how the cards look and behave on the player's hand.
 */

public class HandCard extends FixedObject {

	static final float SCALE_X = 0.3f;
	static final float SCALE_Y = 0.3f;

	private int type = 1;
	private boolean isSelected = false;
	private int side = 1;
	private String name;
	private Card card;

	public HandCard(final Controller controller, String name, int initialX,
			final Card card) {
		super(new TextureRegion(
				new Texture(Gdx.files.internal("data/images/sprites/" + name
						+ "/Card.png")), 0, 0, 360, 480), initialX);
		/*
		 * this.illustration=new TextureRegion(new
		 * Texture(Gdx.files.internal("data/images/sprites/DR-002/Card.png"
		 * )),0,0,360,480); super.setRegion(illustration);
		 */
		this.card = card;
		card.setImage(this);
		this.scaleX = SCALE_X;
		this.scaleY = SCALE_Y;
		this.name = name;

		this.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				if (!isSelected)
					isSelected = true;
				else
					isSelected = false;
				Sequence sAction = Sequence.$();
				if (isSelected) {
					controller.handCardClicked(card, isSelected);
					FadeTo fIn = FadeTo.$(255f, 0.25f);
					FadeOut fOut = FadeOut.$(0.25f);
					Delay delayAction = Delay.$(fIn, 0.25f);
					sAction = Sequence.$(fOut, delayAction);
					action(Forever.$(sAction));
				} else {
					controller.handCardClicked(card, isSelected);
				}

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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void reset() {
		Sequence sAction = Sequence.$();
		clearActions();
		FadeTo fIn = FadeTo.$(255f, 0.25f);
		Delay delayAction = Delay.$(fIn, 0.25f);
		sAction = Sequence.$(fIn, delayAction);
		action(com.badlogic.gdx.scenes.scene2d.actions.Sequence.$(sAction));
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
