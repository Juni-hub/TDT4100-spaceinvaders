package Test;

public class Alien {
	
	private boolean alive = true;
	private int posx;
	private int posy = 10;
	
	public Alien(int posx,int posy) {
		this.posx = posx;
	}
	
	public void dead() {
		this.alive = false;
	}
	
	public void skyt() {
		Shot shot = new Shot(this.posx, this.posy);
		shot.shoot();
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
}
