package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.Scene;

import com.badlogic.gdx.utils.Array;

public class SceneDAO {

	public static Scene getScene(int key) {
		Scene scene = null;
		
		Array<Scene> scenes = new DataManager().retrieveScenes();
		for(Scene s : scenes) {
			if(s.getId() == key)
				scene = s;
		}
		
		return scene;
	}	
	
	public static Array<Scene> getScenes() {
		return new DataManager().retrieveScenes();
	}
	
}
