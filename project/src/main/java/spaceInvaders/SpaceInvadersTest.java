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
		player = board.getPlayer();
	}
	
	@Test
	public void testSetUpBoard() {
		assertEquals(board.getBoardWidth(), 600);
		assertEquals(board.getBoardHeight(), 400);
		
	}
	
	@Test
	public void movePlayer() {
		// sjekke at kommer på riktig sted og hva som skjer når den går utenfor banen
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
