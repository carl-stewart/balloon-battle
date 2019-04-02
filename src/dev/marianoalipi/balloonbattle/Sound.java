package dev.marianoalipi.balloonbattle;

import java.applet.Applet;
import java.applet.AudioClip;

@SuppressWarnings("deprecation")
public class Sound {
	
	public static final Sound flap		= new Sound("assets/sounds/flap.wav"),
							  hit		= new Sound("assets/sounds/hit.wav"),
							  navigate	= new Sound("assets/sounds/menuNavigate.wav"),
							  silence	= new Sound("assets/sounds/silence.wav");

	private AudioClip clip;

	private Sound(String file) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(file));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start(); // starts the thread
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}