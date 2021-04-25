package spaceInvaders;

public class Player {
	
	private int playerWidth = 50;
	private double posx;
	private int direction;
	private double speed = 10;
	private String name;
	private Board board;
	//private double shotDelaySeconds = 0.4;
	private int timeBeetweenShots = 12;
	private int timeSinceLastShot;
	
	public int getTimeBeetweenShots() {
		return timeBeetweenShots;
	}
	
	public int getTimeSinceLastShot() {
		return timeSinceLastShot;
	}
	
	public Player(String name, Board board) {
		this.name = name;
		this.board = board;
	}
	
	public void move() {
		this.posx += speed*direction;
		if(this.posx < -((board. getBoardWidth() - this.playerWidth) / 2)) {
			this.posx = -(board.getBoardWidth() - this.playerWidth) / 2;
		} else if(this.posx > ((board.getBoardWidth() - this.playerWidth) / 2)) {
			this.posx = (board.getBoardWidth() - this.playerWidth) / 2;
		}		
	}
	
	public Shot shoot() {
		if(timeSinceLastShot >= timeBeetweenShots) {
			Shot shot = new Shot(this.posx,this.board);
	    	board.getShotGroup().add(shot);
	    	timeSinceLastShot = 0;
	    	return shot;
		}
		return null;
	}
	

	public double getPosx() {
		return posx;
	}

	public String getName() {
		return name;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getPlayerWidth() {
		return playerWidth;
	}
	
	public void addTimeSinceLastShot() {
		this.timeSinceLastShot += 1;
	}

	
}

