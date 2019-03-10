package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.marianoalipi.balloonbattle.Game;

public abstract class Entity {

	protected int x, y, width, height, speed, xSpeed, ySpeed;
	protected BufferedImage sprite;
	protected Rectangle hitbox;
	protected Game game;
	protected static final int GRAVITY = 1, MAX_SPEED = 4;
	protected boolean visible, spawned;
	
	public Entity() {
		this.x = 0;
		this.y = 0;
		this.width = 1;
		this.height = 1;
		this.speed = 0;
		this.xSpeed = 0;
		this.ySpeed = 0;
		this.sprite = null;
		this.hitbox = new Rectangle(x, y, width, height);
	}
	
	public Entity(int x, int y, int width, int height, Game game) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = 0;
		this.sprite = null;
		this.game = game;
		this.hitbox = new Rectangle(x, y, width, height);
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	
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
	
	public int getSpeed() {
		return speed;
	}
	
	public int getxSpeed() {
		return xSpeed;
	}
	
	public int getySpeed() {
		return ySpeed;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public boolean isSpawned() {
		return spawned;
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
	
	public void setSpeed(int speed) {
		this.speed = (speed > MAX_SPEED) ? MAX_SPEED : speed;
	}
	
	public void setxSpeed(int xSpeed) {
		this.xSpeed = (xSpeed > MAX_SPEED) ? MAX_SPEED : xSpeed;
	}
	
	public void setySpeed(int ySpeed) {
		this.ySpeed = (ySpeed > MAX_SPEED) ? MAX_SPEED : ySpeed;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

}