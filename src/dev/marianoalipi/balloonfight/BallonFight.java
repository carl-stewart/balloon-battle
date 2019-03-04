package dev.marianoalipi.balloonfight;

/**
 * 
 * @author MarianoAlipi
 *
 */
public class BallonFight {

	private final static String title = "Ballon Fight";
	// Aspect ratio: 4:3
	private final static int width = 800, height = 600;
	
	public static void main(String[] args) {
		Game g = new Game(title, width, height);
		g.start();
	}

}
