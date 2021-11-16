import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class TimeRunnable implements Runnable{
	private JLabel timerLable;
	public int n = 0;
	public TimeRunnable(JLabel timerLabel) {
		this.timerLable = timerLabel;
	}
	public void run() {
		while(true) {
			timerLable.setText(Integer.toString(n));
			n++;
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				return;
			}
		}
	}
}


public class Base extends JFrame{
	private JLabel [] label = new JLabel[10];
	private JButton restart = new JButton("Restart");
	private JButton [] button = new JButton[10];
	private JLabel clearlabel;
	private JLabel timerLabel = new JLabel();
	Container c= getContentPane();
	private int index = 0;
	private int [] getX = new int[10];
	private int [] getY = new int[10];
	private int [] getNumber = new int[10];
	public Base() {
		this.setTitle("Base");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(getOwner());
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 40));
		c.setLayout(null);
		clearlabel = new JLabel("Clear!!");
		c.add(clearlabel);
		c.add(timerLabel);
		c.add(restart);
		restart.addActionListener(new MyActionListener());
		clearlabel.setBounds(180,120,100,100);
		clearlabel.setVisible(false);
		restart.setBounds(235, 200, 100, 100);
		restart.setVisible(false);
		c.setBackground(Color.BLACK);
		timerLabel.setForeground(Color.WHITE);
		timerLabel.setBounds(270,0,50,50);
		TimeRunnable runnable = new TimeRunnable(timerLabel);
		Thread th = new Thread(runnable);
		for(int i = 0; i<label.length;i++) {
			int x = (int)(Math.random()*255);
			int y = (int)(Math.random()*255);
			getX[i] = x;
			getY[i] = y;
			label[i] = new JLabel(Integer.toString(i));
			label[i].setBounds(x,y,40,40);
			label[i].setForeground(Color.WHITE);
			c.add(label[i]);
			
		}
		for(int i = 0; i<button.length;i++) {
			button[i] = new JButton(""+i);
			button[i].setBounds(getX[i], getY[i], 20,20);
			button[i].addActionListener(new MyActionListener());
			button[i].setForeground(new Color(0,0,0,0));
			button[i].setBackground(Color.WHITE);
			button[i].setBorderPainted(false);
			c.add(button[i]);
			button[i].setVisible(false);
			
		}
		setSize(600,600);
		setVisible(true);
		
		th.start();
		try {
			for(int i = 0; i<label.length;i++) {
				label[i].setVisible(true);
			}
			th.sleep(3000);
			for(int i = 0; i<label.length;i++) {
				label[i].setVisible(false);
			}
			for(int i =0;i<button.length;i++) {
				button[i].setVisible(true);
			}
		}
		catch(InterruptedException e) {
			return;
		}
	}
	private class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			for(int i = 0; i<button.length; i++) {
				if(e.getSource() == button[i]) {
					button[i].setVisible(false);
				}
				
			}
			if(b.getText().equals("Restart")) {
				new Base();
				dispose();
			}
			
			else if(b.getText().equals(Integer.toString(index))) {
				label[index].setVisible(true);
				index++;
				if(index == 10) {
					for(int i =0; i<label.length;i++) {
						label[i].setVisible(false);
					}
					c.setBackground(Color.GRAY);
					clearlabel.setVisible(true);
				}
			}
			else {
				for(int i=0; i<button.length;i++) {
					button[i].setVisible(false);
				}
				for(int i =0;i<label.length;i++) {
					label[i].setVisible(false);
				}
				restart.setVisible(true);
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Base();
	}
}
