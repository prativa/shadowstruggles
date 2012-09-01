package com.shadowstruggles;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationListener listener = new ShadowStruggles();  
		
        String title = "ShadowStruggles";       
        int width = 960, height = 640;
        boolean useOpenGLES2 = false;
        new JoglApplication( listener, title, width, height, useOpenGLES2 );
		
	}

}
