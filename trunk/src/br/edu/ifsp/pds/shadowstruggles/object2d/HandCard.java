package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/***
 * Specifies how the cards look and behave on the player's hand.
 */

public class HandCard extends FixedObject implements InputProcessor {

	private static final float SCALE_X = 0.3f;
	private static final float SCALE_Y = 0.3f;

	private int type = 1;
	private boolean isSelected = false;
	private int side = 1;
	private String name;
	private Card card;
	private ShadowStruggles game;
	private boolean touched;
	private boolean justTouched;
	private boolean dragging;
	private float clickedX;
	private float clickedY;
	private TextureRegion illustration;

	public HandCard(ShadowStruggles game, String name, int initialX, Card card) {
		super(new TextureRegion(game.getAssets().get(
				"data/images/sprites/" + name + "/card.png", Texture.class), 0,
				0, 360, 480), initialX, true);

		this.illustration = new TextureRegion(new Texture(
				Gdx.files.internal("data/images/sprites/"+name+"/card.png")), 0,
				0, 360, 480);
		((TextureRegionDrawable) super.getDrawable()).setRegion(illustration);

		this.card = card;
		card.setImage(this);
		this.setScaleX(SCALE_X);
		this.setScaleY(SCALE_Y);
		this.name = name;
		this.game = game;
		this.touched = false;
		this.justTouched = false;
		this.dragging = false;
	}

	public void move(Stage st, int cameraInitialX) {
		if (!dragging)
			this.setX(this.getInitialX() + st.getCamera().position.x
					- cameraInitialX);
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

	public void startBlink() {
		this.addAction(Actions.forever(Actions.sequence(Actions.fadeOut(0.3f),
				Actions.fadeIn(0.3f))));

	}

	public void resetBlink() {
		this.clearActions();
		this.addAction(Actions.fadeIn(0.3f));
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int invertY = (int) ((game.getController().getCurrentScreen()
				.getHeight() - y) * (float) ((float) game.getController()
				.getCurrentScreen().getSettings().screenHeight / (float) game
				.getController().getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) game.getController().getCurrentScreen()
				.getSettings().screenWidth / (float) game.getController()
				.getCurrentScreen().getWidth()));

		int deltaCamX = (int) (game.getController().getCurrentScreen()
				.getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);

		if (x + deltaCamX >= this.getX()
				&& x + deltaCamX <= this.getX() + this.getWidth()
						* this.getScaleX() && invertY >= this.getY()
				&& invertY <= this.getY() + this.getHeight() * this.getScaleY()) {
			touched = true;

			if (!justTouched) {
				justTouched = true;
				clickedX = x;
				clickedY = y;
			}

			game.getAudio().playSound("button_8");
			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int invertY = (int) ((game.getController().getCurrentScreen()
				.getHeight() - y) * (float) ((float) game.getController()
				.getCurrentScreen().getSettings().screenHeight / (float) game
				.getController().getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) game.getController().getCurrentScreen()
				.getSettings().screenWidth / (float) game.getController()
				.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (game.getController().getCurrentScreen()
				.getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);

		if (touched) {
			if (!dragging) {
				if (x + deltaCamX >= this.getX()
						&& x + deltaCamX <= this.getX() + this.getWidth()
								* this.getScaleX()
						&& invertY >= this.getY()
						&& invertY <= this.getY() + this.getHeight()
								* this.getScaleY()) {
					if (!isSelected)
						isSelected = true;
					else
						isSelected = false;
					

					if (isSelected) {
						game.getController().handCardClicked(getCard(),
								isSelected);

						startBlink();
					} else {
						game.getController().handCardClicked(getCard(),
								isSelected);
					}

				}
			} else {
				resetPosition();
				game.getController().getPlatform().setSelectedCard(null);
				((BattleScreen) (game.getController().getCurrentScreen()))
						.changeHexagram(false);
			}

			touched = false;
			dragging = false;
		}

		justTouched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		int invertY = (int) ((game.getController().getCurrentScreen()
				.getHeight() - y) * (float) ((float) game.getController()
				.getCurrentScreen().getSettings().screenHeight / (float) game
				.getController().getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) game.getController().getCurrentScreen()
				.getSettings().screenWidth / (float) game.getController()
				.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (game.getController().getCurrentScreen()
				.getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);

		if (justTouched) {
			game.getController().handCardClicked(this.getCard(), true);
		}

		if (touched
				&& Math.sqrt(Math.pow(x - clickedX, 2)
						+ Math.pow(y - clickedY, 2)) > 50) {
			if (!dragging) {
				this.dragging = true;
				this.setScaleX((float) (this.getScaleX() / 2));
				this.setScaleY((float) (this.getScaleY() / 2));
				clickedX = -100;
				clickedY = -100;
			}
			this.setX(x - this.getWidth() * this.getScaleX() / 2 + deltaCamX);
			this.setY(invertY - this.getHeight() * this.getScaleY() / 2);

			return true;
		}
		return false;
	}

	public void resetPosition() {
		this.setX(getInitialX()
				+ (int) (game.getController().getCurrentScreen().getCamera().position.x - BaseScreen.CAMERA_INITIAL_X));
		this.setY(game.getController().getCurrentScreen().getSettings().bottomElementY);
		this.setScaleX(SCALE_X);
		this.setScaleY(SCALE_Y);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
