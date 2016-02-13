package info;

import java.awt.TextArea;

import javax.swing.JFrame;

public class InfoView extends JFrame {

	private TextArea info;
	
	private Recorder r;
	
	public InfoView(){
		r = Recorder.getInstance();
		info = new TextArea(r.toString());
		info.setEditable(false);
		add(info);
		setVisible(true);;
		setBounds(r.getSceenWidth()-200, r.getScreenHight()-150, 200, 150);
		
	}
	
	public void update(){
		info.setText(r.toString());
	}


}
