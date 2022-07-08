package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainView;

public class SelecOrderListener implements ActionListener{
	private MainView mainView;
	
	public SelecOrderListener (MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		
		if(src.equalsIgnoreCase("Increase")) {
			System.out.println("Sort order: Increase");
			this.mainView.isIncrease = true;
		}
		else {
			System.out.println("Sort order: Decrease");
			this.mainView.isIncrease = false;
		}
	}
	
}
