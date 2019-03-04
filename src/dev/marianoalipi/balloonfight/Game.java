package dev.marianoalipi.balloonfight;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import dev.marianoalipi.balloonfight.menu.MainMenu;
import dev.marianoalipi.balloonfight.menu.Menu;

/**
 * 
 * @author MarianoAlipi
 *
 */
public class Game implements Runnable {

	private BufferStrategy bs;
    private Graphics g;
    private Graphics2D g2d;
    private Display display;
    String title;
    private int width;
    private int height;
    private int splashFrames = 70; // the duration of the splash screen fade out
    private Thread thread;
    private boolean running;        // sets up the game
    private boolean splashScreenDisplayed; // whether the splash screen has been displayed
    private boolean showSplash = true;	// whether or not to show the splash screen
    private boolean paused;         // to pause the game
    private Menu menu;				// to set the current menu or no menu
    private KeyManager keyManager;	// keyboard input
	
    // Specialized single-use variables
    private int splashFramesCounter = -1;
    private float alpha = 1f; // the transparency of the rendered images

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        setRunning(false);
        splashScreenDisplayed = false;
        setPaused(false);
        keyManager = new KeyManager();
        if (!showSplash) {
        	splashScreenDisplayed = true;
        	setMenu(new MainMenu(this, getKeyManager()));
        }
    }
    
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();

        //starts to listen the keyboard input
        display.getJframe().addKeyListener(keyManager);
    }

    @Override
    public void run() {
        init();
        //frames per second
        int fps = 60;
        double timeTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        
        while (running) {
            now = System.nanoTime();
            //accumulates times in delta
            delta += (now - lastTime) / timeTick;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }
    
    private void tick() {

        // Get keyboard input
        // keyManager.tick();
        
        if (getMenu() != null) {
        	getMenu().tick();
        }

    }

    private void render() {
        //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /*if its null, we define one with 3 buffers to display images of the game but 
        after clearing the Rectangle, getting the grapic object from the buffer 
        strategy element. show the graphic and dispose it to the trash system.
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
        	
        	g = bs.getDrawGraphics();
        	
        	if (!splashScreenDisplayed) {
                
            	g2d = (Graphics2D) g;
            	g2d.setColor(Color.black);
            	
            	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        		g2d.fillRect(0, 0, width, height);
            	
        		// Using one counter for two things:
        		// First count from -1 to -120 to keep the splash screen at 100% alpha. Then set the counter to 0 and count up to splashFrames.
        		if (splashFramesCounter < 0) {
        			g2d.drawImage(Assets.splash, 179, height / 3, 443, 143, null);
	            	if (splashFramesCounter < 0 && splashFramesCounter > -120) {
	            		splashFramesCounter--;
	            	} else {
	            		splashFramesCounter = 0;
	            	}
        		} else {
	            	if (splashFramesCounter >= 0 && splashFramesCounter <= splashFrames) {
	            		
	            		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	                	g2d.drawImage(Assets.splash, 179, height / 3, 443, 143, null);
	                	alpha = (alpha - 0.01f < 0f) ? 0f : alpha - 0.02f;
	                	splashFramesCounter++;
	            	} else {
	            		splashScreenDisplayed = true;
	            		g.setColor(Color.black);
	            		g.fillRect(0, 0, width, height);
	            		setMenu(new MainMenu(this, getKeyManager()));
	            	}
        		}
            	
        	} else {
	            
	            
	            /* This draws an image with 50% alpha. Will be useful later.
	            Graphics2D g2d = (Graphics2D) g;
	            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
	            g2d.drawImage(Assets.title, 200, 450, 100, 50, null);
	            */
        	}
        	
        	if (getMenu() != null) {
        		getMenu().render(g);
        	}
	        // Prevents stutter on Linux.
	        Toolkit.getDefaultToolkit().sync();
	        bs.show();
	        g.dispose();
        }
    }

    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                //waits till thread dies
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    /**
	 * @return the paused
	 */
	public boolean isPaused() {
		return paused;
	}
	
	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}


	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @param paused the paused to set
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	public KeyManager getKeyManager() {
        return keyManager;
    }
    
}
