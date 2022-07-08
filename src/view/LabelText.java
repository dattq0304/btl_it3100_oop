package view;

import java.awt.Font;

import javax.swing.JLabel;

public class LabelText extends JLabel {
	private int size;
	
	public LabelText(LabelArr labelArr) {
		this.setSize(labelArr.getElementSize() + 20, labelArr.getElementSize());
		this.setLocation(labelArr.getX() - 10, labelArr.getY() + labelArr.getElementSize());
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		size = labelArr.getElementSize();
		this.setFont(new Font("Arial", Font.PLAIN, (int)labelArr.getElementSize()/2));
	}
	
	@Override
	public void setText(String str) {
		super.setText(str);
		if(str.length() < 6) {
			this.setFont(new Font("Arial", Font.PLAIN, (int)size/2));
		}
		else {
			float x = (float) (size*2.5/str.length());
			this.setFont(new Font("Arial", Font.PLAIN, (int)(x)));
		}
	}
}
