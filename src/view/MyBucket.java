package view;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyBucket extends JLabel {
	private int bucketSize = 50;
	private int numElementInBucket = 0;
	private Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);
	private int[] JL = new int[30];
	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}


	public int getMin() {
		return min;
	}


	public void setMin(int min) {
		this.min = min;
	}

	private int max;
	private int min;
	
	public int getNum() {
		return numElementInBucket;
	}
	
	public void setNum(int numElementInBucket) {
		this.numElementInBucket = numElementInBucket;
	}

	public void addElement(int i) {
		JL[numElementInBucket] = i;
		this.numElementInBucket++;
	}

	public int getElement(int k) {
		return JL[k];
	}
	
	public void deleteElement() {
		numElementInBucket = 0;
	}
	
	public MyBucket(int bucketSize) {
		this.bucketSize = bucketSize;
		
		this.setSize(bucketSize, bucketSize/2);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setBorder(lineBorder);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font("Arial", Font.PLAIN, bucketSize/2));
	}

	public int getSide() {
		return bucketSize;
	}

	public void setSide(int bucketSize) {
		this.bucketSize = bucketSize;
	}
	
	
}
