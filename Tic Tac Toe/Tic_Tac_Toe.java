import java.awt.*;
import java.awt.event.*;
import java.util.*;

class TicTacToe extends Frame implements ActionListener , ItemListener
{
    Button[][] barray=new Button[3][3];
    Random r=new Random();
    Label l1;
    Panel p1;
    int count=0;
    int status=0;
    int flag=0;
    int state=0;
    int a=0,compwin=0,friendwin=0,play1=0,play2=0,both=0;

	Frame f1,f2,f3,f4;
	Checkbox c1,c2,c3;
	CheckboxGroup cg;
	TextField fnm,p1nm,p2nm;
	Button btnnxt1,btnnxt2,replay,mainmenu;
	Label l2,l3,l4,l5;
	String letter,str;
	Boolean win;

   	TicTacToe()
    {
		Font f = new Font("Arial",Font.BOLD,16);

    	setSize(500,500);
    	setLocation(700,300);
        setTitle("TicTacToe");

		// 1 Welcome Frame

		f1 = new Frame();
		f1.setSize(500,500);
		f1.setLocation(700,300);
		f1.setLayout(null);
		f1.setFont(f);
		f1.setTitle("Welcome ...!!!");

		CheckboxGroup cg = new CheckboxGroup();

		l2 = new Label("Tic Tac Toe");
		l2.setBounds(170,100,200,50);
		l2.setFont(new Font("Arial",Font.BOLD,30));
		c1 = new Checkbox("vs Computer",cg,false);
		c1.setBounds(200,180,150,50);
		c2 = new Checkbox("vs Friend",cg,false);
		c2.setBounds(200,230,150,50);
		c3 = new Checkbox("",cg,false);

		f1.add(l2);
		f1.add(c1);
		f1.add(c2);

		// 2 vs Computer Frame

		f2 = new Frame();
		f2.setSize(500,500);
		f2.setLocation(700,300);
		f2.setLayout(null);
		f2.setFont(f);
		f2.setTitle("Vs Computer");

		l5 = new Label("Symbol : O");
		l5.setBounds(130,110,250,30);
		l5.setEnabled(false);
		fnm = new TextField(100);
		fnm.setText("Player Name");
		fnm.selectAll();
		fnm.setBounds(130,140,250,30);
		btnnxt1 = new Button(" Next ");
		btnnxt1.setBounds(180,230,150,50);

		f2.add(fnm);
		f2.add(l5);
		f2.add(btnnxt1);

		// 3 vs Friend Frame

		f3 = new Frame();
		f3.setSize(500,500);
		f3.setLocation(700,300);
		f3.setLayout(null);
		f3.setFont(f);
		f3.setTitle("Vs Friend");

		p1nm = new TextField(100);
		p1nm.setText("Player One Name");
		p1nm.selectAll();
		l3 = new Label("Symbol : O");
		l3.setEnabled(false);
		l3.setBounds(130,110,250,30);
		p2nm = new TextField(100);
		p2nm.setText("Player Two Name");
		p2nm.selectAll();
		l4 = new Label("Symbol : X");
		l4.setEnabled(false);
		l4.setBounds(130,170,250,30);
		p1nm.setBounds(130,140,250,30);
		p2nm.setBounds(130,200,250,30);
		btnnxt2 = new Button(" Next ");
		btnnxt2.setBounds(180,280,150,50);

		f3.add(p1nm);
		f3.add(l3);
		f3.add(p2nm);
		f3.add(l4);
		f3.add(btnnxt2);

		// 4 Score Showing Frame

		f4 = new Frame();
		f4.setSize(300,200);
		f4.setLocation(805,465);
		f4.setLayout(null);
		f4.setFont(f);
		f4.setTitle("Winer");

		l1 = new Label("");
		l1.setBounds(30,60,300,40);

		replay = new Button("Replay");
		replay.setBounds(170,130,100,40);

		mainmenu = new Button("Main Menu");
		mainmenu.setBounds(30,130,100,40);
		f4.add(replay);
		f4.add(mainmenu);
		f4.add(l1);

        p1=new Panel();
        p1.setLayout(new GridLayout(3,3));
        for(int i=0;i<3;i++)
        {
			for(int j=0;j<3;j++)
			{
           		barray[i][j]=new Button("");
           		barray[i][j].setFont(new Font("Arial",Font.BOLD,80));
           		p1.add(barray[i][j]);
           		barray[i][j].setBackground(Color.white);
           		barray[i][j].addActionListener(this);
	   		}
	   	}
        add(p1,BorderLayout.CENTER);
        replay.addActionListener(this);
        btnnxt1.addActionListener(this);
        btnnxt2.addActionListener(this);
        mainmenu.addActionListener(this);
        c1.addItemListener(this);
        c2.addItemListener(this);
        f1.show();
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(c1.getState())
		{
			// vs Computer

			state = 1;
			f1.dispose();
			f2.show();
		}
		else if(c2.getState())
		{
			// vs Friend

			state = 2;
			f1.dispose();
			f3.show();
		}
	}

