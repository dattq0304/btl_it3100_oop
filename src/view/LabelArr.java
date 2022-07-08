package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class LabelArr extends JLabel {
	private int elementSize;
	private Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
	
	public LabelArr(int elementSize) {
		this.elementSize = elementSize;
		this.setSize(elementSize, elementSize);
		this.setBackground(Color.WHITE);
		this.setBorder(lineBorder);
		this.setOpaque(true);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font("Arial", Font.PLAIN, (int)elementSize/2));
	}
	
	@Override
	public void setText(String str) {
		super.setText(str);
		if(str.length() < 4) {
			this.setFont(new Font("Arial", Font.PLAIN, (int)elementSize/2));
		}
		else {
			float x = elementSize*3/(2*str.length());
			this.setFont(new Font("Arial", Font.PLAIN, (int)(x)));
		}
	}
	
	public void setInitColor() {
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
	}
	
	public void setProcesColor() {
		this.setBackground(Color.YELLOW);
		this.setForeground(Color.BLACK);
	}
	
	public void setDoneColor() {
		this.setBackground(Color.GREEN);
		this.setForeground(Color.BLACK);
	}
	
	public void setColor1() {
		this.setBackground(Color.BLUE);
		this.setForeground(Color.WHITE);
	}
	
	public void setColor2() {
		this.setBackground(Color.RED);
		this.setForeground(Color.WHITE);
	}
	
	public void setElementSize(int elementSize) {
		this.elementSize = elementSize;
		this.setSize(elementSize, elementSize);
		this.setFont(new Font("Arial", Font.PLAIN, (int)elementSize/2));
	}

	public int getElementSize() {
		return elementSize;
	}
	
}