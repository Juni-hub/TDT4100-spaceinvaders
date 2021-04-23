package spaceInvaders;

public class Player {
	
	private int playerWidth = 50;
	private int posx = 0;
	private int direction;
	private int speed = 10;
	private String name;
	private Board board;
	
	public Player(String name, Board board) {
		this.setName(name);
		this.board = board;
		this.board.setPlayer(this);
	}
	

	public void move() {
		this.posx += speed*direction;
		if(this.posx < -((board. getBoardWidth() - playerWidth) / 2)) {
			this.posx = -(board.getBoardWidth() - playerWidth) / 2;
		} else if(this.posx > ((board.getBoardWidth() - playerWidth) / 2)) {
			this.posx = (board.getBoardWidth() - playerWidth) / 2;
		}
	}
		

	public int getPosx() {
		return posx;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}

