package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyDialog extends JDialog{
	private String str = "";
	private int arr_length;
	private int[] arr = new int[1000];
	private MainView mainView;
	private JTextArea jtext = new JTextArea();
	
	public MyDialog(MainView mainView) {
		this.mainView = mainView;
		
		this.setTitle("Edit array");
		this.setSize(320, 180);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		JLabel heading = new JLabel("Elements are separated by ' ' (space) or ','");
		heading.setFont(new Font("Arial", Font.PLAIN, 14));
		heading.setBounds(15, 4, 300, 30);
		this.add(heading);
		
		JPanel textJP = new JPanel();
		textJP.setLayout(new GridLayout());
		textJP.setBounds(10, 40, 280, 60);
		jtext.setFont(new Font("Arial", Font.PLAIN, 16));
		jtext.setLineWrap(true);
		jtext.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(jtext);
		textJP.add(scrollPane);
		this.add(textJP);
		
		MyButton btn_ok = new MyButton("Ok");
		btn_ok.setBounds(110, 110, 80, 30);
		this.add(btn_ok);
		
		btn_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ok();
			}
		});
	}
	
	
	public void ok() {
		str = jtext.getText();
		
		System.out.println(str);
		
		if(!validArr(str)) {
			JOptionPane.showMessageDialog(this, "Invalid Array!");
		}
		else {
			strToArr(str);
			if(arr_length<2) {
				JOptionPane.showMessageDialog(this, "Invalid Array!");
			}
			else {
				for(int i = 0; i < arr_length; i++) {
					System.out.print(arr[i] + ", ");
				}
				System.out.println("");
		
				mainView.setInitArr(arr, arr_length); 
			}
			
		}
		this.setVisible(false);
	}
	
	public boolean validArr(String s) {
		if(s.isEmpty()) {
			return false;
		}
		
		for(int i = 0; i < s.length(); i++) {
			if(!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
				if(!(s.charAt(i) == ' ' || s.charAt(i) == ',' || s.charAt(i) == '\n' || s.charAt(i) == '\0')) 
					return false;
			}
		}
		
		return true;
	}
	
	public void strToArr(String s) {
		arr_length = 0;
		
		String[] numbers = new String[1000];
	    for(int i = 0; i < 100; i++) {
	    	numbers[i] = "";
	    }
	    
	    int f = 0;
	    while(s.charAt(f) < '0' || s.charAt(f) > '9') {
	        f++;
	    }
	    
	    int b = s.length() - 1;
	    while(s.charAt(b) < '0' || s.charAt(b) > '9') {
	        b--;
	    }  
	    
	    String newStr = s.substring(f, b+1);
	    System.out.println(newStr);
	    
	    for(int i = 0; i < newStr.length(); i++) {
	    	if(newStr.charAt(i) == ' ' || newStr.charAt(i) == ',' || newStr.charAt(i) == '\n') {
	    		if(newStr.charAt(i-1) >= '0' && newStr.charAt(i-1) <= '9')
	    			arr_length++;
	    		else
	    			continue;
	    	}
	    	else {
	    		numbers[arr_length] += s.charAt(i);
	    	}
	    }
	    arr_length++;
	    
	    for(int i = 0; i < arr_length; i++) {
	    	arr[i] = Integer.parseInt(numbers[i]);
	    }
	    
	    for(int i = 0; i < arr_length; i++) {
	    	System.out.print(arr[i] + ", ");
	    }
	}


	public JTextArea getJtext() {
		return jtext;
	}


	public void setJtext(JTextArea jtext) {
		this.jtext = jtext;
	}
}