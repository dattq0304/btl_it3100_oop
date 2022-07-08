package controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import view.MainView;

public class ChangeSizeListener implements ComponentListener{
	MainView mainView;
	
	public ChangeSizeListener(MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		mainView.changeSize();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
