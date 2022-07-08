package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainView;

public class MenuListener implements ActionListener{
	private MainView mainView;
	
	public MenuListener(MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String src = e.getActionCommand();

		if(src.equalsIgnoreCase("Edit")) {
			System.out.println("Edit Elements");
			this.mainView.edit();
		}
		else if(src.equalsIgnoreCase("Create Array")) {
			this.mainView.createZeroElements();
		}
		
		else if(src.equalsIgnoreCase("Delete Array ")) {
			System.out.println("Delete Array");
			this.mainView.deleteArr();
		}
		else if(src.equalsIgnoreCase("Random")) {
			System.out.println("Random");
			this.mainView.createRandomElements();
		}
	}
}
