package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

import controller.ChangeSizeListener;
import controller.ControlListener;
import controller.MenuListener;
import controller.SelecOrderListener;
import controller.SelectAlgoListener;
import controller.SpeedController;

public class MainView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public int arrLength;
	private int maxArrLength = 500;
	public int initArr[] = new int[maxArrLength];

	private int wWidth;
	private int wHeight;
	
	private LabelArr[] labelArr = new LabelArr[maxArrLength];
	private LabelText[] labelText = new LabelText[maxArrLength];
	private int labelArrSize;
	private int labelArrDistance;
	private int oldX[] = new int[maxArrLength];
	private int oldY;
	
	private Thread[] threads = new Thread[100000];
	private int currThread = -1;
	
	private double timeSleep = 10;
	private int step = 5;
	
	private SpinnerModel spinnerModel = new SpinnerNumberModel(13, 2, 500, 1);
	private JSpinner spinner_create_array;
	public String algorithmSelected = "Bubble sort";
	
	private MyBucket[] bucketArr = new MyBucket[maxArrLength];	
	private int bucketLength;
	private int bucketSize = 60;
	private int bucketDistance = 20;

	private JPanel Main_App;
	private JPanel panel_view_main_play = new JPanel();
	private JPanel panel_play;
	
	private MyButton btn_control;
	private JLabel speed_label;
	private JSlider speed_slider;
	
	public boolean isIncrease = true;
	
	public MyButton btn_array_create;
	public MyButton btn_array_delete;
	public MyButton btn_array_element_random;
	public MyButton btn_array_element_edit;
	
	public MyRadioButton btn_select_1;
	public MyRadioButton btn_select_2;
	public MyRadioButton btn_select_3;
	public MyRadioButton btn_select_4;
	
	public MyRadioButton btn_increase;
	public MyRadioButton btn_decrease;

	private JTextArea history;
	String info = "Group 23: \n"
			+ "1. Le Van Tung\n"
			+ "2. Tran Quoc Dat\n"
			+ "3. Truong Quang Truong\n"
			+ "4. Dinh Viet Ty\n";

	private JPanel panel_history;
	private MyButton btn_finish;

	MyDialog myDialog = new MyDialog(this);
	
	public void startApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Application demonstrate sorting algorithms");
		
		//set minimum frame size
		setMinimumSize(new Dimension(640, 600));
		
		//set frame size 900x720
		setSize(900, 720); 
		setLocationRelativeTo(null);
		
		//set frame fit full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		setView();
		setVisible(true);				
	}
	
	public void setView() {
		Main_App = new JPanel();
		Main_App.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Main_App);
		Main_App.setLayout(new BorderLayout(0,0));
		
		JPanel panel_view_title = new JPanel();
		Main_App.add(panel_view_title, BorderLayout.NORTH);
		panel_view_title.setLayout(new BorderLayout(0, 0));
		
		JLabel label_title_inside = new JLabel("Demonstrate Sorting Algorithms");
		label_title_inside.setHorizontalAlignment(SwingConstants.CENTER);
		label_title_inside.setFont(new Font("Arial", Font.BOLD, 28));
		panel_view_title.add(label_title_inside, BorderLayout.CENTER);
		
		JPanel panel_view_main = new JPanel();
		panel_view_main.setBackground(SystemColor.menu);
		panel_view_main.setBorder(new TitledBorder(null, "Main View", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,20), null));
		panel_view_main.setLayout(new BorderLayout());
		
		panel_view_main_play = new JPanel();
		panel_view_main_play.setBackground(Color.WHITE);
		panel_view_main_play.setLayout(null);
		panel_view_main.add(panel_view_main_play);
		Main_App.add(panel_view_main, BorderLayout.CENTER);
		
		panel_play = new JPanel();
		panel_play.setBackground(Color.white);
		panel_play.setLayout(null);
		panel_view_main.add(panel_play);
		panel_play.addComponentListener(new ChangeSizeListener(this));

		
		JPanel panel_view_sub = new JPanel();
		Main_App.add(panel_view_sub, BorderLayout.SOUTH);
		panel_view_sub.setLayout(new BoxLayout(panel_view_sub, BoxLayout.X_AXIS));
		
		JPanel panel_view_sub_data = new JPanel();
		panel_view_sub_data.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,20), null));
		panel_view_sub.add(panel_view_sub_data);
		panel_view_sub_data.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_create_array = new JPanel();
		panel_create_array.setBorder(new TitledBorder(null, "Create Array", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,12), null));
		panel_view_sub_data.add(panel_create_array);
		panel_create_array.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel create_array_number = new JPanel();
		panel_create_array.add(create_array_number);
		create_array_number.setLayout(new BoxLayout(create_array_number, BoxLayout.X_AXIS));
		
		JSeparator separator_create_array_1 = new JSeparator();
		separator_create_array_1.setForeground(SystemColor.menu);
		separator_create_array_1.setBackground(SystemColor.menu);
		create_array_number.add(separator_create_array_1);
		
		JLabel label_create_array = new JLabel("Length of array:");
		label_create_array.setHorizontalAlignment(SwingConstants.CENTER);
		create_array_number.add(label_create_array);
		
		JSeparator separator_create_array_2 = new JSeparator();
		separator_create_array_2.setBackground(SystemColor.menu);
		separator_create_array_2.setForeground(SystemColor.menu);
		create_array_number.add(separator_create_array_2);
		
		spinner_create_array = new JSpinner(spinnerModel);
		spinner_create_array.setAlignmentX(Component.LEFT_ALIGNMENT);
		create_array_number.add(spinner_create_array);
		spinnerModel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				btn_control.setEnabled(false);
			}
		});
		
		JPanel create_array_option = new JPanel();
		panel_create_array.add(create_array_option);
		create_array_option.setLayout(new GridLayout(0, 2, 0, 0));
		
		btn_array_create = new MyButton("Create Array");
		create_array_option.add(btn_array_create);
		
		btn_array_delete = new MyButton("Delete Array ");
		create_array_option.add(btn_array_delete);
		
		JPanel create_element_panel = new JPanel();
		create_element_panel.setBorder(new TitledBorder(null, "Create Element", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,12), null));
		create_element_panel.setLayout(new GridLayout(0, 2, 0, 0));
		panel_view_sub_data.add(create_element_panel);
		
		btn_array_element_random = new MyButton("Random");
		create_element_panel.add(btn_array_element_random);
		
		btn_array_element_edit = new MyButton("Edit");
		create_element_panel.add(btn_array_element_edit);
		
		JPanel panel_view_sub_select = new JPanel();
		panel_view_sub.add(panel_view_sub_select);
		panel_view_sub_select.setLayout(new BoxLayout(panel_view_sub_select, BoxLayout.Y_AXIS));
		
		JPanel speed_panel = new JPanel();
		speed_panel.setBorder(new TitledBorder(null, "Speed", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,20), null));
		panel_view_sub_select.add(speed_panel);
		speed_panel.setLayout(new BoxLayout(speed_panel, BoxLayout.Y_AXIS));
		
		speed_slider = new JSlider(0, 2000, 1000);
		speed_slider.setFont(new Font("Arial", Font.PLAIN, 11));
		speed_panel.add(speed_slider);
		speed_slider.addChangeListener(new SpeedController(this));
		
		speed_label = new JLabel("x1.0");
		speed_label.setFont(new Font("Arial", Font.PLAIN, 20));
	
		speed_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		speed_panel.add(speed_label);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.menu);
		separator.setBackground(SystemColor.menu);
		
		JPanel select_panel = new JPanel();
		select_panel.setBorder(new TitledBorder(null, "Select Sort Algorithms", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,20), null));
		panel_view_sub_select.add(select_panel);
		select_panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		btn_select_1 = new MyRadioButton("Bubble sort");
		btn_select_1.setHorizontalAlignment(SwingConstants.LEFT);
		btn_select_1.setSelected(true);
		
		btn_select_2 = new MyRadioButton("Selection sort");
		btn_select_2.setHorizontalAlignment(SwingConstants.LEFT);
		
		btn_select_3 = new MyRadioButton("Merge sort");
		btn_select_3.setHorizontalAlignment(SwingConstants.LEFT);
		
		btn_select_4 = new MyRadioButton("Bucket sort");
		btn_select_4.setHorizontalAlignment(SwingConstants.LEFT);
		
		ButtonGroup bg_select = new ButtonGroup();
		bg_select.add(btn_select_1);
		bg_select.add(btn_select_2);
		bg_select.add(btn_select_3);
		bg_select.add(btn_select_4);
		
		select_panel.add(btn_select_1);
		select_panel.add(btn_select_2);
		select_panel.add(btn_select_3);
		select_panel.add(btn_select_4);
		
		//
		JPanel panel_view_sub_control = new JPanel();
		panel_view_sub_control.setBorder(new TitledBorder(null, "Control", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,20), null));
		
		panel_view_sub.add(panel_view_sub_control);
	
		panel_view_sub_control.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_control_btn_inde = new JPanel();
		panel_view_sub_control.add(panel_control_btn_inde);
		panel_control_btn_inde.setLayout(new GridLayout(0, 1, 0, 0));
		
		btn_increase = new MyRadioButton("Increase");
		btn_increase.setHorizontalAlignment(SwingConstants.CENTER);
		btn_increase.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_control_btn_inde.add(btn_increase);
		btn_increase.setSelected(true);
		
		btn_decrease = new MyRadioButton("Decrease");
		btn_decrease.setVerticalAlignment(SwingConstants.TOP);
		btn_decrease.setHorizontalAlignment(SwingConstants.CENTER);
		panel_control_btn_inde.add(btn_decrease);
		
		ButtonGroup bg_control = new ButtonGroup();
		bg_control.add(btn_decrease);
		bg_control.add(btn_increase);
		
		JPanel panel_control_btn_start = new JPanel();
		panel_view_sub_control.add(panel_control_btn_start);
