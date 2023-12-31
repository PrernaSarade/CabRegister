//Only Working Driver is active

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

public class SearchAll extends JDialog
{
	//Start create variables
	JButton JBSearch = new JButton("Search",new ImageIcon("images/search.png"));
	JButton JBCancel = new JButton("Cancel",new ImageIcon("images/cancel.png"));

	JLabel JLPic1 = new JLabel(new ImageIcon("images/bSearch.png"));
	JLabel JLBanner = new JLabel("Please select where to locate.");
	JLabel JLSearchFor = new JLabel("Search For:");
	JLabel JLSearchIn = new JLabel("Look In:");

	JPanel JPDialogContainer = new JPanel();
	JTextField JTFSearchFor = new JTextField();

	JComboBox JCSearchIn;
	Boolean flag;
	String strEid,str;
    Connection c;
	Statement s;
    ResultSet rs1;
       

	Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
	//End create variables

	public SearchAll()
	{
		setTitle("Search");
		toolTips();
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		//Start Initalize variables
		String StrListItem[]={"- Select One -","Working_Driver","Not_Working_Driver","Employees","Log Register"};
		JCSearchIn = new JComboBox(StrListItem);
		StrListItem = null;

		JPDialogContainer.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//-- Add the JLPic1
		JLPic1.setBounds(5,5,32,32);
		JPDialogContainer.add(JLPic1);

		//-- Add the JLBanner
		JLBanner.setBounds(55,5,280,48);
		JLBanner.setFont(new Font("Dialog",Font.PLAIN,12));
		JPDialogContainer.add(JLBanner);
		//End initialize variables

		//-- Add Name Input Field
		JLSearchIn.setBounds(5,52,105,20);
		JLSearchIn.setFont(new Font("Dialog",Font.PLAIN,12));

		JCSearchIn.setBounds(110,52,225,20);
		JCSearchIn.setFont(new Font("Dialog",Font.PLAIN,12));

		JPDialogContainer.add(JLSearchIn);
		JPDialogContainer.add(JCSearchIn);
		JCSearchIn.addActionListener(JBActionListener);
		//******************** End adding of input field

		//-- Add the JBSearch
		JBSearch.setBounds(110,100,99,25);
		JBSearch.setFont(new Font("Dialog", Font.PLAIN, 12));
		JBSearch.setMnemonic(KeyEvent.VK_S);
		JBSearch.addActionListener(JBActionListener);
		JBSearch.setActionCommand("search");
		JPDialogContainer.add(JBSearch);

		//-- Add the JBCancel
		JBCancel.setBounds(227,100,99,25);
		JBCancel.setFont(new Font("Dialog", Font.PLAIN, 12));
		JBCancel.setMnemonic(KeyEvent.VK_C);
		JBCancel.addActionListener(JBActionListener);
		JBCancel.setActionCommand("cancel");
		JPDialogContainer.add(JBCancel);

		getContentPane().add(JPDialogContainer);
		setSize(350,165);
		setResizable(false);
		setLocation((screen.width - 350)/2,((screen.height-165)/2));
		setVisible(true);

	}
	ActionListener JBActionListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
    		String str = (String)JCSearchIn.getSelectedItem();
    		
    		str = JCSearchIn.getSelectedItem().toString();
    		if(ae.getSource()==JBSearch)
    		{
    			
    			
    			if(str=="- Select One -")
    			{
    				JOptionPane.showMessageDialog(null,"Select one OPTION from the Combo Box","Incorrect Selection",JOptionPane.INFORMATION_MESSAGE);
    			}
    			if(str == "Working_Driver")
    			{
		    		System.out.println("Driver");
		    		new W_Driver(str);
		    		dispose();
    			}
    			if(str == "Not_Working_Driver")
    			{
		    		System.out.println("Del_Driver");
		    		new N_W_Driver(str);
		    		dispose();
    			}
    			if(str == "Employees")
    			{
		    		System.out.println("Employee");
		    		new Empl(str);
		    		dispose();
    			}
    		
    			if(str == "Log Register")
    			{
    				new Search();
    				dispose();
    			}
    		}
    		if(ae.getSource()==JBCancel)
    		{
    			dispose();
    		}	
		}
	};
	public void toolTips()
	{
		JBSearch.setToolTipText("Search For The Driver From The Security Log");
		JBCancel.setToolTipText("Go Back To Main Admin Page");
	}
	public static void main(String s[])
	{
		new SearchAll();
	}
}
class W_Driver extends JFrame
{
	int i=0,j=0,type;
	int count =0;
	Boolean flag;
	String string;
	String strEid;
    Connection c;
	Statement s;
    ResultSet rs1,rs;

