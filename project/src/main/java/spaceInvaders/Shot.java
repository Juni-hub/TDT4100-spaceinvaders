package spaceInvaders;

public class Shot {
	
	private double posx;
	private double posy;
		
	public Shot(double posx, double posy) {
		this.posx = posx;
		this.posy = posy;
	}
	
	public Shot(double posx) {
		this.posx = posx;
		this.posy = 0;
	}

		
	public void shoot() {
		if (getPosy() == 0) {
			for (int i = 0; i<10;i++) {
				setPosy(this.posy+1);
					/*hvis treff gjør alien død*/
			}
		} else {
			for (int i = 0; i<getPosy();i++) {
				setPosy(this.posy-1);
			}
				/* hvis treff gjør spiller død*/
		}
	}
		
	private boolean checkIfAlien() {
		// Do something
		return true;
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}
		
		
		
}

