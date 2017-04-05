package main.info;

import lc.kra.system.mouse.event.GlobalMouseEvent;
import main.writer.Action;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recorder {

	
	private int mouseX =0;
	private int mouseY =0;
	private int screenHight = 0;
	private int sceenWidth = 0;
	private long programmRunning = 0;
	private String action = "idle";
	private File save;
	private BufferedWriter writer;
	private static Recorder r=null;
	private ArrayList<Action> actions = new ArrayList<Action>();
	private long time = Long.MAX_VALUE;
	private Recorder() throws IOException{
		save = new File("RecordedMouseMovment.txt");
		writer = new BufferedWriter(new FileWriter(save));
	}
	
	public static Recorder getInstance(){
		if(r==null){
			try {
				r = new Recorder();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		return r;
	}

	public void startActions(Robot robot){
		try {
			Action.PerformActions(actions, robot);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void addAction(Integer event){
		long pause = 0;
		if(time!=Long.MAX_VALUE){
			pause = System.currentTimeMillis()-time;
		}
		time = System.currentTimeMillis();
		actions.add(new Action(getMouseX(),getMouseY(),event,pause));
	}


	
	public int getMouseX() {
		mouseX = MouseInfo.getPointerInfo().getLocation().x;
		return mouseX;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	public int getMouseY() {
		mouseY = MouseInfo.getPointerInfo().getLocation().y;
		return mouseY;
	}
	public void setMouseY(int mouseY) {
		
		this.mouseY = mouseY;
	}
	public int getScreenHight() {
		screenHight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		return screenHight;
	}
	public void setScreenHight(int screenHight) {
		this.screenHight = screenHight;
	}
	public int getSceenWidth() {
		sceenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		return sceenWidth;
	}
	public void setSceenWidth(int sceenWidth) {
		this.sceenWidth = sceenWidth;
	}
	public long getProgrammRunning() {
		return programmRunning;
	}
	public void setProgrammRunning(long programmRunning) {
		this.programmRunning = programmRunning;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		try {
			writer.write(getMouseX()+","+getMouseY()+"\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Recorder \n[getMouseX()=" + getMouseX() + ",\n getMouseY()=" + getMouseY() + ",\n getScreenHight()="
				+ getScreenHight() + ",\n getSceenWidth()=" + getSceenWidth() + ",\n getProgrammRunning()="
				+ getProgrammRunning() + ",\n getAction()=" + getAction() + "]";
	}


	public void deleteLastAction() {
		actions.remove(action.length()-1);
	}
}