	JPanel JPContainer = new JPanel();
    JScrollPane jsp=new JScrollPane();

    JLabel JLBanner = new JLabel("Search Results");
    JLabel JLPic = new JLabel(new ImageIcon("images/bSearch.png"));
	JLabel StatusLabel = new JLabel("CREATED BY Prerna Sarade TYBCS :VP COLLAGE",JLabel.CENTER);

    JButton JBSearch = new JButton("Search More",new ImageIcon("images/search.png"));

    JButton JBExit = new JButton("Exit Search",new ImageIcon("images/branch.png"));

	JMenuBar MMenubar = new JMenuBar();

	public W_Driver(String str)
	{
	   setTitle("Search Result");	
           setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPContainer.setLayout(null);

		
		try
		{
			String[] colHeads={"I D","NAME","ADDRESS","PHONE NUMBER","DATE_JOIN"};
	                str="Driver";
			string= "SELECT * FROM "+str;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","Kingtp@36");
			s = c.createStatement();
			rs = s.executeQuery(string);
			while(rs.next())
			{
				count++;
			}
			System.out.println(count);
			//rs1 = null;
			rs1 = s.executeQuery(string);
			Object data[][]=new Object[count][5];
		
                         System.out.println(count+"u");
                        while(rs1.next())
			{ System.out.println(count);
				for(j=1;j<=5;j++)
				{
				    if(j==4)	
			             	data[i][j-1]=rs1.getString(j+1);
                                     else if(j==5)
                                         data[i][j-1]=rs1.getString(j+2);
					else
					data[i][j-1]=rs1.getString(j);
				}
				i++;
			}
                        
			 JTable Jtable=new JTable(data,colHeads);
                        
                        jsp.setViewportView(Jtable);
			JPContainer.add(jsp);
		}
		catch(Exception e)
		{System.out.println(e);}

		MMenubar.add(JBSearch);
		JBSearch.addActionListener(JBActionListener);
		MMenubar.add(JBExit);
		JBExit.addActionListener(JBActionListener);
		setJMenuBar(MMenubar);

		jsp.setBounds(10,100,800,320);
		JPContainer.add(jsp);

		JLPic.setBounds(200,25,32,32);
		JPContainer.add(JLPic);

		JLBanner.setBounds(270,10,350,55);
		JLBanner.setFont(new Font("Old English text MT",Font.BOLD,40));
		JPContainer.add(JLBanner);

		StatusLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(StatusLabel,BorderLayout.PAGE_END);
		getContentPane().add(JPContainer);

		setSize(820,500);
		setResizable(false);
		setLocation(100,100);
		setVisible(true);
	}
	
	ActionListener JBActionListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource() == JBSearch)
			{
				new SearchAll();
				dispose();
			}
			if(ae.getSource()== JBExit)
			{
				dispose();
			}
		}
	};
}

class N_W_Driver extends JFrame
{
	int i=0,j=0,type;
	int count =0;
	Boolean flag;
	String string;
	String strEid;
    Connection c;
	Statement s;
    ResultSet rs1,rs;
        
	JPanel JPContainer = new JPanel();
    JScrollPane jsp=new JScrollPane();

    JLabel JLBanner = new JLabel("Search Results");
    JLabel JLPic = new JLabel(new ImageIcon("images/bSearch.png"));
	JLabel StatusLabel = new JLabel("CREATED BY Prerna Sarade TYBCS :VP COLLAGE",JLabel.CENTER);

    JButton JBSearch = new JButton("Search More",new ImageIcon("images/search.png"));

    JButton JBExit = new JButton("Exit Search",new ImageIcon("images/branch.png"));

	JMenuBar MMenubar = new JMenuBar();

