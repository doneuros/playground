package main.reader;

import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import main.info.InfoView;
import main.info.Recorder;
import main.start.Main;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class MouseReader {

    private InfoView view;
    private static GlobalMouseHook mouseHook = new GlobalMouseHook();
    private ArrayList<Integer> actions = new ArrayList<>();
    private Recorder recorder;
    public MouseReader(Recorder recorder){
        this.recorder= recorder;
    }

    public void startMouseListener(){
		Recorder recorder = Recorder.getInstance();
        view = new InfoView();
        System.out.println("Global mouse hook successfully started, press [middle] mouse button to shutdown.");
        mouseHook.addMouseListener(new GlobalMouseAdapter() {
            @Override public void mousePressed(GlobalMouseEvent event)  {
                if(event.getButton()==GlobalMouseEvent.BUTTON_LEFT) {
                    recorder.addAction(InputEvent.BUTTON1_MASK);
                    view.update();
                    System.out.println(event);
                } else if(event.getButton()==GlobalMouseEvent.BUTTON_RIGHT) {
                    recorder.addAction(InputEvent.BUTTON2_MASK);
                    view.update();
                    System.out.println(event);
                }


                if((event.getButtons()&GlobalMouseEvent.BUTTON_LEFT)!=GlobalMouseEvent.BUTTON_NO
                && (event.getButtons()&GlobalMouseEvent.BUTTON_RIGHT)!=GlobalMouseEvent.BUTTON_NO){
                	System.out.println("Both mouse buttons are currenlty pressed!");

                }   
                if(event.getButton()==GlobalMouseEvent.BUTTON_MIDDLE){
                    System.out.println("Hook end");
                    view.dispose();
                    mouseHook.shutdownHook();
                    try {
                        Main.performRecording();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
                    
            }
            /*@Override public void mouseReleased(GlobalMouseEvent event)  {
                //view.update();
                System.out.println(event); }
            @Override public void mouseMoved(GlobalMouseEvent event) {
                //System.out.println(event);
            }
            @Override public void mouseWheel(GlobalMouseEvent event) {
                System.out.println(event); }*/
        });

        try {
            while(true) Thread.sleep(128);
        } catch(InterruptedException e) { /* nothing to do here */ }
          finally { mouseHook.shutdownHook(); }
	}

	public void endRecording(){
        mouseHook.shutdownHook();
        view.dispose();
    }

}
