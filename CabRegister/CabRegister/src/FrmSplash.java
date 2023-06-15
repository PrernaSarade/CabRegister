
import javax.swing.*;
import java.awt.*;
import java.lang.*;

public class FrmSplash extends JWindow implements Runnable
{
	Thread t;
	public FrmSplash() throws Exception
	{
		t=new Thread(this);
		t.start();
	}
	
	public void run()
	{
		
		JLabel SplashLabel = new JLabel(new ImageIcon("images/cab2.JPG"));
		Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
		JPanel content =(JPanel)this.getContentPane();
		getContentPane().add(SplashLabel,BorderLayout.CENTER);
		content.setBorder(BorderFactory.createLineBorder(Color.white, 4));
		setSize(635,355);
		setLocation((screen.width - 635)/4,((screen.height-355)/8));
		show();
		try
		{
			t.sleep(4000);
			dispose();
			new Logina();
		}
		catch(InterruptedException e)
		{}
	}	
	public static void main(String args[]) throws Exception
	{
		new FrmSplash();
	}
		
	

	
}

