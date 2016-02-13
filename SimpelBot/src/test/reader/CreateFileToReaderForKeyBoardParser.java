package reader;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class CreateFileToReaderForKeyBoardParser {

	
	public void initFile() throws IOException, AWTException, InterruptedException {
		File f = new File("TestKeyBoardFile.txt");
		BufferedWriter bf = new BufferedWriter(new FileWriter(f));
		bf.write("100,100");
		bf.newLine();
		bf.write("T");
		bf.newLine();
		bf.write("H\n\na\nl\nl\no\n \nW\ne\nl\nt\n");
		bf.close();
		Thread.sleep(1000*5);
	
		
		
	}

	@Test
	public void testParser() throws InterruptedException, AWTException{
		ParseKeyBoardInput reader = new ParseKeyBoardInput("TestKeyBoardFile.txt", new Robot());
		for (int i = 0; i < 10; i++) {
			reader.performeLine();
			Thread.sleep(100*1);
		}
		
	}
}
