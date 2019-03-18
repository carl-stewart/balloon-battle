package dev.marianoalipi.balloonbattle;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 *
 * @author MarianoAlipi
 */
public class Assets {

	public static BufferedImage background, splash, title, github, balloon;
	private static SpriteSheet mainMenuOptionsSS;
	public static BufferedImage[] mainMenuOptions;
	private static SpriteSheet balloonsSS, playerFlySS, playerIdleSS, playerWalkSS, playerFallingSS, enemyFlySS, enemyIdleSS;
	public static BufferedImage[] balloonsTwo, balloonsOne,
								  playerFly, playerFlapLeft, playerFlapRight,
								  playerIdle, playerWalkLeft, playerWalkRight, playerFalling,
								  enemyFly, enemyFlapLeft, enemyFlapRight,
								  enemyIdle;
	
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
        playerFlapLeft = Arrays.copyOfRange(playerFly, 0, 2);
        playerFlapRight = Arrays.copyOfRange(playerFly, 2, 4);
        
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
        
        // Player falling animation
        playerFallingSS = new SpriteSheet(ImageLoader.loadImage("assets/images/playerFalling.png"));
        playerFalling = new BufferedImage[4];
        for (int i = 0; i < playerFalling.length; i++)
        	playerFalling[i] = playerFallingSS.crop(i * 16, 0, 16, 12);
        
        // Enemy flying animation
        enemyFlySS = new SpriteSheet(ImageLoader.loadImage("assets/images/enemyFly.png"));
        enemyFly = new BufferedImage[4];
        for (int i = 0; i < enemyFly.length; i++)
        	enemyFly[i] = enemyFlySS.crop(i * 16, 0, 16, 12);
        enemyFlapLeft = Arrays.copyOfRange(enemyFly, 0, 2);
        enemyFlapRight = Arrays.copyOfRange(enemyFly, 2, 4);
        
        // Enemy idle
        enemyIdleSS = new SpriteSheet(ImageLoader.loadImage("assets/images/enemyIdle.png"));
        enemyIdle = new BufferedImage[2];
        for (int i = 0; i < enemyIdle.length; i++) {
        	enemyIdle[i] = enemyIdleSS.crop(i * 16, 0, 16, 12);
        }
    }
}
