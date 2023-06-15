
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class deletedriv extends JFrame implements ActionListener
{
	Boolean flag;
	String strDid;
	String sname,saddr,straval,sphone,scno,sejoin,sdleft;
	String date = String.format("%1$td %1$tb %1$ty",new Date());
	JButton JBDelete = new JButton("Delete",new ImageIcon("images/delete.png"));
	JButton JBReset = new JButton("Reset",new ImageIcon("images/reset.png"));
	JButton JBCancel = new JButton("Cancel",new ImageIcon("images/cancel.png"));
	
	JLabel JLPic = new JLabel(new ImageIcon("images/delete.png"));
	JLabel JLBanner = new JLabel("Choose Driver ID from the List.");

	JLabel JLId = new JLabel("Driver ID");
	
	
	JTextField JTFId = new JTextField();
		int entry=0,i=1;
    JComboBox jcb;
    String did;

    public deletedriv()
    {
    	setTitle("Delete Driver from Database");
        	 try
       {
       
        		 Class.forName("com.mysql.cj.jdbc.Driver");
         		Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","Kingtp@36");
    				    Statement stmt = c.createStatement();
    					ResultSet rs=stmt.executeQuery("select * from Driver");
    					while(rs.next())
    					{
    					  entry++;
    					  
    					}
       }
       catch(Exception e)
       {}
    		String driv[]=new String[entry+1];
    		driv[0]="-- Select 0ne --";		
    	try
    	{
    	
    		Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","Kingtp@36");

    				 Statement   stmt = con.createStatement();
    				ResultSet	rs=stmt.executeQuery("select * from Driver");
    					while(rs.next())
    					{
    					    driv[i]=rs.getString(1);
    					    System.out.println(driv[i]+ " "+rs.getString(2));
    					    i++;
    					}				
    	}
    	catch(Exception e)
    	{
    	}
    	jcb=new JComboBox(driv);
    	Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();

    	setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel JPContainer = new JPanel();

		JPContainer.setLayout(null);
		
		JBDelete.setToolTipText("Delete the whole record of the entered ID");
		JBReset.setToolTipText("Resets all the fields");
		JBCancel.setToolTipText("Return back to the main admin page");

		JLPic.setBounds(5,5,32,32);
		JPContainer.add(JLPic);

		JLBanner.setBounds(45,5,268,48);
		JLBanner.setFont(new Font("Dialog",Font.BOLD,14));
		JPContainer.add(JLBanner);

		//add ID InputField
		JLId.setBounds(10,50,105,20);
		JLId.setFont(new Font("Dialog",Font.PLAIN,12));
		
		jcb.setBounds(120,50,200,20);
	//	JTFId.setFont(new Font("Dialog",Font.PLAIN,12));

		JPContainer.add(JLId);
		JPContainer.add(jcb);

		//-- Add the JBReset
		JBReset.setBounds(112,100,99,25);
		JBReset.setFont(new Font("Dialog", Font.PLAIN, 12));
		JBReset.setMnemonic(KeyEvent.VK_R);
		JBReset.addActionListener(this);
		JBReset.setActionCommand("reset");
		//JPContainer.add(JBReset);

		//-- Add the JBDelete
		JBDelete.setBounds(12,100,99,25);
		JBDelete.setFont(new Font("Dialog", Font.PLAIN, 12));
		JBDelete.setMnemonic(KeyEvent.VK_D);
		JBDelete.addActionListener(this);
		JBDelete.setActionCommand("save");
		JPContainer.add(JBDelete);

		//-- Add the JBCancel
		JBCancel.setBounds(212,100,99,25);
		JBCancel.setFont(new Font("Dialog", Font.PLAIN, 12));
		JBCancel.setMnemonic(KeyEvent.VK_C);
		JBCancel.addActionListener(this);
		JBCancel.setActionCommand("cancel");
		JPContainer.add(JBCancel);
		
		
    	

		getContentPane().add(JPContainer);
		setLocation((screen.width-350)/2,((screen.height-165)/2));
		setSize(340,200);
		setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
    	JButton sourceBtn = (JButton)ae.getSource();
    	if(sourceBtn == JBDelete)
    	{
    		did=(String)jcb.getSelectedItem();
    	     if(did.equals("-- Select 0ne --"))
    	     	JOptionPane.showMessageDialog(null,"Select one OPTION from the Combo Box","Incorrect Selection",JOptionPane.ERROR_MESSAGE);
    				
    		else
    		{
    			int result=JOptionPane.showConfirmDialog((Component)null,"Confirmation","chose yer or no",JOptionPane.YES_NO_OPTION);
    			if(result ==JOptionPane.YES_OPTION)
    			{
    				try
					{
    					Class.forName("com.mysql.cj.jdbc.Driver");
    		    		Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","Kingtp@36");
    					Statement stmt = c.createStatement();
    					ResultSet rs=stmt.executeQuery("select * from Driver where D_No=\'"+did+"\'");
    					rs.next();
    					
    					
    							sname=rs.getString(2);
    							saddr=rs.getString(3);
    							straval=rs.getString(4);
    							sphone=rs.getString(5);
    							scno=rs.getString(6);
    							sejoin=rs.getString(7);
    						    sdleft=date;
    							System.out.println("True");
    						
    					
    					 PreparedStatement stmt1 = c.prepareStatement( "delete from Driver where D_No=?");
    					 System.out.println("Program executed");
    					 stmt1.setString(1,did);
    					 stmt1.executeUpdate();
    					 System.out.println("Program executed success fully");
		    			 PreparedStatement stmt2 = c.prepareStatement( "delete from Cab where C_No=?");
    					 System.out.println("Program executed");
    					 stmt2.setString(1,scno);
    					 stmt2.executeUpdate();
    					 System.out.println("Program executed success fully");
    				     PreparedStatement stmt3 = c.prepareStatement("insert into Del_Driver values(?,?,?,?,?,?,?)");
	    			     stmt3.setString(1,did);
	    			     stmt3.setString(2,sname);
	    			     stmt3.setString(3,saddr);
	    			     //stmt3.setString(4,straval);
	    			     stmt3.setString(4,sphone);
	    			     stmt3.setString(5,scno);
	    			     stmt3.setString(6,sejoin);
	    			     stmt3.setString(7,sdleft);
	    			     stmt3.executeUpdate();
		    			 System.out.println("Program executed success fully");	
		    			 new SplashScreen().SetMessage(4);
		    		
    						dispose();
    					
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
    			}
    		}
    	}
    	else if(sourceBtn == JBCancel)
    	{
    		//new tabbedpane();
    		dispose();
    	}
    	else if(sourceBtn == JBReset)
    	{
    		JTFId.setText("");
    	}
    }
public static void main(String args[])
{
new deletedriv();

}
}
