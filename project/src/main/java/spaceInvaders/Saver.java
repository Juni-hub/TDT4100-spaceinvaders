package spaceInvaders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Saver implements fileWrite{
	private String rootPath = new File("").getAbsolutePath();
	private String path = rootPath + "/src/main/java/spaceInvaders/gameScore";
	
	@Override
	public void writeToFile(String string) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
			writer.write(string);
			System.out.println("Wrote: " + string);
			writer.close();
		} catch (IOException e) {
			System.out.println("An IO-exception occured");
			System.out.println(e);
		} catch(Exception e) {
			System.out.println("An unknown error occured!");
			System.out.println(e);
		}
		
	}
	@Override
	public List<String> readFromFile() {
		List<String> content = new ArrayList<String>();
		try (Scanner scanner = new Scanner (new FileReader(path))) {
			while (scanner.hasNextLine()) {
				content.add(scanner.nextLine());
			return content;
			}
		} catch (IOException e) {
			System.out.println("An IO-exception occured");
			System.out.println(e);
		} catch(Exception e) {
			System.out.println("An unknown error occured!");
			System.out.println(e);
		} return content;
	}
	
	@Override
	public String getHighScore(){
		List<String> content = readFromFile();
		String winner = "";
		int currentMax = 0;
		for(int i=0; i<content.size(); i++) {
			String[] nameScore = content.get(i).split(";");
			String name = nameScore[0];
			int score = Integer.valueOf(nameScore[1]);
			if(score > currentMax) {
				winner = name;
				currentMax = score;
			}
		}
		return ("Player: " + winner + "\nScore: " + currentMax);
	}
}
