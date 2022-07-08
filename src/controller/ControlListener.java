package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainView;

public class ControlListener  implements ActionListener{
	private MainView mainView;
	
	public ControlListener (MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		
		if(src.equalsIgnoreCase("Start")) {	
			System.out.println("Start");
			this.mainView.Start();
		}
		else if(src.equalsIgnoreCase("Continue")) {
			System.out.println("Continue");
			this.mainView.Continue();
		}
		if(src.equalsIgnoreCase("Pause")) {
			System.out.println("Pause");
			this.mainView.Pause();
		}
		else if(src.equalsIgnoreCase("Finish now")) {
			System.out.println("Finish");
			this.mainView.finishNow();
		}
	}
}
