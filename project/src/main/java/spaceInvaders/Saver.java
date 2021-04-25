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
	private String path;
	
	public Saver(String path) {
		// TODO Auto-generated constructor stub
		this.path = rootPath + path;
	}
	
	@Override
	public boolean writeNameToFile(String string) {
		for (int i = 0; i < string.length();i++) {
			if (Character.isLetter(string.charAt(i)) == false && string.charAt(i) != ' ' && string.charAt(i) != '\n') {
				System.out.println("Ugyldig navn");
				return false;
			}
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
			writer.write(string + ";");
			writer.close();
		} catch (IOException e) {
			System.out.println("An IO-exception occured");
			System.out.println(e);
		} catch(Exception e) {
			System.out.println("An unknown error occured!");
			System.out.println(e);
		} return true;
		
	}
	
	@Override
	public boolean writeScoreToFile(String string) {
		try {
			int score = Integer.valueOf(string);
		} catch (Exception e){
			System.out.println(e);
			throw new IllegalArgumentException("Not a number");
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
			writer.write (string + "\n");
			writer.close();
			return true;
		} catch (IOException e) {
			System.out.println("An IO-exception occured");
			System.out.println(e);
		} catch(Exception e) {
			System.out.println("An unknown error occured!");
			System.out.println(e);
		}
		return false;
	}
	
	@Override
	public List<String> readFromFile() {
		List<String> content = new ArrayList<String>();
		try (Scanner scanner = new Scanner (new FileReader(path))) {
			while (scanner.hasNextLine()) {
				content.add(scanner.nextLine());
			}
			return content;
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
			if(content.get(i).contains(";")) {
				String[] nameScore = content.get(i).split(";");
				String name = nameScore[0];
				try {
					int score = Integer.valueOf(nameScore[1]);
					if(score > currentMax) {
						winner = name;
						currentMax = score;
					}
				} catch(Exception e) {
					System.out.println("File corrupted");
					System.out.println(e);
				}
			}
		}
		return ("HIGHEST SCORE OF ALL TIME \nPlayer: " + winner + "\nScore: " + currentMax);
	}
}
