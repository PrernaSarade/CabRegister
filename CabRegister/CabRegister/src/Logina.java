
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//import java.applet.Applet;

public class Logina extends JFrame
{
    boolean bflag=false;
    Connection con;
    Statement stmt;
    int attempt=0;
    ResultSet rs;
    String strname,strpass;
    JButton JBEnter = new JButton(" LOGIN",new ImageIcon("images/login.png"));
    JButton JBCancel = new JButton(" CANCEL",new ImageIcon("images/cancel.png"));

    //JLabel JLPic1 = new JLabel(new ImageIcon("images/car.png"));
    JLabel JLBanner2= new JLabel("Welcome Administrator");
    JLabel JLBanner = new JLabel("Login Name");
    JLabel JLBanner1 = new JLabel("Password");
    JLabel JLBanner3 = new JLabel(new ImageIcon("C:\\Users\\Tejas\\eclipse-workspace\\CabRegister\\images\\admin.gif"));
    JTextField JTFUserid = new JTextField(20);
    JPasswordField JTFPassword = new JPasswordField(20);
    //String ch = "*";
    //JTFPassword.setEchoChar("*");
    JPanel JPDialogContainer = new JPanel();

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public Logina()
   {
	setTitle("CAB REGISTER");
	JPDialogContainer.setLayout(null);

	//-- Add the JLPic1
                //JLPic1.setBounds(45,45,42,42);
                //JPDialogContainer.add(JLPic1);

	//-- Add the JLBanner
	JLBanner2.setBounds(70,1,350,100);
	JLBanner2.setFont(new Font("Old English text MT",Font.BOLD,27));
	JPDialogContainer.add(JLBanner2);
	JLBanner.setBounds(150,100,120,20);
	JLBanner.setFont(new Font("Palatino Linotype",Font.BOLD,14));
	JPDialogContainer.add(JLBanner);

        	JLBanner3.setBounds(25,70,100,100);
        	JPDialogContainer.add(JLBanner3);
	//-- Add the JLBanner1
	JLBanner1.setBounds(150,130,120,20);
	JLBanner1.setFont(new Font("Palatino Linotype",Font.BOLD,14));
	JPDialogContainer.add(JLBanner1);

	//-- Add the JTFUserid
	JTFUserid.setBounds(250,100,120,20);
	JTFUserid.setFont(new Font("Palatino Linotype",Font.BOLD,12));
	JPDialogContainer.add(JTFUserid);

	//-- Add the JTFPassword
	JTFPassword.setBounds(250,130,120,20);
	JTFPassword.setFont(new Font("Palatino Linotype",Font.BOLD,12));
	JTFPassword.addActionListener(JBActionListener);

	JPDialogContainer.add(JTFPassword);
	//End initialize variables

	//End initialize variables

	//-- Add the JBEnter
	JBEnter.setBounds(80,190,110,25);
	JBEnter.setFont(new Font("Palatino Linotype", Font.PLAIN, 12));
	JBEnter.setMnemonic(KeyEvent.VK_L);
	JBEnter.addActionListener(JBActionListener);
	JBEnter.setActionCommand("search");
	JPDialogContainer.add(JBEnter);

	//-- Add the JBCancel
	JBCancel.setBounds(230,190,110,25);
	JBCancel.setFont(new Font("Palatino Linotype", Font.PLAIN, 12));
	JBCancel.setMnemonic(KeyEvent.VK_C);
	JBCancel.addActionListener(JBActionListener);
	JBCancel.setActionCommand("cancel");
	JPDialogContainer.add(JBCancel);

	getContentPane().add(JPDialogContainer);
	setSize(450,265);
	setResizable(false);
	setLocation((screen.width - 450)/2,((screen.height-265)/2));
	setVisible(true);
}
ActionListener JBActionListener = new ActionListener()
{
	public void actionPerformed(ActionEvent e)		
	{
		 //JButton btn = (JButton)e.getSource();
		if(e.getSource() == JBEnter || e.getSource() == JTFPassword)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
    				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","Kingtp@36");
    				stmt = con.createStatement();
    				rs=stmt.executeQuery("select * from Admin");
    				while(rs.next())
    				{
    					strname=rs.getString(5);
    					strpass=rs.getString(6);
    					 if(strname.equals(JTFUserid.getText()) && strpass.equals(JTFPassword.getText()))
    					{
    						bflag=true;
    						new tabbedpane();
    					}
    					else
    					{
						bflag=false;
					}
    				}//while
    				if(bflag==true)
    				{
    					dispose();
    					//new tabbedpane();
    				}
    				else
    				{
    					attempt++;
    					System.out.println(attempt);
    					JOptionPane.showMessageDialog(null,"Invalid Admin name or Password","Error in Logging",JOptionPane.ERROR_MESSAGE);
    					JTFUserid.setText("");
    					JTFPassword.setText("");
    				}
			}//try
			catch(Exception ex)
			{
			}
			 if(attempt == 3)
                                		{
                                        JOptionPane.showMessageDialog(null,"3 Attempts Over \nYou are not a Valid Administrator","Validation Failed",JOptionPane.ERROR_MESSAGE);
                                        System.exit(0);
                                }
	}
	if(e.getSource() == JBCancel)
	{
		dispose();
		JOptionPane.showMessageDialog(null,"Logged Out.","LOGGED OUT",JOptionPane.INFORMATION_MESSAGE);
	}

	}
};
public static void main(String args[])
{
	try
	{
		new Logina();
	}
	catch(Exception e)
	{
		System.out.println("exp"+e);
	}
}
}


