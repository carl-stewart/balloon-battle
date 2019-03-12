package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.marianoalipi.balloonbattle.Game;

public abstract class Entity {

	protected int x, y, width, height;
	protected double speed, xSpeed, ySpeed;
	protected BufferedImage sprite;
	protected Rectangle hitbox;
	protected Game game;
	//protected static final int abc;
	protected static final double GRAVITY = 0.3, MAX_SPEED = 7;
	protected boolean grounded, visible, spawned;

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
	
	public double getSpeed() {
		return speed;
	}
	
	public double getxSpeed() {
		return xSpeed;
	}
	
	public double getySpeed() {
		return ySpeed;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public boolean isGrounded() {
		return grounded;
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
	
	public void setSpeed(double speed) {
		this.speed = (Math.abs(speed) > MAX_SPEED) ? (speed >= 0) ? MAX_SPEED : -1 * MAX_SPEED : speed;
		//this.speed = speed;
	}
	
	public void setxSpeed(double xSpeed) {
		this.xSpeed = (Math.abs(xSpeed) > MAX_SPEED) ? (xSpeed >= 0) ? MAX_SPEED : -1 * MAX_SPEED : xSpeed;
		//this.xSpeed = xSpeed;
	}
	
	public void setySpeed(double ySpeed) {
		this.ySpeed = (Math.abs(ySpeed) > MAX_SPEED) ? (ySpeed >= 0) ? MAX_SPEED : -1 * MAX_SPEED : ySpeed;
		//this.ySpeed = ySpeed;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

}