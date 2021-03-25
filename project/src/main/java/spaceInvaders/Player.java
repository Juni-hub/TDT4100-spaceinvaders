package spaceInvaders;

public class Player {
	
	private int life = 3;
	private double posx = 0;
	private String name;
	private Board board;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void hit() {
		setLife(this.life -1);
		if (getLife() == 0) {
			Board.gameOver();
		}
	}
	
	public void shoot() {
		Shot shot = new Shot(this.posx);
		shot.shoot();
	}
	
	public void moveLeft() {
		this.posx -=50;
	}
	
	public void moveRight() {
		this.posx +=50;
	}

	public double getPosx() {
		return posx;
	}


	public void setPos(double posx) {
		this.posx = posx;
	}


	public String getName() {
		return name;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
}

