import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Start implements ActionListener
{
	int u1,u2;
	JFrame f1;
	JLabel l,lauthor;
	JButton b; 
	public Start()
	{
		f1=new JFrame();
		l=new JLabel("WELCOME TO PLAY TIC TAC TOE");
		lauthor=new JLabel("-by HIMANSHU SANDHA");
		f1.setSize(500,300);
		f1.setVisible(true);
		f1.setResizable(false);
		f1.setLayout(null);
		f1.setDefaultCloseOperation(f1.HIDE_ON_CLOSE);
		b=new JButton("START");
		l.setBounds(200,150,200,50);
		b.setBounds(220,200,100,30);
		lauthor.setBounds(350,250,150,20);
		f1.add(l);
		f1.add(b);
		f1.add(lauthor);
		b.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		new UsernameSet();
		f1.dispose();
	}
	public static void main(String args[])
	{
		new Start();
	}
}