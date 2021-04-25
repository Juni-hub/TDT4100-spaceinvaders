package spaceInvaders;


public class Shot {
	
	private double posx;
	private double posy;
	private double radius = 5;
	private double shotSpeed = 5;
	private Board board;
	
	public Shot(double posx, Board board) {
		this.posx = posx+300;
		this.posy = board.getBoardHeight() - board.getPlayer().getPlayerWidth();
		this.board = board;
	}
	
	public Alien hitsAlien() {
		if(board.getAlienGroup().size() != 0) {
			for (Alien alien : board.getAlienGroup()) {
				double dist = (alien.getPosx() - this.posx)*(alien.getPosx() - this.posx) + (alien.getPosy() - this.posy)*(alien.getPosy() - this.posy);
				if(dist <= (this.radius + alien.getRadius())*(this.radius + alien.getRadius())) {
					return alien;
				}
			}
		}
		return null;
	}
	
	public void moveShot() {
		this.posy -= shotSpeed;
		if (this.posy < -10) {
			board.removeShot(this);
		}
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
	
	
	public double getShotSpeed() {
		return shotSpeed;
	}
}