	public N_W_Driver(String str)
	{
	 setTitle("Search Result");
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPContainer.setLayout(null);

		
		try
		{
			String[] colHeads={"I D","NAME","ADDRESS","PHONE NUMBER","DATE_JOIN","DATE_LEFT"};
	                str="Del_Driver";
			string= "SELECT * FROM "+str;
			Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","Kingtp@36");
			s = c.createStatement();
			rs = s.executeQuery(string);
			while(rs.next())
			{
				count++;
			}
			System.out.println(count);
			//rs1 = null;
			rs1 = s.executeQuery(string);
			Object data[][]=new Object[count][6];
			 
                        while(rs1.next())
			{
				for(j=1;j<=6;j++)
				{
                                  if(j==4)	
			             	data[i][j-1]=rs1.getString(j+1);
                                     else if(j==5)
                                         data[i][j-1]=rs1.getString(j+2);
					else if(j==6)
                                          data[i][j-1]=rs1.getString(j+2);
                                          else
					data[i][j-1]=rs1.getString(j);
                         	}
				i++;
			}
                        
			 JTable Jtable=new JTable(data,colHeads);
                        
                        jsp.setViewportView(Jtable);
			JPContainer.add(jsp);
		}
		catch(Exception e)
		{System.out.println(e);}

		MMenubar.add(JBSearch);
		JBSearch.addActionListener(JBActionListener);
		MMenubar.add(JBExit);
		JBExit.addActionListener(JBActionListener);
		setJMenuBar(MMenubar);

		jsp.setBounds(10,100,800,320);
		JPContainer.add(jsp);

		JLPic.setBounds(200,25,32,32);
		JPContainer.add(JLPic);

		JLBanner.setBounds(270,10,350,55);
		JLBanner.setFont(new Font("Old English text MT",Font.BOLD,40));
		JPContainer.add(JLBanner);

		StatusLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(StatusLabel,BorderLayout.PAGE_END);
		getContentPane().add(JPContainer);

		setSize(820,500);
		setResizable(false);
		setLocation(100,100);
		setVisible(true);
	}
	
	ActionListener JBActionListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource() == JBSearch)
			{
				new SearchAll();
				dispose();
			}
			
			if(ae.getSource()== JBExit)
			{
				dispose();
			}
		}
	};
}

class Empl extends JFrame
{
	int i=0,j=0,type;
	int count =0;
	Boolean flag;
	String string;
	String strEid;
    Connection c;
	Statement s;
    ResultSet rs1,rs;

	JPanel JPContainer = new JPanel();
    JScrollPane jsp=new JScrollPane();

    JLabel JLBanner = new JLabel("Search Results");
    JLabel JLPic = new JLabel(new ImageIcon("images/bSearch.png"));
	JLabel StatusLabel = new JLabel("CREATED BY Prerna Sarade TYBCS :VP COLLAGE",JLabel.CENTER);

    JButton JBSearch = new JButton("Search More",new ImageIcon("images/search.png"));

    JButton JBExit = new JButton("Exit Search",new ImageIcon("images/branch.png"));

	JMenuBar MMenubar = new JMenuBar();

	public Empl(String str)
	{
                 setTitle("Search Result");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPContainer.setLayout(null);

		
		
		try
		{
			String[] colHeads={"I D","NAME","ADDRESS","PHONE NUMBER","PROCESS"};
	                str="Employee";
			string= "SELECT * FROM "+str;
			Class.forName("com.mysql,cj.jdbc.Driver");
    		Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","Kingtp@36");
			s = c.createStatement();
			rs = s.executeQuery(string);
			while(rs.next())
			{
				count++;
			}
			System.out.println(count);
			//rs1 = null;
			rs1 = s.executeQuery(string);
			Object data[][]=new Object[count][6];
                        while(rs1.next())
			{
				for(j=1;j<=5;j++)
				{
				    if(j==4)	
					data[i][j-1]=rs1.getString(j+1);
					else if(j==5)
                                        data[i][j-1]=rs1.getString(j+1);
                                         else
					data[i][j-1]=rs1.getString(j);
				}
				i++;
			}
                        
                        
			 JTable Jtable=new JTable(data,colHeads);
                        
                        jsp.setViewportView(Jtable);
			JPContainer.add(jsp);
		}
		catch(Exception e)
		{System.out.println(e);}

		MMenubar.add(JBSearch);
		JBSearch.addActionListener(JBActionListener);
		
		MMenubar.add(JBExit);
		JBExit.addActionListener(JBActionListener);
		setJMenuBar(MMenubar);

		jsp.setBounds(10,100,800,320);
		JPContainer.add(jsp);

		JLPic.setBounds(200,25,32,32);
		JPContainer.add(JLPic);

		JLBanner.setBounds(270,10,350,55);
		JLBanner.setFont(new Font("Old English text MT",Font.BOLD,40));
		JPContainer.add(JLBanner);

		StatusLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(StatusLabel,BorderLayout.PAGE_END);
		getContentPane().add(JPContainer);

		setSize(820,500);
		setResizable(false);
		setLocation(100,100);
		setVisible(true);
	}
	
	ActionListener JBActionListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource() == JBSearch)
			{
				new SearchAll();
				dispose();
			}
			
			if(ae.getSource()== JBExit)
			{
				dispose();
			}
		}
	};
}
