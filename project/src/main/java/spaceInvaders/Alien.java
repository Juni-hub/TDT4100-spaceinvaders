package spaceInvaders;


public class Alien {
	
	private double posx;
	private double posy;
	private double radius;
	
	public Alien(double posx,double posy, double radius) {
		// this.posx = posx;
		// this.posy = posy;
		setPosx(posx);
		setPosy(posy);
		this.radius = radius;
	}

	public double getPosx() {
		return posx;
	}

	public double getPosy() {
		return posy;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setPosx(double posx) {
		if(posx > 600 - radius || posx < radius) {
			throw new IllegalStateException("Out of bounds");
		}
		this.posx = posx;
	}
	
	public void setPosy(double posy) {
		if(posy > 400 - radius || posy < radius) {
			throw new IllegalStateException("Out of bounds");
		}
		this.posy = posy;
	}
	
}
