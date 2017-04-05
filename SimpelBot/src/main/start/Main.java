package main.start;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.SyncFailedException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.info.InfoView;
import main.info.Recorder;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import main.reader.MouseReader;

public class Main {

	public static void main(String[] args) throws AWTException, InterruptedException {
        Robot r = new Robot();
        MouseReader reader = new MouseReader(Recorder.getInstance());
        System.out.println("Recording startet");
        Scanner sc = new Scanner(System.in);
        String line = "";
        while(true){
            System.out.println("If you finish enter 1 for retry 0");
            reader.startMouseListener();
            line = sc.nextLine();
            System.out.println(line);
            if(line.equals("1")){
                System.out.println("1");
                break;
            } else {
                System.out.println("!1");
                reader.endRecording();
            }
        }
        System.out.print("Enter time to start");
        int i = sc.nextInt();//5min = 300 000
        while (true) {
            Thread.sleep(i);
            System.out.print("Actions startet");
            Recorder.getInstance().startActions(r);
        }

     /*
        for (int i = 0; i < 1000; i++) {
			try {

				r.mousePress(InputEvent.BUTTON1_MASK);
				Thread.sleep(70);
				r.mouseRelease(InputEvent.BUTTON1_MASK);
				//Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//iv.update();
		}*/
		//imageEditing();
	}
}


