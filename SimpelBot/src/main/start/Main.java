package start;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info.InfoView;
import info.Recorder;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class Main {

	public static void main(String[] args) throws AWTException, InterruptedException {
		//InfoView iv = new InfoView();
		Thread.sleep(200);
		Robot r = new Robot();
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
		}
		//imageEditing();
	}
}


