package spaceInvaders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

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
	public void playerShoots() {
		for(int j=0; j < 5; j++) {
			for(int i=0; i < 12; i++) {
				assertEquals(null, player.shoot());
				player.addTimeSinceLastShot();
			}
			assertEquals(Shot.class, player.shoot().getClass());
		}
	}
	
	@Test
	public void moveShots() {
		Shot shot = new Shot(100, board);
		for(int i=0; i<10; i++) {
			assertEquals(400-player.getPlayerWidth()-i*shot.getShotSpeed(), shot.getPosy());
			shot.moveShot();
		}
	}
	
	@Test
	public void shotHitsAlien1() {
		Alien alien = new Alien(50, 50, 25);
		board.getAlienGroup().add(alien);
		Shot shot = new Shot(50,board);
		for(int i=0; i<100; i++) {
			if(Math.abs(shot.getPosx()-alien.getPosx()) < alien.getRadius() + shot.getRadius()) {
				assertEquals(alien, shot.hitsAlien());
			}
			else {
				assertEquals(null,shot.hitsAlien());
			}
		}
	}
	
	@Test
	public void shotHitsAlien2() {
		Alien alien = new Alien(50, 50, 25);
		board.getAlienGroup().add(alien);
		Shot shot = new Shot(80,board);
		for(int i=0; i<100; i++) {
			if(shot.getPosy() == alien.getPosx()) {
				assertEquals(alien, shot.hitsAlien());
			}
			else {
				assertEquals(null,shot.hitsAlien());
			}
		}
	}
	
	@Test
	public void shotHitsAlien3() {
		Alien alien = new Alien(50, 300, 25);
		Alien alien2 = new Alien(50, 250, 25);
		board.getAlienGroup().add(alien);
		Shot shot = new Shot(80,board);
		double distSquared;
		for(int i=0; i<10; i++) {
			distSquared = (shot.getPosx() - alien.getPosx())*(shot.getPosx() - alien.getPosx()) + (shot.getPosy() - alien.getPosy())*(shot.getPosy() - alien.getPosy());
			if(distSquared <= (shot.getRadius() + alien.getRadius())*(shot.getRadius()+alien.getRadius())) {
				assertEquals(alien, shot.hitsAlien());
			}
			else {
				assertEquals(null, shot.hitsAlien());
			}
		}
	}
	
	
	@Test
	public void alienMoves() {
		Alien alien1 = new Alien(25, 25, 25);
		double lastPosX = 25;
		double lastPosY = 25;
		double pushDist = 2 * alien1.getRadius();
		board.getAlienGroup().add(alien1);
		board.pushAliens(); // Pushes down
		assertEquals(25, alien1.getPosx());
		assertEquals(lastPosY + pushDist, alien1.getPosy());
		lastPosY = alien1.getPosy();
		board.pushAliens(); // Pushes right
		assertEquals(lastPosX + pushDist, alien1.getPosx());
		assertEquals(lastPosY, alien1.getPosy());
		lastPosX = alien1.getPosx();
		board.pushAliens(); // Pushes down
		assertEquals(lastPosX, alien1.getPosx());
		assertEquals(lastPosY + pushDist, alien1.getPosy());
		lastPosY = alien1.getPosy();
		board.pushAliens(); // Pushes left
		assertEquals(lastPosX - pushDist, alien1.getPosx());
		assertEquals(lastPosY, alien1.getPosy());
	}
	
	@Test
	public void alienOutOfBounds() {
		Alien alien = new Alien(25,25,25);
		Assertions.assertThrows(IllegalStateException.class, () -> {
			alien.setPosx(600);
		});
		Assertions.assertThrows(IllegalStateException.class, () -> {
			alien.setPosy(400);
		});
		Assertions.assertThrows(IllegalStateException.class, () -> {
			alien.setPosx(0);
		});
		Assertions.assertThrows(IllegalStateException.class, () -> {
			alien.setPosy(0);
		});	
	}
	
	@Test
	public void testWriteNameToSave() {
		String rootPath = new File("").getAbsolutePath();
		String path = rootPath + "/src/test/java/spaceInvaders/gameScoreTest";
		File newTestSaveFile = new File(path);
		Saver saver = new Saver("/src/test/java/spaceInvaders/gameScoreTest");
		assertEquals(true, saver.writeNameToFile("Ola"));
		assertEquals(true, saver.writeNameToFile("Ola Halvorsen"));
		assertEquals(false, saver.writeNameToFile("Ola12"));
		assertEquals(false, saver.writeNameToFile("Ola-Halvorsen"));
		newTestSaveFile.delete();
	}
	
	@Test
	public void testWriteNameAndScore() {
		String rootPath = new File("").getAbsolutePath();
		String path = rootPath + "/src/test/java/spaceInvaders/gameScoreTest";
		File newTestSaveFile = new File(path);
		Saver saver = new Saver("/src/test/java/spaceInvaders/gameScoreTest");
		assertEquals(true, saver.writeNameToFile("Ola"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			saver.writeScoreToFile("Ola");
		});
		assertEquals(true, saver.writeScoreToFile("120"));
		newTestSaveFile.delete();
	}
	
	
	@Test
	public void testHighScore() {
		String rootPath = new File("").getAbsolutePath();
		String path = rootPath + "/src/test/java/spaceInvaders/gameScoreTest";
		File newTestSaveFile = new File(path);
		Saver saver = new Saver("/src/test/java/spaceInvaders/gameScoreTest");
		saver.writeNameToFile("Ola");
		saver.writeScoreToFile("100");
		saver.writeNameToFile("Per");
		saver.writeScoreToFile("2000");
		saver.writeNameToFile("Jan");
		saver.writeScoreToFile("30");
		saver.writeNameToFile("Ole");
		saver.writeScoreToFile("400");
		assertEquals("HIGHEST SCORE OF ALL TIME \nPlayer: " + "Per" + "\nScore: " + "2000", saver.getHighScore());
		newTestSaveFile.delete();
	}
	
}
