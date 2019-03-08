package dev.marianoalipi.balloonbattle;

import java.awt.image.BufferedImage;

/**
 *
 * @author MarianoAlipi
 */
public class Assets {

	public static BufferedImage background, splash, title, github, balloon, mainMenuOptionsImg;
	private static SpriteSheet mainMenuOptionsSS;
	public static BufferedImage[] mainMenuOptions;
	
	public static int textScale = 4;

    public static void init() {
    	
        background = ImageLoader.loadImage("assets/images/background.png");
        splash = ImageLoader.loadImage("assets/images/splash.png");
        title = ImageLoader.loadImage("assets/images/title_pixel.png");
        github = ImageLoader.loadImage("assets/images/github.png");
        balloon = ImageLoader.loadImage("assets/images/balloon.png");
        mainMenuOptionsImg = ImageLoader.loadImage("assets/images/menu_options.png");
        
        mainMenuOptionsSS = new SpriteSheet(mainMenuOptionsImg);
        mainMenuOptions = new BufferedImage[3];
        
        for (int i = 0; i < mainMenuOptions.length; i++)
        	mainMenuOptions[i] = mainMenuOptionsSS.crop(0, i * 7, mainMenuOptionsImg.getWidth(), 7);

    }
}
