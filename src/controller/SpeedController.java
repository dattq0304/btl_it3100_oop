package controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.MainView;

public class SpeedController implements ChangeListener{
	private MainView mainView;
	
	public SpeedController (MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		mainView.changeSpeed();
	}
}