//		panel_control_btn_start.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_control_btn_start.setLayout(new GridLayout(2, 0));
		
		btn_control = new MyButton("Start");
		panel_control_btn_start.add(btn_control);
		btn_control.setEnabled(false);
		
		btn_finish = new MyButton("Finish now");
		panel_control_btn_start.add(btn_finish);
		btn_finish.setEnabled(false);
		
		//History
		panel_history = new JPanel();
		history = new JTextArea(8, 30);
				
		history.setText(info);
		history.setEditable(false);
		history.setLineWrap(true);
		history.setWrapStyleWord(true);
		DefaultCaret caret = (DefaultCaret)history.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JScrollPane scroll_history = new JScrollPane(history);
		
		panel_history.setLayout(new GridLayout());
		panel_history.add(scroll_history);
		panel_history.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,20), null));
		
		panel_view_sub.add(panel_history);
		
		//Action listener
		ActionListener menuListener = new MenuListener(this);
		btn_array_create.addActionListener(menuListener);
		btn_array_delete.addActionListener(menuListener);
		btn_array_element_random.addActionListener(menuListener);
		btn_array_element_edit.addActionListener(menuListener);
		
		ActionListener controlListener = new ControlListener(this);
		btn_control.addActionListener(controlListener);
		btn_finish.addActionListener(controlListener);
		
		ActionListener selectAlgoListener = new SelectAlgoListener(this);
		btn_select_1.addActionListener(selectAlgoListener);
		btn_select_2.addActionListener(selectAlgoListener);
		btn_select_3.addActionListener(selectAlgoListener);
		btn_select_4.addActionListener(selectAlgoListener);
			
		ActionListener selecOrderListener = new SelecOrderListener(this);
		btn_increase.addActionListener(selecOrderListener);
		btn_decrease.addActionListener(selecOrderListener);
	}

	public void changeSize() {
		System.out.println("Resize");
		System.out.println("Width: " + getWidth());
		System.out.println("Height: " + getHeight());
		
		if(arrLength > 0) {
			clearScreen();
			createElements(initArr, arrLength);
			
			btn_control.setText("Start");
			btn_control.setEnabled(true);
		}
		
		if(panel_play.getWidth() < 750) {
			panel_history.setVisible(false);
			bucketSize = 40;
		}
		else {
			panel_history.setVisible(true);
			bucketSize = 60;
		}
	}
	
	//createElements
	public void createElements(int arr[], int length) {
		btn_control.setText("Start");
		btn_control.setEnabled(true);
		
		wWidth = panel_play.getWidth();
		wHeight = panel_play.getHeight();
		
		//Create label element 
		//Label text set visible = false
		labelArrSize = 60;
		if((5*length - 1)*labelArrSize/4 >= wWidth - 20) {
			labelArrSize = 4*(wWidth - 20)/(5*length - 1);
		}
		labelArrDistance = labelArrSize/4;
		
		oldY = wHeight/2 - labelArrSize/2;
		
		for(int i = 0; i < length; i++) {
			labelArr[i] = new LabelArr(labelArrSize);
			labelArr[i].setInitColor();
			
			if(i == 0) {
				labelArr[i].setLocation(wWidth/2 - (length*labelArrSize + (length -1)*labelArrDistance)/2, oldY);
			}
			else {
				labelArr[i].setLocation(labelArr[i-1].getX() + labelArrDistance + labelArrSize, oldY);
			}
			
			oldX[i] = labelArr[i].getX();
			labelArr[i].setText(arr[i] + "");
			initArr[i] = arr[i];
			panel_play.add(labelArr[i]);
			
			labelText[i] = new LabelText(labelArr[i]);
			panel_play.add(labelText[i]);
		}	
		
		//Create bucket and set visible = false
		int max = arr[0];
		int min = arr[0];
		for(int i = 0; i < arrLength; i++) {
			if(arr[i] > max) 
				max = arr[i];
			if(arr[i] < min) 
				min = arr[i];
		}
		System.out.println("Min = " + min);
		System.out.println("Max = " + max);
		max++;

		bucketLength = 10;
		for(int i = 0; i < bucketLength; i++) {
			bucketArr[i] = new MyBucket(bucketSize);
			bucketArr[i].setVisible(false);
			bucketArr[i].setText(i + "");
			
			bucketArr[i].setMin(min + i*(max-min)/bucketLength);
			bucketArr[i].setMax(min + (i+1)*(max-min)/bucketLength);
			System.out.print("Bucket " + i + ": ");
			System.out.print(bucketArr[i].getMin() + " -> ");
			System.out.print(bucketArr[i].getMax() + "\n");
			
			if(i == 0) {
				bucketArr[i].setLocation((int)(wWidth/2 - (bucketLength*bucketSize + (bucketLength-1)*bucketDistance)/2), wHeight - bucketSize/2);
			}
			else {
				bucketArr[i].setLocation(bucketArr[i-1].getX() + bucketDistance + bucketSize, wHeight - bucketSize/2);
			}
			
			panel_play.add(bucketArr[i]);
		}
	}
		
	public void clearScreen() {
		if(arrLength > 0) {
			for (int i = 0; i < arrLength; i++) {
				panel_play.remove(labelArr[i]);
				panel_play.remove(labelText[i]);
			}
		}
		
		if(bucketLength > 0) {
			for(int i = 0; i < bucketLength; i++) {
				panel_play.remove(bucketArr[i]);
			}
		}
		
		if(currThread > -1) {
			for (int i = 0; i < currThread; i++) {
				try {
					threads[i].interrupt();
				} 
				catch (Exception e) {
				}
			}
			currThread = -1;
		}

		panel_play.revalidate();
		panel_play.repaint();
	}
	
	  
	public void deleteArr() {
		clearScreen();
		finished(true);
		
		panel_history.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,20), null));
		history.setText(info);
		
		btn_control.setEnabled(false);
	}
		
	public int[] getZeroArr(int length) {
		int[] a = new int[length];
		for (int i = 0; i < length; i++) {
			a[i] = 0;
		}
		return a;
	}	
		
	public void createZeroElements() {
		clearScreen();

		arrLength = (Integer)spinner_create_array.getValue();
		int[] arr = getZeroArr(arrLength);
		createElements(arr, arrLength);
		
		for (int i = 0; i < arrLength; i++) {
			System.out.print(arr[i] + " ");
		}
		
	}
	
	public int[] getRandomArr(int length) {	
		int[] a = new int[length];
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			int ranNum = rand.nextInt(99);
			a[i] = ranNum;
		}
		return a;
	}

	public void createRandomElements() {
		clearScreen();
			
		arrLength = (Integer)spinner_create_array.getValue();
		int[] arr = getRandomArr(arrLength);
		createElements(arr, arrLength);
		
		for (int i = 0; i < arrLength; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public void changeSpeed() {
		System.out.println("Speed");
		int x = speed_slider.getValue();
		
		if(x <= 1000) {
			x = x/100;
			speed_label.setText("x" + (x/10) + "." + (x%10));
			if(x<1) speed_label.setText("x" + (x/10) + ".1");
			timeSleep = 21 - x;
		}
		else {
			int y = x - 1000;
			speed_label.setText("x" + ((y/250) + 1) + "." + ((y%250)/25));
			if(x <= 1500) {
				timeSleep = -0.018*x + 28;
			}
			else {
				timeSleep = -0.00198*x + 3.97;
			}
		}
		
		System.out.println("Time sleep: " + timeSleep);
	}
	
	public void setInitArr(int[] arr, int length) {
		clearScreen();
		
		arrLength = length;
		spinnerModel.setValue(arrLength);
		
		for(int i = 0; i < arrLength; i++) {
			initArr[i] = arr[i];
		}
		
		createElements(initArr, arrLength);
	}

	public void edit() {
		String s = "";
		
		for(int i = 0; i < arrLength; i++) {
			s += initArr[i] + ", ";
		}
		
		myDialog.getJtext().setText(s);
		myDialog.setVisible(true);
	}

	public void Start() {
		btn_control.setText("Pause");
		btn_finish.setEnabled(true);
		
		panel_history.setBorder(new TitledBorder(null, "History", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.ITALIC,20), null));
		history.setText("");
		
		changeSpeed();
		step = 5;
		
		finished(false);
		
		for(int i = 0; i < arrLength; i++) {
			labelArr[i].setInitColor();
		}

		history.append(algorithmSelected + "\n\n");
		addToHistory("First Arr:");
		for(int j = 0; j < arrLength;j++) {
			addToHistory(labelArr[j].getText() + " ");
		}
		addToHistory("\nArr length:" + arrLength + "\n\n");
		if(algorithmSelected.equalsIgnoreCase("Bubble sort")) {
			bubbleSort();
		}
		else if(algorithmSelected.equalsIgnoreCase("Selection sort")) {
			selectionSort();
		}
		else if(algorithmSelected.equalsIgnoreCase("Merge sort")) {
			mergeSort();
		}
		else if(algorithmSelected.equalsIgnoreCase("Bucket sort")) {
			bucketSort();
		}
	}
	
	public void Pause(){
		btn_control.setText("Continue");
		step = 0;
	}
	
	public void Continue() {
		btn_control.setText("Pause");
		step = 5;
	}
	
	public void finishNow() {
		btn_finish.setEnabled(false);
		timeSleep = 0;
		step = 5;
	}

	public void finished(boolean isFinished) {
		spinner_create_array.setEnabled(isFinished);
		btn_array_create.setEnabled(isFinished);
		btn_array_delete.setEnabled(isFinished);
		btn_array_element_random.setEnabled(isFinished);
		btn_array_element_edit.setEnabled(isFinished);

		btn_select_1.setEnabled(isFinished);
		btn_select_2.setEnabled(isFinished);
		btn_select_3.setEnabled(isFinished);
		btn_select_4.setEnabled(isFinished);
		
		btn_increase.setEnabled(isFinished);
		btn_decrease.setEnabled(isFinished);
		
		btn_finish.setEnabled(!isFinished);
	}

	
	public void markFinishedAll() {
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				joinPreviousThread(curr);
				
    			for(int i = 0; i < arrLength; i++) {
    				labelArr[i].setDoneColor();
    			}
    			
    			for(int i = 0; i < bucketLength; i++) {
    				  bucketArr[i].setVisible(false);
    				  bucketArr[i].setNum(0);
    			}
    			
    			history.append("\nFinal Arr: ");
				for(int i = 0; i < arrLength; i++) {
					history.append(initArr[i] + " ");
				}
				finished(true);

				history.append("\n\nFinished\n\n");
				btn_control.setText("Start");	
			}
		});
		threads[curr].start();
	}

	//Common animation:
	//Join previous thread
	public void joinPreviousThread(int p) {
		if(p > 0) {
			try {
				threads[p-1].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//
	public void sleep(int speed) {
		double x = timeSleep*speed;
		double y = (x - (int)x)*1000000; // 1ms = 1000000ns
		try {
			Thread.sleep((int)x, (int)y);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//swaps
	public void SwapAnimation(int i, int j) {
		currThread++;
		
    	int temp = initArr[i];
		initArr[i] = initArr[j];
		initArr[j] = temp;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	joinPreviousThread(curr);	
		    	
		    	//Move up and down
				while (labelArr[i].getY() > oldY - 100) {
					labelArr[i].setLocation(labelArr[i].getX(), labelArr[i].getY() - step);
					labelArr[j].setLocation(labelArr[j].getX(), labelArr[j].getY() + step);
					sleep(1);
		        }
				
				//Move left and right
		        while (labelArr[i].getX() < oldX[j]) {
		        	labelArr[i].setLocation(labelArr[i].getX() + step, labelArr[i].getY());
		        	labelArr[j].setLocation(labelArr[j].getX() - step, labelArr[j].getY());
		        	sleep(1);
		        }
		        
		      //Move up and down
		        while (labelArr[i].getY() < oldY) {
		        	labelArr[i].setLocation(labelArr[i].getX(), labelArr[i].getY() + step);
		        	labelArr[j].setLocation(labelArr[j].getX(), labelArr[j].getY() - step);
		        	sleep(1);
		        }

		        labelArr[i].setLocation(oldX[i], oldY);
		        labelArr[j].setLocation(oldX[j], oldY);

		        String txt_tmp = labelArr[i].getText();
		        labelArr[i].setText( labelArr[j].getText());
		        labelArr[j].setText(txt_tmp);
		        
		        sleep(20);
		    		
		    }
		});
		threads[curr].start();
	}
	
	//History
	public void addToHistory(String str) {
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				joinPreviousThread(curr);
				
				history.append(str);
			}
		});

		threads[curr].start();
	}
	
	//
	public void addText(int i, String str, int speed) {
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				joinPreviousThread(curr);
				
				labelText[i].setLocation(labelArr[i].getX() - 10, labelArr[i].getY() + labelArr[i].getElementSize());
				labelText[i].setText(str);
				
				sleep(speed);
			}
		});

		threads[curr].start();
	}
	
	//Color
	public void setColor1(int left, int right, int speed) {
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				joinPreviousThread(curr);
				
				for(int i = left; i <= right; i++) {
					labelArr[i].setColor1();
				}
				
				sleep(speed);
			}
		});

		threads[curr].start();
	}
	
	public void setColor2(int left, int right, int speed) {
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				joinPreviousThread(curr);
				
				for(int i = left; i <= right; i++) {
					labelArr[i].setColor2();
				}
				
			    sleep(speed);
			}
		});

		threads[curr].start();
	}
	
	public void setInitColor(int left, int right, int speed) {
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				joinPreviousThread(curr);
				
				for(int i = left; i <= right; i++) {
					labelArr[i].setInitColor();
				}
				
				sleep(speed);
			}
		});

		threads[curr].start();
	}
	
	public void setProcesColor(int left, int right, int speed) {
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				joinPreviousThread(curr);
				
				for(int i = left; i <= right; i++) {
					labelArr[i].setProcesColor();
				}
				
				sleep(speed);
			}
		});

		threads[curr].start();
	}
	
	public void setDoneColor(int left, int right, int speed) {
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				joinPreviousThread(curr);
				
				for(int i = left; i <= right; i++) {
					labelArr[i].setDoneColor();
				}
				
				sleep(speed);
			}
		});

		threads[curr].start();
	}
	
	//Move label in the y direction
	public void moveYAnimation(int left, int right, int y) {
		currThread++;		
		int curr = currThread;
		
		threads[curr] = new Thread(new Runnable() {
			
            @Override
            public void run() {
            	joinPreviousThread(curr);
                        
                //Shift up
                if(labelArr[right].getY() > y) {
                	while (labelArr[right].getY() > y + step) {
                		for (int i = left; i <= right; i++ ) {
                			if (labelArr[i].getY() > y)
                				labelArr[i].setLocation(labelArr[i].getX(), labelArr[i].getY() - step);
                		}
                		sleep(1);
                	}
                	for (int i = left; i <= right; i++ ) {
            			if (labelArr[i].getY() > y)
            				labelArr[i].setLocation(labelArr[i].getX(), y);
            		}
            		sleep(1);
                }
                
                //Shift down
                else {
                	while (labelArr[right].getY() < y - step) {
                		for (int i = left; i <= right; i++ ) {
                			if (labelArr[i].getY() < y)
                				labelArr[i].setLocation(labelArr[i].getX(), labelArr[i].getY() + step);
                		}
                		sleep(1);
                	}    
                	for (int i = left; i <= right; i++ ) {
            			if (labelArr[i].getY() < y)
            				labelArr[i].setLocation(labelArr[i].getX(), y);
            		}
            		sleep(1);
                }
            }
        });
		
        threads[curr].start();
	}
	
	//	Sort Algorithms:
	//	Bubble sort
	public void bubbleSort() {
		for(int i = 0; i < arrLength - 1; i++) {
			boolean swapped = false;
			addToHistory("+) For j = " + 0 + " to " + (arrLength - i - 1) + "\n");
			
			for(int j = 0; j < arrLength - i - 1; j++) {				
				setProcesColor(j, j+1, 100);
				
				if(isIncrease && (initArr[j] > initArr[j + 1])) {
					addToHistory("Arr[" + (j+1) + "] < Arr[" + j + "] => swap(Arr["+ (j+1) + "], Arr[" + j + "])\n");
					addText(j+1, "< " + initArr[j], 100);
					SwapAnimation(j, j+1);
					addToHistory("Arr now: ");
					for(int m = 0; m < arrLength; m++) {
						addToHistory(initArr[m] + " ");
					}
					addToHistory("\n" + "\n");
					swapped = true;
				}
				if(!isIncrease && (initArr[j] < initArr[j + 1])) {
					addToHistory("Arr[" + (j+1) + "] > Arr[" + j + "] => swap(Arr["+ (j+1) + "], Arr[" + j + "])\n");
					addText(j+1, "> " + initArr[j], 100);
					SwapAnimation(j, j+1);
					addToHistory("Arr now: ");
					for(int m = 0; m < arrLength; m++) {
						addToHistory(initArr[m] + " ");
					}
					addToHistory("\n" + "\n");
					swapped = true;
				}
				
				setInitColor(j, j, 0);
				addText(j, "", 0);
				addText(j+1, "", 0);
			}		
			
			if(!swapped) {
				addToHistory("There are no number was swapped!\n");
				break;
			}
			
			setDoneColor(arrLength - i - 1, arrLength - i - 1, 0);
			addToHistory("\n");
		}
		
		markFinishedAll();			
	}
	
	//Selection sort
	public void selectionSort() {
		for(int i = 0; i < arrLength - 1; i++) {
			addToHistory("+) i = " + i + "\n");
			if(isIncrease) addToHistory("Finding min...\n");
			else addToHistory("Finding max...\n");

			setProcesColor(i, i, 0);
			addText(i, "i", 100);
			
			int k = i;
			
			for(int j = i + 1; j < arrLength; j++) {
				setProcesColor(j, j, 0);
				addText(j, "j", 100);
				
				if(isIncrease && (initArr[j] < initArr[k])) {
					addToHistory("Arr[" + j + "] < Arr[" + k + "] => min = Arr[" + j + "] = " + initArr[j] + "\n");
					if(k != i) {
						setInitColor(k, k, 0);
						addText(k, "", 0);
					}

					k = j;
					setProcesColor(k, k, 0);
					addText(k, "MIN", 100);
				}
				else if(!isIncrease && (initArr[j] > initArr[k])) {
					addToHistory("Arr[" + j + "] > Arr[" + k + "] => max = Arr[" + j + "] = " + initArr[j] + "\n");
					if(k != i) {
						setInitColor(k, k, 0);
						addText(k, "", 0);
					}
					
					k = j;
					setProcesColor(k, k, 0);
					addText(k, "MAX", 100);
				} 
				else {
					setInitColor(j, j, 0);
					addText(j, "", 0);
					if(j == arrLength - 1)
						addText(j, "", 100);
				}
			}
				
			if(k != i) {
				addToHistory("swap(Arr["+ i + "], Arr[" + k + "])\n");
				SwapAnimation(i, k);
				
				setInitColor(k, k, 0);
				addText(k, "", 0);
			}	
			addToHistory("Arr now: ");
			for(int m = 0; m < arrLength; m++) {
				addToHistory(initArr[m] + " ");
			}
			addToHistory("\n");
			
			setDoneColor(i, i, 0);
			addText(i, "", 0);
			addToHistory("\n");
			}
		markFinishedAll();		
	}

	
	//Merge sort
	public void mergeAnimation(int currLabel, int k) {
		int x1 = labelArr[currLabel].getX();
		currThread++;
		
		int curr = currThread;
		threads[curr] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	joinPreviousThread(curr);
	    		
	    		//Move down 
	    		while(labelArr[currLabel].getY() < oldY - 60) {
	    			labelArr[currLabel].setLocation(labelArr[currLabel].getX(), labelArr[currLabel].getY() + step);
    				sleep(1);
	    		}
	    		
	    		//Move right
	    		if(x1 < oldX[k]) {
	    			while(labelArr[currLabel].getX() < oldX[k] - step) {
	    				labelArr[currLabel].setLocation(labelArr[currLabel].getX() + step, labelArr[currLabel].getY());
	    				sleep(1);
	    			}
	    			labelArr[currLabel].setLocation(oldX[k], labelArr[currLabel].getY());
    				sleep(1);
	    		}
	    		
	    		//Move left
	    		else {
	    			while(labelArr[currLabel].getX() > oldX[k] + step) {
	    				labelArr[currLabel].setLocation(labelArr[currLabel].getX() - step, labelArr[currLabel].getY());
	    				sleep(1);
	    			}
	    			labelArr[currLabel].setLocation(oldX[k], labelArr[currLabel].getY());
    				sleep(1);
	    		}       
	    		
	    		//Move down
	    		while(labelArr[currLabel].getY() < oldY) {
	    			labelArr[currLabel].setLocation(labelArr[currLabel].getX(), labelArr[currLabel].getY() + step);
	    			sleep(1);
	    		}
	    		sleep(20);	
		    }
		});
		threads[curr].start();
	}
	
    public void reLocation(int left, int right, int[] T) {
    	currThread++;
    	System.out.println(currThread);
    	int curr = currThread;

    	threads[curr] = new Thread(new Runnable() {
    		@Override
    		public void run() {	
    			joinPreviousThread(curr);
				
				for (int i = left; i <= right; i ++) {
					if (labelArr[i].getX() != oldX[i]) {
						labelArr[i].setLocation(oldX[i], oldY);
						labelArr[i].setText(T[i - left] + "");
					}
					if(left == 0 && right == arrLength-1) {
						labelArr[i].setDoneColor();
					}
				}
				
				sleep(1);	
    		}
    	});
    	threads[curr].start();
    }

	public void merge(int arr[], int l, int m, int r){
	    int n1 = m - l + 1;
	    int n2 = r - m;
	    int L[] = new int[n1];
	    int R[] = new int[n2];
	    int T[] = new int[n2 + n1];
	    
	    //Divide
	    int i, j;
	    for(i = 0; i < n1; i++) {
	        L[i] = arr[l + i];
	    }
	    for(j = 0; j < n2; j++) {
	        R[j] = arr[m + 1 + j];
	    }
	    
	    moveYAnimation(l, r, oldY - 120);
	    
	    setColor1(l, m, 0);
	    setColor2(m+1, r, 100);
	    
	    //Merge
	    int k = l;
	    i = 0; j = 0;
	    while(i < n1 && j < n2) {
	    	
	    	if(isIncrease) {
		        if(L[i] <= R[j]) {
		        	addText(l + i, "<=" + R[j], 100);
		        	addToHistory("L[" + i + "] <= R[" + j + "] => " + "Arr[" + k + "] = L[" + i + "] \n");
		            arr[k] = L[i];
		            mergeAnimation(l + i, k);
		            addText(l + i, "", 50);
		            i++;
		        }
		        else {
		        	addText(l + n1 + j, "<" + L[i], 100);
		        	addToHistory("L[" + i + "] > R[" + j + "] => " + "Arr[" + k + "] = R[" + j + "] \n");
		            arr[k] = R[j];
		            mergeAnimation(m + j + 1, k);
		            addText(l + n1 + j, "", 50);
		            j++;
		        }
	    	}
	    	else {
	    		if(L[i] >= R[j]) {
	    			addText(l + i, ">=" + R[j], 100);
		        	addToHistory("L[" + i + "] >= R[" + j + "] => " + "Arr[" + k + "] = L[" + i + "] \n");
		            arr[k] = L[i];
		            mergeAnimation(l + i, k);
		            addText(l + i, "", 50);
		            i++;
		        }
		        else {
		        	addText(l + n1 + j, ">" + L[i], 100);
		        	addToHistory("L[" + i + "] < R[" + j + "] => " + "Arr[" + k + "] = R[" + j + "] \n");
		            arr[k] = R[j];
		            mergeAnimation(m + j + 1, k);
		            addText(l + n1 + j, "", 50);
		            j++;
		        }
	    	}

	        k++;
	    }
	    while(i < n1) {
	    	addToHistory("Arr[" + k + "] = R[" + i + "] \n");
	        arr[k] = L[i];
	        mergeAnimation(l + i, k);
	        i++;
	        k++;
	    }
	    while(j < n2) {
	    	addToHistory("Arr[" + k + "] = R[" + j + "] \n");
	        arr[k] = R[j];
	        mergeAnimation(m + j + 1, k);
	        j++;
	        k++;
	    }
	    
	    setInitColor(l, r, 0);
	    
	    addToHistory("\n");
	    
	    for (i = 0; i < n1 + n2; i ++)
            T[i] = arr[l + i];

	    reLocation(l, r, T);
	}

	public void mergeRecursive(int a[], int l, int r){
	    if(r > l) {
	        int m = (l + r)/2;   
	        mergeRecursive(a, l, m);
	        mergeRecursive(a, m + 1, r);
	        merge(a, l, m, r);
	    }
	}
	
	public void mergeSort() {
		mergeRecursive(initArr, 0, arrLength - 1);
		markFinishedAll();
	}
	
	//Bucket sort
	public void moveToBucketAnimation(int i, int k) {
		currThread++;
		int curr = currThread;

		threads[curr] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	joinPreviousThread(curr);
		    	
				history.append(bucketArr[k].getMin() + " <= Arr[" + i + "] < " + bucketArr[k].getMax() + "\n");
				history.append("=> Arr[" + i + "] to bucket " + k + "\n\n");
		    	sleep(100);
		    	
		    	labelArr[i].setProcesColor();
	    		
	    		bucketArr[k].addElement(i);

	    		//Shift down
	    		while(labelArr[i].getY() < labelArrSize + 2*labelArrDistance) {
	    			labelArr[i].setLocation(labelArr[i].getX(), labelArr[i].getY() + step);
	    			sleep(1);
	    		}
	    		
	    		//Shift right
	    		if(labelArr[i].getX() < bucketArr[k].getX()) {
	    			while(labelArr[i].getX() < bucketArr[k].getX()  + (bucketArr[k].getSide() - labelArrSize)/2 - step) {
	    				labelArr[i].setLocation(labelArr[i].getX() + step, labelArr[i].getY());
	    				sleep(1);
	    			}
	    		}
	    		
	    		//Shift left
	    		else {
	    			while(labelArr[i].getX() > bucketArr[k].getX() + (bucketArr[k].getSide() - labelArrSize)/2 + step) {
	    				labelArr[i].setLocation(labelArr[i].getX() - step, labelArr[i].getY());
	    				sleep(1);
	    			}
	    		}    
	    		
	    		labelArr[i].setLocation(bucketArr[k].getX() + (bucketArr[k].getSide() - labelArrSize)/2, labelArr[i].getY());
    			sleep(1);
	    		
	    		//Shift down  
	    		while(labelArr[i].getY() < wHeight - bucketSize/2 - bucketArr[k].getNum()*labelArrSize - (bucketArr[k].getNum() + 1)*labelArrDistance/2 - step) {
	    			labelArr[i].setLocation(labelArr[i].getX(), labelArr[i].getY() + step);
    				sleep(1);
	    		}
	    		
	    		labelArr[i].setLocation(labelArr[i].getX(), wHeight - bucketSize/2 - bucketArr[k].getNum()*labelArrSize - (bucketArr[k].getNum() + 1)*labelArrDistance/2);
				sleep(1);
	    		
	    		labelArr[i].setInitColor();
	    		
		       sleep(20);	
		    }
		});
		threads[curr].start();
	}
	
	 public void sortElementInBucket() {
	    	currThread++;
	    	
	    	int curr = currThread;
	    	threads[curr] = new Thread(new Runnable() {
	    		@Override
	    		public void run() {
	    			joinPreviousThread(curr);
	    				
	    			for(int k = 0; k < bucketLength; k++) {
		    			
    					if(bucketArr[k].getNum() < 2) { 
    						continue;
    					}
    					
    					history.append("Sorting bucket " + k + "\n");
    					for(int i = 0; i < bucketArr[k].getNum(); i++) {
							labelArr[bucketArr[k].getElement(i)].setProcesColor();
						}
    					
    					int[] num = new int[arrLength];
    					for(int i = 0; i < bucketArr[k].getNum(); i++) {
    						num[i] = initArr[bucketArr[k].getElement(i)];
    					}
    						
    					for(int i = 0; i < bucketArr[k].getNum(); i++) {
    						for(int j = bucketArr[k].getNum() - 1; j > i; j--) {
    							if(isIncrease && (num[i] < num[j])) {
    								int temp = num[i];
    								num[i] = num[j];
    								num[j] = temp;
    							}
    							if(!isIncrease && (num[i] > num[j])) {
    								int temp = num[i];
    								num[i] = num[j];
    								num[j] = temp;
    							}
    						}	
    					}
    						
    					for(int i = 0; i < bucketArr[k].getNum(); i++) {
    						
    						labelArr[bucketArr[k].getElement(i)].setText(num[i] + "");
    					}
    					
    					sleep(100);
						for(int i = 0; i < bucketArr[k].getNum(); i++) {
							labelArr[bucketArr[k].getElement(i)].setInitColor();
						}
    				
    				}
    				sleep(100);		
	    		}
	    	});
	    	threads[curr].start();
	    }

	  public void moveBackAnimation() {
		  	currThread++;
		  	
	    	int curr = currThread;
	    	threads[curr] = new Thread(new Runnable() {
	    		@Override
	    		public void run() {
	    			joinPreviousThread(curr);
	    				
	    			history.append("\nGather from buckets\n");
	    			
	    			int count = -1;
    				
    				for(int j = 0; j < bucketLength; j++) {	
    					int k = j;
    					
    					if(!isIncrease) {
    						k = bucketLength - 1 - j;
    					}
    					
    					for(int i = bucketArr[k].getNum() - 1; i >= 0 ; i--) {
    						
    						int index = bucketArr[k].getElement(i);
							count++;
    						
							labelArr[index].setProcesColor();
							
    						//Shift up
							while(labelArr[index].getY() > labelArrSize + 2*labelArrDistance) {
								labelArr[index].setLocation(labelArr[index].getX(), labelArr[index].getY() - step);
								sleep(1);
							}
							
							//Shift right
							if(labelArr[index].getX() < oldX[count]) {
								while(labelArr[index].getX() < oldX[count] - step) {
									labelArr[index].setLocation(labelArr[index].getX() + step, labelArr[index].getY());
									sleep(1);
								}	
							}
							//Or shift left
							else {
								while(labelArr[index].getX() > oldX[count] + step) {
									labelArr[index].setLocation(labelArr[index].getX() - step, labelArr[index].getY());
									sleep(1);
								}	
							}
							
							labelArr[index].setLocation(oldX[count], labelArr[index].getY());
							sleep(1);
							
							//Shift up
							while(labelArr[index].getY() >  labelArrDistance + step) {
								labelArr[index].setLocation(labelArr[index].getX(), labelArr[index].getY() - step);
								sleep(1);
							}
							labelArr[index].setLocation(labelArr[index].getX(), labelArrDistance);
							sleep(1);
							
							initArr[count] = Integer.parseInt((labelArr[index].getText()));
						}
    				}

	                while (labelArr[arrLength-1].getY() < oldY) {
	                    for (int i = 0; i < arrLength; i++ ) {
	                    	if (labelArr[i].getY() < oldY)
	                    		labelArr[i].setLocation(labelArr[i].getX(), labelArr[i].getY() + step);
	                    }
	                    sleep(1);
	                } 
	    		}
	    	});
	    	threads[curr].start();
	  }

	  public void bucketSort() {
		  for(int i = 0; i < bucketLength; i++) {
			 bucketArr[i].setVisible(true);
		  }	  	
			
		  moveYAnimation(0, arrLength - 1, labelArrDistance);
			
		  for(int i = 0; i < arrLength; i++) {
			  int k = 0;
			  while(initArr[i] < bucketArr[k].getMin() || initArr[i] >= bucketArr[k].getMax()) {
		    	k++;
		    }
		    moveToBucketAnimation(i, k);
		  }
			
		  sortElementInBucket();
			
		  moveBackAnimation();	
            
		  markFinishedAll();
	  }	
}
