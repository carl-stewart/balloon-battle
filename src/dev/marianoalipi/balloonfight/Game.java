package dev.marianoalipi.balloonfight;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

/**
 * 
 * @author MarianoAlipi
 *
 */
public class Game implements Runnable {

	private BufferStrategy bs;
    private Graphics g;
    private Display display;
    String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;        //sets up the game
    private boolean paused;         // to pause the game
    private KeyManager keyManager;	// keyboard input
	
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        setRunning(false);
        setPaused(false);
        keyManager = new KeyManager();
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
        keyManager.tick();

    }

    private void render() {
        //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /*if its null, we define one with 3 buffers to display images of the game but 
        after clearing the Rectangle, getting the grapic object frome the buffer 
        strategy element. show the graphic and dispose it to the trash system.
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            
            //g.drawImage(Assets.background, 0, 0, width, height, null);
            g.setColor(Color.black);
            g.fillRect(0, 0, width, height);

            g.drawImage(Assets.title, 100, 20, 600, 250, null);
            g.drawImage(Assets.github, 179, height - 50, 443, 31, null);
            
            /* This draws an image with 50% alpha. Will be useful later.
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(Assets.title, 200, 450, 100, 50, null);
            */
            
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
                //waits till thread dieas
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
