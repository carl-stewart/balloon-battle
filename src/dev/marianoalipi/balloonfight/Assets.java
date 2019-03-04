package dev.marianoalipi.balloonfight;

import java.awt.image.BufferedImage;

/**
 *
 * @author MarianoAlipi
 */
public class Assets {

	public static BufferedImage background;

    public static void init() {
    	
        background = ImageLoader.loadImage("assets/images/background.png");

    }
}
