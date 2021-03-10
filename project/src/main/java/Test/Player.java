package Test;

public class Player {
	
	private int life = 3;
	private int pos = 5;
	private String name;
	
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
		Shot shot = new Shot(this.pos);
		shot.shoot();
	}
	
	public void moveLeft() {
		pos -=1;
	}
	
	public void moveRight() {
		pos +=1;
	}

	public int getPos() {
		return pos;
	}


	public void setPos(int pos) {
		this.pos = pos;
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
	
	
}

