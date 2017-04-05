package main.reader;

import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import main.info.InfoView;
import main.info.Recorder;

import java.util.ArrayList;

public class MouseReader {

    private InfoView view;
    private GlobalMouseHook mouseHook = new GlobalMouseHook();
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
                    recorder.addAction(GlobalMouseEvent.BUTTON_LEFT);
                    view.update();
                    System.out.println(event);
                } else if(event.getButton()==GlobalMouseEvent.BUTTON_RIGHT) {
                    recorder.addAction(GlobalMouseEvent.BUTTON_LEFT);
                    view.update();
                    System.out.println(event);
                }


                if((event.getButtons()&GlobalMouseEvent.BUTTON_LEFT)!=GlobalMouseEvent.BUTTON_NO
                && (event.getButtons()&GlobalMouseEvent.BUTTON_RIGHT)!=GlobalMouseEvent.BUTTON_NO){
                	System.out.println("Both mouse buttons are currenlty pressed!");
                }   
                if(event.getButton()==GlobalMouseEvent.BUTTON_MIDDLE){
                	
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
        recorder.deleteLastAction();
        mouseHook.shutdownHook();
        view.dispose();
    }

}
