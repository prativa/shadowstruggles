package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.data.Assets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class EnergyBar extends FixedObject {

	public float percentage;
	private Label energy;

	public EnergyBar(int initialX) {
		super(new TextureRegion(Assets.energyBar, 0, 0,
				128, 128), initialX);

		this.percentage = 0.45f;
	}

	/**
	 * Changes the image according to the percentage.
	 */
	public void update() {
		this.energy.x = this.x + 30;
		float h1 = percentage * 128;
		this.height = h1;
		int h2 = (int) h1;
		this.setRegion(new TextureRegion(Assets.energyBar, 0, 128 - h2, 128, h2));
	}

	public void initEnergy(int currentEnergy, int maxEnergy, Skin skin) {
		String energyString = String.valueOf(currentEnergy) + "/"
				+ String.valueOf(maxEnergy);
		this.energy = new Label(energyString, skin);
		this.energy.x = this.x + 30;
		this.energy.y = this.y + 50;
		this.getStage().addActor(energy);
	}

	public void drawEnergy(int currentEnergy, int maxEnergy) {
		String lifeString = String.valueOf(currentEnergy) + "/"
				+ String.valueOf(maxEnergy);
		this.energy.setText(lifeString);
	}

}