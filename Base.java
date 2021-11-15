import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class TimeRunnable implements Runnable{
	private JLabel timerLable;
	
	public TimeRunnable(JLabel timerLabel) {
		this.timerLable = timerLabel;
	}
	public void run() {
		int n = 0;
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
	private int index = 0;
	private int [] getX = new int[10];
	private int [] getY = new int[10];
	
	public Base() {
		this.setTitle("Base");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(getOwner());
		Container c= getContentPane();
		c.setLayout(null);
		clearlabel = new JLabel("Clear!!");
		c.add(clearlabel);
		clearlabel.setBounds(180,120,100,100);
		clearlabel.setVisible(false);
		c.setBackground(Color.WHITE);
		TimeRunnable runnable = new TimeRunnable(timerLabel);
		Thread th = new Thread(runnable);
		for(int i = 0; i<label.length;i++) {
			int x = (int)(Math.random()*255);
			int y = (int)(Math.random()*255);
			getX[i] = x;
			getY[i] = y;
			label[i] = new JLabel(Integer.toString(i));
			label[i].setBounds(x,y,20,20);
			label[i].setForeground(Color.BLACK);
			c.add(label[i]); 
			label[i].setVisible(true);
			System.out.println(timerLabel);
			
		}
		for(int i = 0; i<button.length;i++) {
			button[i] = new JButton();
			button[i].setBounds(getX[i], getY[i], 20,20);
			button[i].addActionListener(new MyActionListener());
			button[i] = new JButton(Integer.toString(i));
			c.add(button[i]);
			if(button[i].getText().equals(i)) {
				index++;
				if(index==10) {
					c.setBackground(Color.GRAY);
					clearlabel.setVisible(true);
					label[index].setVisible(true);
				}
			}
			
		}
		setSize(600,600);
		setVisible(true);
		
		th.start();
	}
	private class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<button.length; i++) {
				if(e.getSource() == button[i]) {
					button[i].setVisible(false);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Base();
	}
}