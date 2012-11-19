package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.model.Scene;

import com.badlogic.gdx.utils.Array;

public class SceneDAO {

	public static Scene getScene(int key, DataManager manager) {
		Scene scene = null;
		
		Array<Scene> scenes = manager.getScenesList();
		for(Scene s : scenes) {
			if(s.getId() == key)
				scene = s;
		}
		
		return scene;
	}	
	
	public static Array<Scene> getScenes(DataManager manager) {
		return manager.getScenesList();
	}
	
}
