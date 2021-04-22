package spaceInvaders;

public class Player {
	
	private int playerWidth = 50;
	private int boardWidth = 600;
	
	private double posx = 0;
	private String name;
	private Board board;
	
	public Player(String name) {
		this.setName(name);
	}
	
<<<<<<< HEAD
=======
	public void moveLeft() {
		if (posx != -300) {
			this.posx -=50;
		}
	}
	
	public void moveRight() {
		if (posx != 300) {
			this.posx +=50;
		}
	}
>>>>>>> refs/remotes/origin/Filbehandling

	public double getPosx() {
		return posx;
	}


	public void setPosx(double posx) {
		this.posx = posx;
	}


	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
}

