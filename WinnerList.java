import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class WinnerList implements ActionListener
{
	JFrame f;
	JButton b_main,b_quit;
	JLabel l;
	
	public WinnerList(String user1,String user2,String user3,String user4,int winner[],int n)
	{
		f=new JFrame("Ludo");
		f.setSize(900,700);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.HIDE_ON_CLOSE);
		f.setResizable(false);
		f.setLayout(null);
		
		l=new JLabel("WINNER LIST (RANKING)");
		l.setForeground(Color.red);
		l.setFont(new Font("calibri", Font.BOLD, 40));
		l.setBounds(300,250,450,40);
		f.add(l);
		
		int ucrow[]={winner[0],winner[1],winner[2],winner[3]};
		int crow[]={n,n,n,n};
	
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(ucrow[i]>ucrow[j])
				{
					crow[i]--;
				}
			}
		}
		for(int i=n;i<4;i++)
		{
			crow[i]=0;
		}
		
		String col[]={"Name","Rank"};
		String urow[][]={{user1,Integer.toString(crow[0])},{user2,Integer.toString(crow[1])},{user3,Integer.toString(crow[2])},{user4,Integer.toString(crow[3])}};
		
		JTable jt=new JTable(urow,col);
		jt.setBounds(400,350,200,65); 
		f.add(jt);
		
		b_main=new JButton("Main Menu");
		b_quit=new JButton("Quit");
		b_main.addActionListener(this);
		b_quit.addActionListener(this);
		b_main.setBounds(450,430,100,20);
		b_quit.setBounds(450,460,100,20);
		f.add(b_main);
		f.add(b_quit);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b_main)
		{
			new One();
			f.dispose();
		}
		else
		{
			System.exit(0);
		}
	} 
}