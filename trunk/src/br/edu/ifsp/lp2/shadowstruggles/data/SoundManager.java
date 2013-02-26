package br.edu.ifsp.lp2.shadowstruggles.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Class for encapsulating the sound management within the application.
 * Currently, it supports only the .ogg format.
 */

public class SoundManager {
	private Music music;
	private float volume; // Goes from 0 to 1.
	private String musicName;
	private AssetManager assets;

	public SoundManager(AssetManager assets) {
		this.volume = 0.3f;
		this.assets = assets;
	}

	/**
	 * Sets the current background music.
	 * 
	 * @param musicName
	 *            The name of the music to be played, without the extension
	 *            (example: if a music has "music_1.ogg" as filename, then the
	 *            expected musicName is "music_1").
	 */
	public void setMusic(String musicName) {
		this.musicName = musicName;
		this.music = assets.get("data/audio/" + musicName + ".ogg", Music.class);
		music.setLooping(true);
		music.setVolume(volume);
		music.play();
	}

	/**
	 * Stops the current background music.
	 */
	public void stop() {
		this.musicName = "";
		music.stop();
	}

	/**
	 * Plays a sound effect.
	 * 
	 * @param soundName
	 *            The name of the sound effect to be played, without the
	 *            extension (example: if a sound effect has "effect_1.ogg" as
	 *            filename, then the expected soundName is "effect_1").
	 */
	public void playSound(String soundName) {
		assets.get("data/audio/" + soundName + ".ogg", Sound.class).play(volume);
	}

	public void setVolume(float newVolume) {
		this.volume += newVolume;

		if (volume > 1)
			volume = 1;
		if (volume < 0)
			volume = 0;

		music.setVolume(volume);
	}

	public float getVolume() {
		return volume;

	}

	/**
	 * Returns the volume as a string representation of an integer between 0 and
	 * 100.
	 */
	public String getVolumeNumber() {
		int volume = (int) (this.volume * 100);
		return String.valueOf(volume);
	}

	public Music getMusic() {
		return music;
	}

	public String getMusicName() {
		return musicName;
	}

}
