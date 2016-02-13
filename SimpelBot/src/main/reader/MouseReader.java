package reader;

import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class MouseReader {

public void input(){
		
		GlobalMouseHook mouseHook = new GlobalMouseHook();

        System.out.println("Global mouse hook successfully started, press [middle] mouse button to shutdown.");
        mouseHook.addMouseListener(new GlobalMouseAdapter() {
            @Override public void mousePressed(GlobalMouseEvent event)  {
                System.out.println(event);
                if((event.getButtons()&GlobalMouseEvent.BUTTON_LEFT)!=GlobalMouseEvent.BUTTON_NO
                && (event.getButtons()&GlobalMouseEvent.BUTTON_RIGHT)!=GlobalMouseEvent.BUTTON_NO){
                	System.out.println("Both mouse buttons are currenlty pressed!");
                }   
                if(event.getButton()==GlobalMouseEvent.BUTTON_MIDDLE){
                	
                }
                    
            }
            @Override public void mouseReleased(GlobalMouseEvent event)  {
                System.out.println(event); }
            @Override public void mouseMoved(GlobalMouseEvent event) {
                System.out.println(event); }
            @Override public void mouseWheel(GlobalMouseEvent event) {
                System.out.println(event); }
        });

        try {
            while(true) Thread.sleep(128);
        } catch(InterruptedException e) { /* nothing to do here */ }
          finally { mouseHook.shutdownHook(); }
	}

}
