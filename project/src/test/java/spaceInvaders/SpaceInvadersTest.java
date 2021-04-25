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
		assertEquals(null, board.getEndGame());
		board.startGame();
		assertEquals(false, board.getEndGame());
		board.gameOver();
		assertEquals(true, board.getEndGame());
	}
	
	@Test
	public void movePlayerRight() {
		// sjekke at kommer på riktig sted og hva som skjer når den går utenfor banen
		assertEquals(0, player.getPosx());
		assertEquals(0, player.getDirection());
		player.setDirection(1);
		for(int i=0; i < 1000; i++) {
			assertEquals(Math.min(i*10, board.getBoardWidth()/2 - player.getPlayerWidth()/2), player.getPosx());
			player.move();
		}
	}
	
	@Test
	public void movePlayerLeft() {
		// sjekke at kommer på riktig sted og hva som skjer når den går utenfor banen
		assertEquals(0, player.getPosx());
		assertEquals(0, player.getDirection());
		player.setDirection(-1);
		for(int i=0; i < 1000; i++) {
			assertEquals(Math.max(-i*10, -(board.getBoardWidth()/2 - player.getPlayerWidth()/2)), player.getPosx());
			player.move();
		}
	}
	
	@Test
	public void playerShoot() {
		player.setDirection(1);
		for(int i=0; i<10; i++) {
			Shot shot = player.shoot();
			assertEquals(player.getPosx()+300, shot.getPosx());
			// Player operates with -300 to 300 in x direction, while shot uses 0 to 600
			assertEquals(i+1, board.getShotGroup().size());
			player.move();
		}
	}
	
	@Test
	public void shotMoves() {
		Shot shot = player.shoot();
		assertEquals(player.getPosx()+300, shot.getPosx());
		assertEquals(board.getBoardHeight()-player.getPlayerWidth(), shot.getPosy());
		double newShotPosY = shot.getPosy() - 5.0;
		shot.moveShot();
		assertEquals(newShotPosY, shot.getPosy());
	}
	
	@Test
	public void shotHitsAlien() {
		
	}
	
	@Test
	public void alienMoves() {
		
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
