package br.edu.ifsp.lp2.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;

public class LoadScreen extends BaseScreen {

	private Image background;
	
	public LoadScreen(ShadowStruggles game) {
		super(game);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	public void initComponents() {
		stage.clear();

		background = new Image(new TextureRegion(new Texture(
				Gdx.files.internal("data/images/objects/loading.png")), 512,
				512));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 512f);

		stage.addActor(background);

	}

}
