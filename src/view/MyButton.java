package view;

import java.awt.Font;

import javax.swing.JButton;

public class MyButton extends JButton{
	public MyButton(String text) {
		this.setText(text);
		this.setFont(new Font("Arial", Font.PLAIN, 15));
	}
}
