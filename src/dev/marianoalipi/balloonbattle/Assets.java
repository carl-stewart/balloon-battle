package dev.marianoalipi.balloonbattle;

import java.awt.image.BufferedImage;

/**
 *
 * @author MarianoAlipi
 */
public class Assets {

	public static BufferedImage background, splash, title, github, balloon;

    public static void init() {
    	
        background = ImageLoader.loadImage("assets/images/background.png");
        splash = ImageLoader.loadImage("assets/images/splash.png");
        title = ImageLoader.loadImage("assets/images/title_pixel.png");
        github = ImageLoader.loadImage("assets/images/github.png");
        balloon = ImageLoader.loadImage("assets/images/balloon.png");

    }
}
