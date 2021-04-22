package spaceInvaders;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Saver implements fileWrite{
	private String rootPath = new File("").getAbsolutePath();
	private String path = rootPath + "/src/main/java/spaceInvaders/gameScore";
	
	@Override
	public void writeToFile(String string) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			writer.write(string);
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
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			List<String> content = new ArrayList<String>();
			while (reader.readLine() != null) {
				content.add(reader.readLine());
			}
			return content;
		} catch (IOException e) {
			System.out.println("An IO-exception occured");
			System.out.println(e);
		} catch(Exception e) {
			System.out.println("An unknown error occured!");
			System.out.println(e);
		} return null;
	}
}
