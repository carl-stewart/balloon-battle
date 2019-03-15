package dev.marianoalipi.balloonbattle;

import java.awt.image.BufferedImage;

/**
 *
 * @author MarianoAlipi
 */
public class Assets {

	public static BufferedImage background, splash, title, github, balloon;
	private static SpriteSheet mainMenuOptionsSS;
	public static BufferedImage[] mainMenuOptions;
	private static SpriteSheet balloonsSS, playerFlySS, playerIdleSS, playerWalkSS;
	public static BufferedImage[] balloonsTwo, balloonsOne, playerFly, playerIdle, playerWalkLeft, playerWalkRight;
	
	public static int textScale = 4;

    public static void init() {
    	
        background = ImageLoader.loadImage("assets/images/background.png");
        splash = ImageLoader.loadImage("assets/images/splash.png");
        title = ImageLoader.loadImage("assets/images/title_pixel.png");
        github = ImageLoader.loadImage("assets/images/github.png");
        balloon = ImageLoader.loadImage("assets/images/balloon.png");
        
        // Main menu options
        mainMenuOptionsSS = new SpriteSheet(ImageLoader.loadImage("assets/images/menu_options.png"));
        mainMenuOptions = new BufferedImage[3];
        
        for (int i = 0; i < mainMenuOptions.length; i++)
        	mainMenuOptions[i] = mainMenuOptionsSS.crop(0, i * 7, mainMenuOptionsSS.getSheet().getWidth(), 7);
        
        balloonsSS = new SpriteSheet(ImageLoader.loadImage("assets/images/balloons.png"));
        balloonsTwo = new BufferedImage[6];
        balloonsOne = new BufferedImage[2];
        
        // Two balloons sprites
        for (int i = 0; i < 4; i++)
        	balloonsTwo[i] = balloonsSS.crop(i * 16, 0, 16, 12);
        // Repeat two frames to create a looping animation.
        balloonsTwo[4] = balloonsTwo[1];
        balloonsTwo[5] = balloonsTwo[2];
        
        // Single balloon sprites
        for (int i = 0; i < 2; i++)
        	balloonsOne[i] = balloonsSS.crop(i * 16, 12, 16, 12);
        
        // Player flying animation
        playerFlySS = new SpriteSheet(ImageLoader.loadImage("assets/images/playerFly.png"));
        playerFly = new BufferedImage[4];
        for (int i = 0; i < playerFly.length; i++)
        	playerFly[i] = playerFlySS.crop(i * 16, 0, 16, 12);
        
        // Player idle sprite
        playerIdleSS = new SpriteSheet(ImageLoader.loadImage("assets/images/playerIdle.png"));
        playerIdle = new BufferedImage[2];
        for (int i = 0; i < playerIdle.length; i++)
        	playerIdle[i] = playerIdleSS.crop(i * 16, 0, 16, 12);
        
        // Player walking animation
        playerWalkSS = new SpriteSheet(ImageLoader.loadImage("assets/images/playerWalk.png"));
        playerWalkLeft = new BufferedImage[4];
        playerWalkRight = new BufferedImage[4];
        
        for (int i = 0; i < playerWalkLeft.length; i++) {
        	playerWalkLeft[i] = playerWalkSS.crop(i * 16, 0, 16, 12);
        	playerWalkRight[i] = playerWalkSS.crop(i * 16, 12, 16, 12);
    	}        
    }
}
