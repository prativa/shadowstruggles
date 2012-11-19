package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuButton extends FixedObject implements InputProcessor {

	Controller controller;
	private boolean touched;
	private boolean justTouched;
	private ShadowStruggles game;

	public MenuButton(Controller c, final ShadowStruggles game) {
		super(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/objects/pause.png")), 45, 45),
				10);
		this.y = 560;
		this.game = game;
		this.controller = c;

	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int deltaCamX = (int) (controller.getCurrentScreen().camera.position.x - BaseScreen.CAMERA_INITIAL_X);
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) controller
				.getCurrentScreen().getSettings().screenHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));
		
		if (x + deltaCamX >= this.x && x + deltaCamX <= this.x + this.width*scaleX
				&& invertY >= this.y && invertY <= this.y + this.height*scaleY) {
			touched = true;
			Assets.buttonSound4.play(1);
			if (!justTouched) {
				justTouched = true;
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int deltaCamX = (int) (controller.getCurrentScreen().camera.position.x - BaseScreen.CAMERA_INITIAL_X);
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) controller
				.getCurrentScreen().getSettings().screenHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));
	
		if (touched) {
			if (x + deltaCamX >= this.x && x + deltaCamX <= this.x + this.width*scaleX
					&& invertY >= this.y && invertY <= this.y + this.height*scaleY) {
				controller.menuButtonClicked(this.game);
			}
			touched = false;
		}

		justTouched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {

		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

}
