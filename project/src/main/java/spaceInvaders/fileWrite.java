package spaceInvaders;

import java.util.List;

public interface fileWrite {
	public boolean writeNameToFile(String string);
	
	public boolean writeScoreToFile(String string);
	
	public List<String> readFromFile();
	
	public String getHighScore();
}
