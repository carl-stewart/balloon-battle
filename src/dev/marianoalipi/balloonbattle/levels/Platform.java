package dev.marianoalipi.balloonbattle.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.entities.Player;

public class Platform {

	private int x, y, width, height;
	private Rectangle hitbox;
	private BufferedImage sprite;
	private boolean touching; 
	private Game game;
	private Player player;

	public Platform() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.hitbox = new Rectangle(x, y, width, height);
		this.sprite = null;
		this.touching = false;
		this.game = null;
		this.player = null;
	}

	public Platform(int x, int y, int width, int height, Game game) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hitbox = new Rectangle(x, y, width, height);
		this.sprite = null;
		this.touching = false;
		this.game = game;
		this.player = game.getPlayer();
	}

	public void tick() {

		this.player = game.getPlayer();

		if (getHitbox().intersects(player.getHitbox()) || getHitbox().intersects(player.getBalloons().getHitbox())) {
			// Touching from above
			if (player.getY() + player.getHeight() < getY() + getHeight() / 2) {
				player.setY(getY() - player.getHeight() + 1);
				player.setGrounded(true);
			}
			if (!touching) {
				touching = true;
				player.setySpeed(0);
			}
			
			// Other cases
			if (!player.isGrounded()) {
				// Touching the player's balloons (probably from below).
				if (getHitbox().intersects(player.getBalloons().getHitbox())) {
					if (player.getBalloons().getY() > getY() + getHeight() / 2) {
						player.setY(getY() + getHeight() + player.getBalloons().getHeight() + 1);
						player.setySpeed(Math.abs(player.getySpeed()) * -0.5);
					}
				}
				// Touching from right side
				if (player.getX() > getX() + getWidth() - 15) {
					if (player.getxSpeed() < 0)
						player.setxSpeed(player.getxSpeed() * -1);
					player.setX(player.getX() + 5);
				// Touching from left side
				} else if (player.getX() < getX() + 15) {
					if (player.getxSpeed() > 0)
						player.setxSpeed(player.getxSpeed() * -1);
					player.setX(player.getX() - 5);
				}
				
			}
		} else {
			touching = false;
		}
	}

	public void render(Graphics g) {
		//g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);

		// Placeholder renderer
		g.setColor(Color.green);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
}