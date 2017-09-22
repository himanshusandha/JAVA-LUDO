import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

class Ludo extends Thread implements ActionListener
{
	JFrame f;
	JPanel pcenter,peast;
	JButton quit,b[]=new JButton[226];
	JLabel u1,u2,u3,u4,u_turn;
	
	//arrays for main player position movement i.e inside square
	//int playpos[][]={{167,170,206,203},{90,93,126,129},{96,99,132,135},{173,176,212,209}};
	
	int playpos[][]={{180,181,193,192},{103,104,116,115},{109,110,122,121},{186,187,199,198}};
	int initialplaypos[][]={{180,181,193,192},{103,104,116,115},{109,110,122,121},{186,187,199,198}};
	
	//color name
	String col[]={"blue.png","red.png","green.png","yellow.png"};
	
	//starting position
	int startpos[]={1,14,27,40};
	
	//home part
	int homeposmod[]={51,12,25,38};
	int homepos1[]={53,59,65,71};
	int homepos2[]={58,64,70,76};
	int countincre[][]={{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
	
	int n;
	String user1,user2,user3,user4;
	
	public Ludo(int n,String user1,String user2,String user3,String user4)
	{
		this.n=n;
		this.user1=user1;
		this.user2=user2;
		this.user3=user3;
		this.user4=user4;
		
		f=new JFrame("Ludo");
		f.setSize(900,700);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setResizable(false);
		
		pcenter=new JPanel();
		pcenter.setLayout(new GridLayout(15,15));
		
		int arr[]={24,25,26,23,65,27,22,66,28,21,67,29,20,68,30,19,69,31,13,14,15,16,17,18,70,32,33,34,35,36,37,12,59,60,61,62,63,64,76,75,74,73,72,71,38,11,10,9,8,7,6,58,44,43,42,41,40,39,5,57,45,4,56,46,3,55,47,2,54,48,1,53,49,52,51,50};
		
		int arr1[]={7,8,9,22,23,24,37,38,39,52,53,54,67,68,69,82,83,84,91,92,93,94,95,96,98,100,101,102,103,104,105,106,107,108,109,110,111,112,114,115,116,117,118,119,120,121,122,123,124,125,126,128,130,131,132,133,134,135,142,143,144,157,158,159,172,173,174,187,188,189,202,203,204,217,218,219,0};
		
		int j=0,k=77;
		for(int i=1;i<=225;i++)
		{
			if(arr1[j]==i)
			{
				b[arr[j]]=new JButton();
				pcenter.add(b[arr[j]]);
				
				//action listener registration & not registering the winning point
				if(arr[j]==58 || arr[j]==64 || arr[j]==70 || arr[j]==76)
				{}
				else
				{
					b[arr[j]].addActionListener(this);
				}
				
				j++;
			}
			else
			{
				b[k]=new JButton();
				pcenter.add(b[k]);
				k++;
			}
		}
		
		//square part
		j=77;
		while(j<=82)
		{
			b[j].setBackground(Color.red);
			b[j+6].setBackground(Color.green);
			b[j+77].setBackground(Color.blue);
			b[j+83].setBackground(Color.yellow);
			j++;
		}
		j=82;
		while(j<=142)
		{
			b[j].setBackground(Color.red);
			b[j+6].setBackground(Color.green);
			b[j+77].setBackground(Color.blue);
			b[j+83].setBackground(Color.yellow);
			j+=12;
		}
		j=142;
		while(j>=137)
		{
			b[j].setBackground(Color.red);
			b[j+6].setBackground(Color.green);
			b[j+77].setBackground(Color.blue);
			b[j+83].setBackground(Color.yellow);
			j--;
		}
		j=137;
		while(j>=77)
		{
			b[j].setBackground(Color.red);
			b[j+6].setBackground(Color.green);
			b[j+77].setBackground(Color.blue);
			b[j+83].setBackground(Color.yellow);
			j-=12;
		} 
		
		//inside square position
		for(int i=0;i<4;i++)
		{
			for(j=0;j<4;j++)
			{
				if(i==0)
				{
					b[playpos[i][j]].setBackground(Color.blue);
				}
				else if(i==1)
				{
					b[playpos[i][j]].setBackground(Color.red);
				}
				else if(i==2)
				{
					b[playpos[i][j]].setBackground(Color.green);
				}
				else
				{
					b[playpos[i][j]].setBackground(Color.yellow);
				}
				
				b[playpos[i][j]].setIcon(new ImageIcon(col[i]));
			
				//action listener registration
				b[playpos[i][j]].addActionListener(this);
			}
		}
		
		//going home part
		for(int i=53;i<=58;i++)
		{
			b[i].setBackground(Color.blue);
			b[i+6].setBackground(Color.red);
			b[i+12].setBackground(Color.green);
			b[i+18].setBackground(Color.yellow);
		}

		//starting position
		b[27].setBackground(Color.green);
		b[14].setBackground(Color.red);
		b[40].setBackground(Color.yellow);
		b[1].setBackground(Color.blue);
		
		for(int i=1;i<=225;i++)
		{
			b[i].setEnabled(false);
		}
		
		//direction icons
		b[152].setIcon(new ImageIcon("1.png"));
		b[149].setIcon(new ImageIcon("2.png"));
		b[150].setIcon(new ImageIcon("3.png"));
		b[153].setIcon(new ImageIcon("4.png"));
		b[151].setIcon(new ImageIcon("5.png"));
		b[152].setEnabled(true);
		b[149].setEnabled(true);
		b[150].setEnabled(true);
		b[153].setEnabled(true);
		b[151].setEnabled(true);
		
		//east
		b[0]=new JButton("DICE");
		peast=new JPanel();
		peast.setLayout(new GridLayout(15,1));
		peast.add(b[0]);
		b[0].addActionListener(this);
		
		u1=new JLabel("User 1:"+user1);
		u2=new JLabel("User 2:"+user2);
		u3=new JLabel("User 3:"+user3);
		u4=new JLabel("User 4:"+user4);
		u_turn=new JLabel();
		quit=new JButton("Quit");
		peast.add(u1);
		peast.add(u2);
		peast.add(u3);
		peast.add(u4);
		peast.add(u_turn);
		peast.add(quit);
		quit.addActionListener(this);
		
		f.add(pcenter,BorderLayout.CENTER);
		f.add(peast,BorderLayout.EAST);
		
		for(int i=0;i<n;i++)
		{
			winner[i]=0;
		}
	}
	
	int turn=0,dicenum=0,kill=0,winner[]={1,1,1,1};
	public void actionPerformed(ActionEvent ae)
	{	
		u_turn.setText("Turn:"+(turn+1));
		if(playpos[turn][0]==homepos2[turn] && playpos[turn][1]==homepos2[turn] && playpos[turn][2]==homepos2[turn] && playpos[turn][3]==homepos2[turn])
		{
			winner[turn]++;
			turn++;
			turn%=n;
			if(winner[0]>0 && winner[1]>0 && winner[2]>0 && winner[3]>0)
			{
				new WinnerList(user1,user2,user3,user4,winner,n);
				f.dispose();
			}
		}
		if(ae.getSource()==b[0])
		{
			Random r=new Random();
			do
			{
				if(dicenum==0)
				{
					dicenum=6;
					break;
				}
				dicenum=r.nextInt(6)+1;
			}
			while(dicenum==6 && b[startpos[turn]].getIcon()!=null || playpos[turn][0]==playpos[turn][1]-dicenum || playpos[turn][0]==playpos[turn][2]-dicenum || playpos[turn][0]==playpos[turn][3]-dicenum || playpos[turn][1]==playpos[turn][0]-dicenum || playpos[turn][1]==playpos[turn][2]-dicenum || playpos[turn][1]==playpos[turn][3]-dicenum || playpos[turn][2]==playpos[turn][0]-dicenum || playpos[turn][2]==playpos[turn][1]-dicenum || playpos[turn][2]==playpos[turn][3]-dicenum || playpos[turn][3]==playpos[turn][0]-dicenum || playpos[turn][3]==playpos[turn][1]-dicenum || playpos[turn][3]==playpos[turn][2]-dicenum);
			
			b[0].setText(""+dicenum);
			b[0].setEnabled(false);
			enabledisable();
		}
		if(ae.getSource()!=b[0])
		{
			for(int i=0;i<4;i++)
			{
				b[playpos[turn][i]].setEnabled(false);
				if(ae.getSource()==b[playpos[turn][i]] && playpos[turn][i]>76)
				{
					b[playpos[turn][i]].setIcon(null);
					playpos[turn][i]=startpos[turn];
					b[playpos[turn][i]].setIcon(new ImageIcon(col[turn]));
				}
				if(ae.getSource()==b[playpos[turn][i]] && playpos[turn][i]<76)
				{
					b[playpos[turn][i]].setIcon(null);
					playpos[turn][i]+=dicenum;
					countincre[turn][i]+=dicenum;
					
					//round move for other than blue
					if(playpos[turn][i]>52 && turn!=0)
					{
						playpos[turn][i]%=52;
					}
					
					//count over,so move to part towards home
					if(countincre[turn][i]>51)
					{
						int k=playpos[turn][i]%homeposmod[turn];
						playpos[turn][i]=homepos1[turn];
						playpos[turn][i]+=(k-1);
					}
					
					//exactly at home(win) no increament
					if(playpos[turn][i]>=homepos2[turn])
					{
						playpos[turn][i]=homepos2[turn];
					}
					
					gohome(i);
					b[playpos[turn][i]].setIcon(new ImageIcon(col[turn]));
				}
			}
			b[0].setEnabled(true);
		}
		
		if(ae.getSource()==quit)
		{
			System.exit(0);
		}
		
		if(dicenum!=6)
		{
			if(kill==0)
			{
				turn++;
			}
			else if(kill==1)
			{
				kill=0;
			}
			b[0].setEnabled(true);
		}		
		turn%=n;
	}
	
	public void enabledisable()
	{
		for(int i=0;i<4;i++)
		{
			b[playpos[turn][i]].setIcon(new ImageIcon(col[turn]));
			if(dicenum==6 && playpos[turn][i]>76)
			{
				b[playpos[turn][i]].setEnabled(true);
			}
			if(playpos[turn][i]<76)
			{
				b[playpos[turn][i]].setEnabled(true);
			}
		}
	}
	
	public void gohome(int x)
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(turn==i)
				{
					break;
				}
				if(playpos[turn][x]==playpos[i][j])
				{
					playpos[i][j]=initialplaypos[i][j];
					countincre[i][j]=1;
					b[playpos[i][j]].setIcon(new ImageIcon(col[i]));
					kill=1;
				}
			}
		}
	}
}