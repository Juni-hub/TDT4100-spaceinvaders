package spaceInvaders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpaceInvadersTest {
	private Player player;
	private Board board;
	
	@BeforeEach
	public void setUp() {
		board = new Board();
		player = new Player("Ola", board);
	}
	
	@Test
	public void testSetUpBoard() {
		
	}
	
	@Test
	public void movePlayer() {
		// sjekke at kommer på riktig sted og hva som skjer når den går utenfor banen
		assertEquals(player.getPosx(), 0);
		assertEquals(player.getDirection(), 0);
		player.setDirection(1);
		for(int i=0; i < 1000; i++) {
			assertEquals(player.getPosx(), Math.min(i*10, board.getBoardWidth()/2));
			player.move();
		}
	}
	
	@Test
	public void testSave() {
		String rootPath = new File("").getAbsolutePath();
		String path = rootPath + "/src/main/java/spaceInvaders/gameScore";
		Saver saver = new Saver();
		saver.writeToFile("Hei");
		List<String> testList = new ArrayList<String>();
		testList.add("Hei");
		Assertions.assertTrue(saver.readFromFile().equals(testList));
	}
	
	@AfterAll
	static void tearDown() {
		String rootPath = new File("").getAbsolutePath();
		String path = rootPath + "/src/main/java/spaceInvaders/gameScore";
		File newTestSaveFile = new File(path);
		newTestSaveFile.delete();
	}
	
}
