package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.screens.*;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.screens.BaseScreen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.FadeTo;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

/***
 * Specifies how the cards look and behave on the player's hand.
 */

public class HandCard extends FixedObject implements InputProcessor {

	static final float SCALE_X = 0.3f;
	static final float SCALE_Y = 0.3f;

	private int type = 1;
	private boolean isSelected = false;
	private int side = 1;
	private String name;
	private Card card;
	private Controller controller;
	private boolean touched;
	private boolean justTouched;
	private boolean dragging;
	private float clickedX;
	private float clickedY;
	
	public HandCard(Controller controller, String name, int initialX, Card card) {
		super(new TextureRegion(Assets.handCards.get(card.getName()), 0, 0,
				360, 480), initialX, true);
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
		this.controller = controller;
		this.touched = false;
		this.justTouched = false;
		this.dragging = false;
	}
	
	public void move(Stage st, int cameraInitialX) {
		if (!dragging)
			this.x = this.initialX + st.getCamera().position.x - cameraInitialX;
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

	public boolean isDragging() {
		return this.dragging;
	}
	
	public void resetBlink() {
		this.clearActions();
		Sequence sAction = Sequence.$();
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

	

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) controller
				.getCurrentScreen().getSettings().screenHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));

		int deltaCamX = (int) (controller.getCurrentScreen().camera.position.x - BaseScreen.CAMERA_INITIAL_X);

		if (x + deltaCamX >= this.x
				&& x + deltaCamX <= this.x + this.width * this.scaleX
				&& invertY >= this.y
				&& invertY <= this.y + this.height * this.scaleY) {
			touched = true;

			if (!justTouched) {
				justTouched = true;
				clickedX=x;
				clickedY=y;
			}
			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) controller
				.getCurrentScreen().getSettings().screenHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (controller.getCurrentScreen().camera.position.x - BaseScreen.CAMERA_INITIAL_X);
		
		if (touched) {
			if (!dragging) {
				if (x + deltaCamX >= this.x
						&& x + deltaCamX <= this.x + this.width * this.scaleX
						&& invertY >= this.y
						&& invertY <= this.y + this.height * this.scaleY) {
					if (!isSelected)
						isSelected = true;
					else
						isSelected = false;

					Sequence sAction = Sequence.$();

					if (isSelected) {
						controller.handCardClicked(getCard(), isSelected);
						FadeTo fIn = FadeTo.$(255f, 0.25f);
						FadeOut fOut = FadeOut.$(0.25f);
						Delay delayAction = Delay.$(fIn, 0.25f);
						sAction = Sequence.$(fOut, delayAction);
						action(Forever.$(sAction));
					} else {
						controller.handCardClicked(getCard(), isSelected);
					}
				}
			} else {
				resetPosition();
				this.controller.getPlatform().setSelectedCard(null);
				((BattleScreen)(this.controller.getCurrentScreen())).changePentagram(false);
			}

			touched = false;
			dragging = false;
		}

		justTouched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) controller
				.getCurrentScreen().getSettings().screenHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (controller.getCurrentScreen().camera.position.x - BaseScreen.CAMERA_INITIAL_X);
		
		if(justTouched) {
			this.controller.handCardClicked(this.getCard(), true);
		}
		
		if (touched && Math.sqrt(Math.pow(x-clickedX,2)+Math.pow(y-clickedY,2))>50 ) {
			if(!dragging){
				this.dragging=true;
				this.scaleX=(float)(this.scaleX/2);
				this.scaleY=(float)(this.scaleY/2);
				clickedX=-100;
				clickedY=-100;
			}
			this.x = x - this.width * this.scaleX / 2 + deltaCamX;
			this.y = invertY - this.height * this.scaleY / 2;
			
			return true;
		}
		return false;
	}
	
	public void resetPosition() {
		this.x = initialX+(int) (controller.getCurrentScreen().camera.position.x - BaseScreen.CAMERA_INITIAL_X);
		this.y = this.controller.getCurrentScreen().getSettings().bottomElementY;
		this.scaleX = SCALE_X;
		this.scaleY = SCALE_Y;
	}

	@Override
	public boolean touchMoved(int x, int y) {

		return false;
	}

}
