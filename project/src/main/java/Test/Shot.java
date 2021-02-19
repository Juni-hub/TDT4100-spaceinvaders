package Test;

public class Shot {
	private int posx;
	private int posy;
		
	public Shot(int pos) {
		this.posx = pos;
		this.posy = 0;
	}
		
	public Shot(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
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

}
