package view;

import java.awt.Font;

import javax.swing.JRadioButton;

public class MyRadioButton extends JRadioButton{
	
	public MyRadioButton(String text) {
		this.setText(text);
		this.setFont(new Font("Arial", Font.PLAIN, 15));
	}
	
	public void firstSelect() {
		this.setSelected(true);
	}
}
