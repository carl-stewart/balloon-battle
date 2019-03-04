package dev.marianoalipi.balloonfight;

import java.awt.image.BufferedImage;

/**
 *
 * @author MarianoAlipi
 */
public class Assets {

	public static BufferedImage background, title;

    public static void init() {
    	
        background = ImageLoader.loadImage("assets/images/background.png");
        title = ImageLoader.loadImage("assets/images/title_pixel.png");

    }
}