    public void actionPerformed(ActionEvent ae)
    {
		if(ae.getSource()==btnnxt1)
		{
			// vs Computer Next Button

			f2.dispose();
			show();
		}
		else if(ae.getSource()==btnnxt2)
		{
			// vs Friend Next Button

			f3.dispose();
			show();
		}

		if(ae.getSource()==mainmenu)
		{
			// This button is use for back to front frame

			c3.setState(true);
			dispose();
			f1.show();
			f4.dispose();
			p1.setEnabled(true);
	    	for(int i=0;i<3;i++)
	    	{
				for(int j=0;j<3;j++)
				{
           			barray[i][j].setLabel("");
	       			barray[i][j].setBackground(Color.white);
	       			barray[i][j].setForeground(Color.black);
				}
	   		}
	   		a=0;
	    	count=0;
        	status=0;
	   		compwin=0;
	   		friendwin=0;
	   		play1=0;
	   		play2=0;
		}

		if(ae.getSource()==replay)
	    {
			// This button is use for play again the game after game over or draw

			replay();
		}
		if(state==1)
		{
			// vs Computer

			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
		    		if(ae.getSource()==barray[i][j])
		        	{
						if(barray[i][j].getLabel().equals(""))
				    	{
							flag=0;
					    	barray[i][j].setLabel("O");
					    	count++;
                        	winner();
					    	compMove();

					    	if(status==0 && count==4)
					    	{
								p1.setEnabled(false);
								f4.show();
					    		l1.setText("Draw Match ...!!! Play Again ...!!!");
							}
				  		}
					}
				}
			}
		}
		else if(state==2)
		{
			// vs Friend

			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
		    		if(ae.getSource()==barray[i][j])
		        	{
						if(barray[i][j].getLabel().equals(""))
				    	{
							a++;

							if(a == 1 || a == 3 || a == 5 || a == 7 || a == 9)
							{
								letter = "O";
							}
							else if(a == 2 || a == 4 || a == 6 || a == 8 || a == 10)
							{
								letter = "X";
							}
					    	barray[i][j].setLabel(letter);
					    	Checkwin();
				  		}
					}
				}
			}
		}
	}

	public void check()
	{
		// This is use for checking winner is O or X

		if(str=="O")
		{
			both = 1;
		}
		else if(str=="X")
		{
			both = 2;
		}
	}

	public void Checkwin()
	{
		//Checking Wining Conditions

		// Horizontal wins

		if(barray[0][0].getLabel() == barray[0][1].getLabel() && barray[0][1].getLabel() == barray[0][2].getLabel() && barray[0][0].getLabel() != "")
		{
			barray[0][0].setForeground(Color.red);
			barray[0][1].setForeground(Color.red);
			barray[0][2].setForeground(Color.red);
			str = barray[0][0].getLabel();
			check();
			win = true;
		}
		else if(barray[1][0].getLabel() == barray[1][1].getLabel() && barray[1][1].getLabel() == barray[1][2].getLabel() && barray[1][0].getLabel() != "")
		{
			barray[1][0].setForeground(Color.red);
			barray[1][1].setForeground(Color.red);
			barray[1][2].setForeground(Color.red);
			str = barray[1][0].getLabel();
			check();
			win = true;
		}
		else if(barray[2][0].getLabel() == barray[2][1].getLabel() && barray[2][1].getLabel() == barray[2][2].getLabel() && barray[2][0].getLabel() != "")
		{
			barray[2][0].setForeground(Color.red);
			barray[2][1].setForeground(Color.red);
			barray[2][2].setForeground(Color.red);
			str = barray[2][0].getLabel();
			check();
			win = true;
		}

		// Vertical wins

		else if(barray[0][0].getLabel() == barray[1][0].getLabel() && barray[1][0].getLabel() == barray[2][0].getLabel() && barray[0][0].getLabel() != "")
		{
			barray[0][0].setForeground(Color.red);
			barray[1][0].setForeground(Color.red);
			barray[2][0].setForeground(Color.red);
			str = barray[0][0].getLabel();
			check();
			win = true;
		}
		else if(barray[0][1].getLabel() == barray[1][1].getLabel() && barray[1][1].getLabel() == barray[2][1].getLabel() && barray[0][1].getLabel() != "")
		{
			barray[0][1].setForeground(Color.red);
			barray[1][1].setForeground(Color.red);
			barray[2][1].setForeground(Color.red);
			str = barray[0][1].getLabel();
			check();
			win = true;
		}
		else if(barray[0][2].getLabel() == barray[1][2].getLabel() && barray[1][2].getLabel() == barray[2][2].getLabel() && barray[0][2].getLabel() != "")
		{
			barray[0][2].setForeground(Color.red);
			barray[1][2].setForeground(Color.red);
			barray[2][2].setForeground(Color.red);
			str = barray[0][2].getLabel();
			check();
			win = true;
		}

		// Diagonal wins

		else if(barray[0][0].getLabel() == barray[1][1].getLabel() && barray[1][1].getLabel() == barray[2][2].getLabel() && barray[0][0].getLabel() != "")
		{
			barray[0][0].setForeground(Color.red);
			barray[1][1].setForeground(Color.red);
			barray[2][2].setForeground(Color.red);
			str = barray[0][0].getLabel();
			check();
			win = true;
		}
		else if(barray[0][2].getLabel() == barray[1][1].getLabel() && barray[1][1].getLabel() == barray[2][0].getLabel() && barray[0][2].getLabel() != "")
		{
			barray[0][2].setForeground(Color.red);
			barray[1][1].setForeground(Color.red);
			barray[2][0].setForeground(Color.red);
			str = barray[0][2].getLabel();
			check();
			win = true;
		}
		else
		{
			win = false;
		}

		// Show a dialog if someone wins or the game is tie

		if(win == true)
		{
			p1.setEnabled(false);
			if(both==1)
			{
				play1++;
				l1.setText(p1nm.getText()+" Wins "+String.valueOf(play1));
				f4.show();
			}
			else if(both==2)
			{
				play2++;
				l1.setText(p2nm.getText()+" Wins "+String.valueOf(play2));
				f4.show();
			}
			both=0;
		}
		else if(a == 8 && win == false)
		{
			f4.show();
			l1.setText("Draw Match ...!!! Play Again ...!!!");
			p1.setEnabled(false);
		}
	}

	public void compMove()
	{
		// This method is use for computer move

		if(count==1)
	    {
	    	if(barray[0][0].getLabel().equals("O") || barray[0][2].getLabel().equals("O") || barray[2][0].getLabel().equals("O") || barray[2][2].getLabel().equals("O"))
	        {
		    	barray[1][1].setLabel("X");
            }
            else
            {
		    	barray[0][0].setLabel("X");
		    }
		}
	    if(count==2)
	    {
        	blockWin();
            if(flag!=1)
            {
            	noForkMove();
			}
     	}
		if(count==3 || count==4)
		{
			if(status!=2)
		    {
		    	compWin();
		    	if(status!=1)
		    	{
		        	blockWin();
		        	if(flag!=1)
		        	{
		          		noForkMove();
		        	}
		      	}
		   	}
	    }
	}
	public void randomMove()
	{
		//This method is for Random movement

		int random1=r.nextInt(3);
	    int random2=r.nextInt(3);

	    while(barray[random1][random2].getLabel()!="")
	    {
	  		random1=r.nextInt(3);
	  		random2=r.nextInt(3);
	  	}
	  	barray[random1][random2].setLabel("X");
	}
    public void noForkMove()
    {
		// This method is use for computer move

    	if(barray[0][0].getLabel().equals("X"))
        {
        	if(barray[0][1].getLabel().equals("") && barray[0][2].getLabel().equals(""))
            {
	  	    	barray[0][2].setLabel("X");
		    }
            else if(barray[1][1].getLabel().equals("") && barray[2][2].getLabel().equals(""))
            {
	  	    	barray[1][1].setLabel("X");
		    }
            else if(barray[1][0].getLabel().equals("") && barray[2][0].getLabel().equals(""))
            {
	  	        barray[2][0].setLabel("X");
		    }
    		else
    		{
     			randomMove();
			}
		}
        else if(barray[1][1].getLabel().equals("X"))
        {
        	if(barray[0][1].getLabel().equals("") && barray[2][1].getLabel().equals(""))
            {
	  	        barray[0][1].setLabel("X");
		    }
            else if(barray[1][0].getLabel().equals("") && barray[1][2].getLabel().equals(""))
            {
				barray[1][0].setLabel("X");
		    }
            else if(barray[0][0].getLabel().equals("") && barray[2][2].getLabel().equals(""))
            {
	  	        barray[2][2].setLabel("X");
		    }
            else if(barray[0][2].getLabel().equals("") && barray[2][0].getLabel().equals(""))
            {
	  	        barray[2][0].setLabel("X");
		    }
     		else
     		{
     		    randomMove();
			}
		}
	}
	public void blockWin()
	{
		// This method is for block the win

		if(barray[0][1].getLabel().equals("O") && barray[0][2].getLabel().equals("O") && barray[0][0].getLabel().equals(""))
     	{
       		barray[0][0].setLabel("X");
       		flag=1;
		}
		else if(barray[1][0].getLabel().equals("O") && barray[2][0].getLabel().equals("O") && barray[0][0].getLabel().equals(""))
     	{
            barray[0][0].setLabel("X");
            flag=1;
		}
		else if(barray[1][1].getLabel().equals("O") && barray[2][2].getLabel().equals("O") && barray[0][0].getLabel().equals(""))
     	{
         	barray[0][0].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][0].getLabel().equals("O") && barray[0][2].getLabel().equals("O") && barray[0][1].getLabel().equals(""))
     	{
            barray[0][1].setLabel("X");
       	    flag=1;
		}
		else if(barray[1][1].getLabel().equals("O") && barray[2][1].getLabel().equals("O") && barray[0][1].getLabel().equals(""))
     	{
            barray[0][1].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][0].getLabel().equals("O") && barray[0][1].getLabel().equals("O") && barray[0][2].getLabel().equals(""))
     	{
            barray[0][2].setLabel("X");
       	    flag=1;
		}
		else if(barray[1][2].getLabel().equals("O") && barray[2][2].getLabel().equals("O") && barray[0][2].getLabel().equals(""))
     	{
            barray[0][2].setLabel("X");
       	    flag=1;
		}
		else if(barray[1][1].getLabel().equals("O") && barray[2][0].getLabel().equals("O") && barray[0][2].getLabel().equals(""))
     	{
            barray[0][2].setLabel("X");
       	    flag=1;
		}

		else if(barray[0][0].getLabel().equals("O") && barray[2][0].getLabel().equals("O") && barray[1][0].getLabel().equals(""))
     	{
        	barray[1][0].setLabel("X");
       	    flag=1;
		}
		else if(barray[1][1].getLabel().equals("O") && barray[1][2].getLabel().equals("O") && barray[1][0].getLabel().equals(""))
     	{
            barray[1][0].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][0].getLabel().equals("O") && barray[2][2].getLabel().equals("O") && barray[1][1].getLabel().equals(""))
     	{
            barray[1][1].setLabel("X");
       	    flag=1;
		}
		else if(barray[1][0].getLabel().equals("O") && barray[1][2].getLabel().equals("O") && barray[1][1].getLabel().equals(""))
     	{
            barray[1][1].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][1].getLabel().equals("O") && barray[2][1].getLabel().equals("O") && barray[1][1].getLabel().equals(""))
     	{
            barray[1][1].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][2].getLabel().equals("O") && barray[2][0].getLabel().equals("O") && barray[1][1].getLabel().equals(""))
     	{
            barray[1][1].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][2].getLabel().equals("O") && barray[2][2].getLabel().equals("O") && barray[1][2].getLabel().equals(""))
     	{
            barray[1][2].setLabel("X");
            flag=1;
		}
		else if(barray[1][0].getLabel().equals("O") && barray[1][1].getLabel().equals("O") && barray[1][2].getLabel().equals(""))
     	{
            barray[1][2].setLabel("X");
            flag=1;
		}

		else if(barray[0][0].getLabel().equals("O") && barray[1][0].getLabel().equals("O") && barray[2][0].getLabel().equals(""))
   	 	{
            barray[2][0].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][2].getLabel().equals("O") && barray[1][1].getLabel().equals("O") && barray[2][0].getLabel().equals(""))
     	{
            barray[2][0].setLabel("X");
            flag=1;
		}
		else if(barray[2][1].getLabel().equals("O") && barray[2][2].getLabel().equals("O") && barray[2][0].getLabel().equals(""))
     	{
            barray[2][0].setLabel("X");
            flag=1;
		}
		else if(barray[0][1].getLabel().equals("O") && barray[1][1].getLabel().equals("O") && barray[2][1].getLabel().equals(""))
     	{
            barray[2][1].setLabel("X");
       	    flag=1;
		}
		else if(barray[2][0].getLabel().equals("O") && barray[2][2].getLabel().equals("O") && barray[2][1].getLabel().equals(""))
     	{
            barray[2][1].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][0].getLabel().equals("O") && barray[1][1].getLabel().equals("O") && barray[2][2].getLabel().equals(""))
     	{
            barray[2][2].setLabel("X");
       	    flag=1;
		}
		else if(barray[0][2].getLabel().equals("O") && barray[1][2].getLabel().equals("O") && barray[2][2].getLabel().equals(""))
     	{
            barray[2][2].setLabel("X");
       	    flag=1;
		}
		else if(barray[2][0].getLabel().equals("O") && barray[2][1].getLabel().equals("O") && barray[2][2].getLabel().equals(""))
     	{
            barray[2][2].setLabel("X");
       	    flag=1;
		}
	}
	public void compWin()
	{
		// This method is use for make the computer as winner

		if(barray[0][1].getLabel().equals("X") && barray[0][2].getLabel().equals("X") && barray[0][0].getLabel().equals(""))
     	{
        	barray[0][0].setLabel("X");
       		winner();
		}
		else if(barray[1][0].getLabel().equals("X") && barray[2][0].getLabel().equals("X") && barray[0][0].getLabel().equals(""))
     	{
            barray[0][0].setLabel("X");
		    winner();
		}
		else if(barray[1][1].getLabel().equals("X") && barray[2][2].getLabel().equals("X") && barray[0][0].getLabel().equals(""))
     	{
            barray[0][0].setLabel("X");
		    winner();
		}
		else if(barray[0][0].getLabel().equals("X") && barray[0][2].getLabel().equals("X") && barray[0][1].getLabel().equals(""))
     	{
            barray[0][1].setLabel("X");
		    winner();
		}
		else if(barray[1][1].getLabel().equals("X") && barray[2][1].getLabel().equals("X") && barray[0][1].getLabel().equals(""))
     	{
            barray[0][1].setLabel("X");
		    winner();
		}
		else if(barray[0][0].getLabel().equals("X") && barray[0][1].getLabel().equals("X") && barray[0][2].getLabel().equals(""))
     	{
            barray[0][2].setLabel("X");
		    winner();
		}
		else if(barray[1][2].getLabel().equals("X") && barray[2][2].getLabel().equals("X") && barray[0][2].getLabel().equals(""))
     	{
            barray[0][2].setLabel("X");
		    winner();
		}
		else if(barray[1][1].getLabel().equals("X") && barray[2][0].getLabel().equals("X") && barray[0][2].getLabel().equals(""))
     	{
            barray[0][2].setLabel("X");
		    winner();
		}

		else if(barray[0][0].getLabel().equals("X") && barray[2][0].getLabel().equals("X") && barray[1][0].getLabel().equals(""))
     	{
            barray[1][0].setLabel("X");
		    winner();
		}
		else if(barray[1][1].getLabel().equals("X") && barray[1][2].getLabel().equals("X") && barray[1][0].getLabel().equals(""))
     	{
            barray[1][0].setLabel("X");
		    winner();
		}
		else if(barray[0][0].getLabel().equals("X") && barray[2][2].getLabel().equals("X") && barray[1][1].getLabel().equals(""))
     	{
            barray[1][1].setLabel("X");
		    winner();
		}
		else if(barray[1][0].getLabel().equals("X") && barray[1][2].getLabel().equals("X") && barray[1][1].getLabel().equals(""))
     	{
            barray[1][1].setLabel("X");
		    winner();
		}
		else if(barray[0][1].getLabel().equals("X") && barray[2][1].getLabel().equals("X") && barray[1][1].getLabel().equals(""))
     	{
            barray[1][1].setLabel("X");
		    winner();
	 	}
		else if(barray[0][2].getLabel().equals("X") && barray[2][0].getLabel().equals("X") && barray[1][1].getLabel().equals(""))
     	{
            barray[1][1].setLabel("X");
		    winner();
		}
		else if(barray[0][2].getLabel().equals("X") && barray[2][2].getLabel().equals("X") && barray[1][2].getLabel().equals(""))
     	{
            barray[1][2].setLabel("X");
		    winner();
		}
		else if(barray[1][0].getLabel().equals("X") && barray[1][1].getLabel().equals("X") && barray[1][2].getLabel().equals(""))
     	{
            barray[1][2].setLabel("X");
		    winner();
		}

		else if(barray[0][0].getLabel().equals("X") && barray[1][0].getLabel().equals("X") && barray[2][0].getLabel().equals(""))
     	{
        	barray[2][0].setLabel("X");
		    winner();
		}
		else if(barray[0][2].getLabel().equals("X") && barray[1][1].getLabel().equals("X") && barray[2][0].getLabel().equals(""))
     	{
            barray[2][0].setLabel("X");
		    winner();
		}
		else if(barray[2][1].getLabel().equals("X") && barray[2][2].getLabel().equals("X") && barray[2][0].getLabel().equals(""))
     	{
            barray[2][0].setLabel("X");
		    winner();
		}
		else if(barray[0][1].getLabel().equals("X") && barray[1][1].getLabel().equals("X") && barray[2][1].getLabel().equals(""))
     	{
            barray[2][1].setLabel("X");
		    winner();
		}
		else if(barray[2][0].getLabel().equals("X") && barray[2][2].getLabel().equals("X") && barray[2][1].getLabel().equals(""))
     	{
            barray[2][1].setLabel("X");
		    winner();
		}
		else if(barray[0][0].getLabel().equals("X") && barray[1][1].getLabel().equals("X") && barray[2][2].getLabel().equals(""))
     	{
            barray[2][2].setLabel("X");
		    winner();
		}
		else if(barray[0][2].getLabel().equals("X") && barray[1][2].getLabel().equals("X") && barray[2][2].getLabel().equals(""))
     	{
            barray[2][2].setLabel("X");
		    winner();
		}
		else if(barray[2][0].getLabel().equals("X") && barray[2][1].getLabel().equals("X") && barray[2][2].getLabel().equals(""))
     	{
            barray[2][2].setLabel("X");
			winner();
		}

	}
	public void winner()
	{

		// Friend Wins

	    if((barray[0][0].getLabel().equals("O")) && (barray[0][1].getLabel().equals("O")) && (barray[0][2].getLabel().equals("O")))
      	{
	    	barray[0][0].setForeground(Color.red);
	       	barray[0][1].setForeground(Color.red);
	       	barray[0][2].setForeground(Color.red);
	       	l1.setText("Congratulations!!! You Won");
	       	p1.setEnabled(false);
	       	friendwin++;
	       	f4.show();
	       	l1.setText(fnm.getText()+"Win "+String.valueOf(friendwin));
	       	status=2;
		}
	    if((barray[1][0].getLabel().equals("O")) && (barray[1][1].getLabel().equals("O")) && (barray[1][2].getLabel().equals("O")))
      	{
	    	barray[1][0].setForeground(Color.red);
	       	barray[1][1].setForeground(Color.red);
	       	barray[1][2].setForeground(Color.red);
	       	l1.setText("Congratulations!!! You Won");
	       	p1.setEnabled(false);
	       	friendwin++;
	       	f4.show();
	       	l1.setText(fnm.getText()+"Win "+String.valueOf(friendwin));
	       	status=2;
	 	}
	    if((barray[2][0].getLabel().equals("O")) && (barray[2][1].getLabel().equals("O")) && (barray[2][2].getLabel().equals("O")))
      	{
	     	barray[2][0].setForeground(Color.red);
	       	barray[2][1].setForeground(Color.red);
	       	barray[2][2].setForeground(Color.red);
	       	l1.setText("Congratulations!!! You Won");
	       	friendwin++;
	       	f4.show();
	       	l1.setText(fnm.getText()+"Win "+String.valueOf(friendwin));
	       	p1.setEnabled(false);
	       		status=2;
	 	}
	    if((barray[0][0].getLabel().equals("O")) && (barray[1][0].getLabel().equals("O")) && (barray[2][0].getLabel().equals("O")))
      	{
	    	barray[0][0].setForeground(Color.red);
	       	barray[1][0].setForeground(Color.red);
	       	barray[2][0].setForeground(Color.red);
	       	l1.setText("Congratulations!!! You Won");
	       	friendwin++;
	       	f4.show();
	       	l1.setText(fnm.getText()+"Win "+String.valueOf(friendwin));
	       	p1.setEnabled(false);
	       	status=2;
	 	}
	    if((barray[0][1].getLabel().equals("O")) && (barray[1][1].getLabel().equals("O")) && (barray[2][1].getLabel().equals("O")))
      	{
	      	barray[0][1].setForeground(Color.red);
	       	barray[1][1].setForeground(Color.red);
	       	barray[2][1].setForeground(Color.red);
	       	l1.setText("Congratulations!!! You Won");
	       	friendwin++;
	       	f4.show();
	       	l1.setText(fnm.getText()+"Win "+String.valueOf(friendwin));
	       	p1.setEnabled(false);
	       	status=2;
		}
	    if((barray[0][2].getLabel().equals("O")) && (barray[1][2].getLabel().equals("O")) && (barray[2][2].getLabel().equals("O")))
      	{
	       	barray[0][0].setForeground(Color.red);
	       	barray[1][2].setForeground(Color.red);
	       	barray[2][2].setForeground(Color.red);
	       	l1.setText("Congratulations!!! You Won");
	       	friendwin++;
	       	f4.show();
	       	l1.setText(fnm.getText()+"Win "+String.valueOf(friendwin));
	       	p1.setEnabled(false);
	       	status=2;
	 	}
	    if((barray[0][0].getLabel().equals("O")) && (barray[1][1].getLabel().equals("O")) && (barray[2][2].getLabel().equals("O")))
      	{
	       	barray[0][0].setForeground(Color.red);
	       	barray[1][1].setForeground(Color.red);
	       	barray[2][2].setForeground(Color.red);
	       	l1.setText("Congratulations!!! You Won");
	       	friendwin++;
	       	f4.show();
	       	l1.setText(fnm.getText()+"Win "+String.valueOf(friendwin));
	       	p1.setEnabled(false);
	       	status=2;
	 	}
	    if((barray[0][2].getLabel().equals("O")) && (barray[1][1].getLabel().equals("O")) && (barray[2][0].getLabel().equals("O")))
      	{
	       	barray[0][2].setForeground(Color.red);
	       	barray[1][1].setForeground(Color.red);
	       	barray[2][0].setForeground(Color.red);
	       	l1.setText("Congratulations!!! You Won");
	       	friendwin++;
	       	f4.show();
	       	l1.setText(fnm.getText()+"Win "+String.valueOf(friendwin));
	       	p1.setEnabled(false);
	       	status=2;
	 	}

		// Computer Wins

	    if((barray[0][0].getLabel().equals("X")) && (barray[0][1].getLabel().equals("X")) && (barray[0][2].getLabel().equals("X")))
 		{
		   	barray[0][0].setForeground(Color.red);
		    barray[0][1].setForeground(Color.red);
		    barray[0][2].setForeground(Color.red);
		    p1.setEnabled(false);
		    compwin++;
		    f4.show();
		    l1.setText("Computer Win "+String.valueOf(compwin));
		    status=1;
		}
	    if((barray[1][0].getLabel().equals("X")) && (barray[1][1].getLabel().equals("X")) && (barray[1][2].getLabel().equals("X")))
 		{
		   	barray[1][0].setForeground(Color.red);
		    barray[1][1].setForeground(Color.red);
		    barray[1][2].setForeground(Color.red);
		    p1.setEnabled(false);
		    compwin++;
		    f4.show();
		    l1.setText("Computer Win "+String.valueOf(compwin));
		    status=1;
		}
	    if((barray[2][0].getLabel().equals("X")) && (barray[2][1].getLabel().equals("X")) && (barray[2][2].getLabel().equals("X")))
 		{
		    barray[2][0].setForeground(Color.red);
		    barray[2][1].setForeground(Color.red);
		    barray[2][2].setForeground(Color.red);
		    compwin++;
		    f4.show();
		    l1.setText("Computer Win "+String.valueOf(compwin));
		    status=1;
		}
	    if((barray[0][0].getLabel().equals("X")) && (barray[1][0].getLabel().equals("X")) && (barray[2][0].getLabel().equals("X")))
 		{
		    barray[0][0].setForeground(Color.red);
		    barray[1][0].setForeground(Color.red);
		    barray[2][0].setForeground(Color.red);
		    compwin++;
		    f4.show();
		    l1.setText("Computer Win "+String.valueOf(compwin));
		    p1.setEnabled(false);
		    status=1;
		}
	    if((barray[0][1].getLabel().equals("X")) && (barray[1][1].getLabel().equals("X")) && (barray[2][1].getLabel().equals("X")))
 		{
		    barray[0][1].setForeground(Color.red);
		    barray[1][1].setForeground(Color.red);
		    barray[2][1].setForeground(Color.red);
		    compwin++;
		    f4.show();
		    l1.setText("Computer Win "+String.valueOf(compwin));
		    p1.setEnabled(false);
		    status=1;
		}
	    if((barray[0][2].getLabel().equals("X")) && (barray[1][2].getLabel().equals("X")) && (barray[2][2].getLabel().equals("X")))
 		{
		    barray[0][2].setForeground(Color.red);
		    barray[1][2].setForeground(Color.red);
		    barray[2][2].setForeground(Color.red);
		    compwin++;
		    f4.show();
		    l1.setText("Computer Win "+String.valueOf(compwin));
		    p1.setEnabled(false);
		    status=1;
		}
	    if((barray[0][0].getLabel().equals("X")) && (barray[1][1].getLabel().equals("X")) && (barray[2][2].getLabel().equals("X")))
 		{
		    barray[0][0].setForeground(Color.red);
		    barray[1][1].setForeground(Color.red);
		    barray[2][2].setForeground(Color.red);
		    compwin++;
		    f4.show();
		    l1.setText("Computer Win "+String.valueOf(compwin));
		    p1.setEnabled(false);
		    status=1;
		}
	    if((barray[0][2].getLabel().equals("X")) && (barray[1][1].getLabel().equals("X")) && (barray[2][0].getLabel().equals("X")))
 		{
			barray[0][2].setForeground(Color.red);
		    barray[1][1].setForeground(Color.red);
		    barray[2][0].setForeground(Color.red);
		    compwin++;
		    f4.show();
		    l1.setText("Computer Win "+String.valueOf(compwin));
		    p1.setEnabled(false);
		    status=1;
		}
  	}
	public void replay()
	{
		// This methhod is use for play again

		f4.dispose();
		show();
		p1.setEnabled(true);
	    for(int i=0;i<3;i++)
	    {
			for(int j=0;j<3;j++)
			{
           		barray[i][j].setLabel("");
	       		barray[i][j].setBackground(Color.white);
	       		barray[i][j].setForeground(Color.black);
			}
	   	}
	   	a=0;
	    count=0;
        status=0;
  	}
}
class Tic_Tac_Toe_Main
{
	// Main Method of Program

   	public static void main(String args[])
   	{
       	TicTacToe obj=new TicTacToe();
   	}
}