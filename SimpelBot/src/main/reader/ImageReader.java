package reader;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class ImageReader {


	private void imageEditing() {
		int  red =0;
		int  green =0;
		int  blue =0;
		try {			
			BufferedImage image;
			//image = getScreenShotEdited();
			image = ImageIO.read(new File("test"));
			long start = System.currentTimeMillis();
			
			for (int y = 0; y < image.getHeight(); y++) {
			    for (int x = 0; x < image.getWidth(); x++) {
			    	int[] rgbArray = new int[9];
			          image.getRGB(x, y, 3,3,rgbArray,0,1); 
			          boolean black = analysePart3x3(rgbArray);
			          
			          if(black){
			        	  image.setRGB(x, y, Color.BLACK.getRGB());
			          } else {
			        	  image.setRGB(x, y, Color.WHITE.getRGB());
			          }
			        	  
			          
			    }
			}
			System.out.println("Time taken: "+(System.currentTimeMillis()-start)+" ms");
			System.out.println("Red: "+red);
			System.out.println("Green: "+green);
			System.out.println("Blue: "+blue);
			ImageIO.write(image,"bmp", new File("test"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean analysePart3x3(int[] rgbArray) {
		//Rot vielleicht auch für contrast geignet
		//      red   = (clr & 0x00ff0000) >> 16;
		//     	green = (clr & 0x0000ff00) >> 8;
		//      blue  =  clr & 0x000000ff;
		int pixel = rgbArray[4];
		for (int i = 0; i < rgbArray.length; i++) {
			if(i!=4 && rgbArray[i]>pixel){
				return false;
			}
		}
		return true;
	}
	
	private void analyseBlack3x3(int[] rgbArray) {
		//Rot vielleicht auch für contrast geignet
		//      red   = (clr & 0x00ff0000) >> 16;
		//     	green = (clr & 0x0000ff00) >> 8;
		//      blue  =  clr & 0x000000ff;
		int count =0;
		for (int i = 0; i < rgbArray.length; i++) {
			if(rgbArray[i]==Color.BLACK.getRGB()){
				count++;
			}
		}
		if(count<5){
			return;
		}
		//TODO analyse
	}
	
	

	private BufferedImage getScreenShotEdited() {
		Dimension screenDimension = null;
		Dimension screenToWatch = null;
		double PERCENT_x = 0.7;
		double PERCENT_y = 0.7;
		
		screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		screenToWatch = new Dimension((int)(screenDimension.getWidth()*PERCENT_x), (int)(screenDimension.getHeight()*PERCENT_y));
		Rectangle rectToCompute = new Rectangle(
				(int)((screenDimension.getWidth()-screenToWatch.getWidth())/2),
				(int)((screenDimension.getHeight()-screenToWatch.getHeight())/2),
				(int)screenToWatch.getWidth(),
				(int)screenToWatch.getHeight()
		);
		Robot robot;
		try {
			robot = new Robot();
			return robot.createScreenCapture(rectToCompute);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return null;
				
	}

}
