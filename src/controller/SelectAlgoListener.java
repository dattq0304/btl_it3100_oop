package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainView;
public class SelectAlgoListener implements ActionListener{
	
	private MainView mainView;
	
	public SelectAlgoListener(MainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();

		if(src.equalsIgnoreCase("Bubble sort")) {
			System.out.println("Sort Algorithms: Bubble sort");
			this.mainView.algorithmSelected = "Bubble sort";
		}
		else if(src.equalsIgnoreCase("Selection sort")) {
			System.out.println("Sort Algorithms: Selection sort");
			this.mainView.algorithmSelected = "Selection sort";
		}
		else if(src.equalsIgnoreCase("Merge sort")) {
			System.out.println("Sort Algorithms: Merge sort");
			this.mainView.algorithmSelected = "Merge sort";
		}
		else if(src.equalsIgnoreCase("Bucket sort")) {
			System.out.println("Sort Algorithms: Bucket sort");
			this.mainView.algorithmSelected = "Bucket sort";
		}
	}
}
