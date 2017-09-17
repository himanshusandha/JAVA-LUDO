import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class Two implements ActionListener,ItemListener 
{
	JFrame f;
	JButton b_start,b_quit;
	JLabel lback,l[]=new JLabel[6];
	JTextField tf[]=new JTextField[4];
	JRadioButton r[]=new JRadioButton[3];
	ButtonGroup bg;
	
	int n;
	Two()
	{
		f=new JFrame("Ludo");
		f.setSize(900,700);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.HIDE_ON_CLOSE);
		f.setResizable(false);
		f.setLayout(null);
		
		lback=new JLabel();
		lback.setIcon(new ImageIcon("twoback.png"));
		lback.setBounds(0,-20,900,700);
		f.add(lback);
		
		l[0]=new JLabel("Select the Number of Players:  ");
		l[0].setBounds(50,200,200,20);
		l[1]=new JLabel("Enter the Players Name:  ");
		l[1].setBounds(50,230,200,20);
		l[2]=new JLabel("Player Name 1 (Blue):  ");
		l[2].setBounds(50,260,200,20);
		l[3]=new JLabel("Player Name 2 (Red):  ");
		l[3].setBounds(50,290,200,20);
		l[4]=new JLabel("Player Name 3 (Green):  ");
		l[4].setBounds(50,320,200,20);
		l[5]=new JLabel("Player Name 4 (Yellow):  ");
		l[5].setBounds(50,350,200,20);
		
		bg=new ButtonGroup();
		int k=230;
		for(int i=0;i<3;i++)
		{
			k+=50;
			r[i]=new JRadioButton(""+(i+2));
			bg.add(r[i]);
			r[i].setBounds(k,200,40,20);
			f.add(r[i]);
			r[i].addItemListener(this);
		}
		
		int j=230;
		for(int i=0;i<4;i++)
		{
			j+=30;
			tf[i]=new JTextField();
			tf[i].setBounds(250,j,200,20);
			tf[i].setEditable(false);
			f.add(tf[i]);
		}
		
		b_start=new JButton("Start");
		b_start.setBounds(400,400,100,30);
		b_start.setEnabled(false);
		b_quit=new JButton("Quit");
		b_quit.setBounds(400,440,100,30);
		
		f.add(b_start);
		f.add(b_quit);
		for(int i=0;i<6;i++)
		{
			f.add(l[i]);
		}
		
		b_start.addActionListener(this);
		b_quit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b_start)
		{
			new Ludo(n,tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText());
			f.dispose();
		}
		else
		{
			System.exit(0);
		}
	}
	
	public void itemStateChanged(ItemEvent ae)
	{
		for(int i=0;i<4;i++)
		{
			tf[i].setText(null);
		}
		if(ae.getSource()==r[0])
		{
			n=2;
			tf[0].setEditable(true);
			tf[1].setEditable(true);
			tf[2].setEditable(false);
			tf[3].setEditable(false);
		}
		else if(ae.getSource()==r[1])
		{
			n=3;
			tf[0].setEditable(true);
			tf[1].setEditable(true);
			tf[2].setEditable(true);
			tf[3].setEditable(false);
		}
		else if(ae.getSource()==r[2])
		{
			n=4;
			tf[0].setEditable(true);
			tf[1].setEditable(true);
			tf[2].setEditable(true);
			tf[3].setEditable(true);
		}
		b_start.setEnabled(true);
	}
}