package reader;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.KeyStroke;

public class ParseKeyBoardInput {

	private Robot robot;
	private BufferedReader reader;
	
	public ParseKeyBoardInput(String path, Robot robot){
		this.robot = robot;
		try {
			reader = new BufferedReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void performeLine() {
		try {
			
			String s = reader.readLine();
			int x =0;
			int y =0;
			String split[];
			if(s==null){
				return;
			} else if(s.contains(",")){
				split = s.split(",");
				x = Integer.parseInt(split[0]);
				y = Integer.parseInt(split[1]);
				robot.mouseMove(x, y);
			} else if(s.length()==1){
				KeyStroke ks = KeyStroke.getKeyStroke(s.charAt(0), 0);
				robot.keyPress(ks.getKeyCode());
				Thread.sleep(10);
				robot.keyRelease(ks.getKeyCode());
			} else if(s.equals("left")){
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(10);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			} else if(s.equals("right")){
				robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
				Thread.sleep(10);
				robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			}
				
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
