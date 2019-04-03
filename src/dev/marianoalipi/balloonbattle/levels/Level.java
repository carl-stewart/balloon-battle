package dev.marianoalipi.balloonbattle.levels;

public class Level {

	private byte id;
	
	
	public Level() {
		this.id = -1;
	}
	
	public Level(byte id) {
		this.id = id;
	}
	
	public byte getId() {
		return id;
	}
	
	public void setId(byte id) {
		this.id = id;
	}
}