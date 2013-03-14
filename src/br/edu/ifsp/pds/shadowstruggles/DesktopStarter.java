package br.edu.ifsp.pds.shadowstruggles;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopStarter {

	/** The application's entry point for desktop platforms.
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationListener listener = new ShadowStruggles();
		String title = "Shadow Struggles";
        int width = 960, height = 640;
        boolean useOpenGLES2 = false;
        new LwjglApplication( listener, title, width, height, useOpenGLES2 );
//		TexturePacker2.process("data/images/controls", "data/images/controls", "skin");
	}

}
