import java.awt.*;
import javax.swing.*;
public class SplashScreen extends JWindow implements Runnable
{
    Thread SplashThread;
    int intValue =-1;
    JPanel content =(JPanel)this.getContentPane();
    JLabel labelImage = new JLabel("",JLabel.CENTER);
  public SplashScreen()
  {
    SplashThread = new Thread(this);
    content.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
    content.add(labelImage, BorderLayout.CENTER);
    this.setAlwaysOnTop(true);
    this.invalidate();
    this.validate();
  }
  public void run()
  {  	
    if(intValue == 1)                       ////////Splash At the starting of Project
    {
        labelImage.setIcon(new ImageIcon("images/ProcessinData.png"));
        this.setBounds(100,100,850,525);
        this.setVisible(true);
        try
        {
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
        }
        this.dispose();
    }

    
    if(intValue == 2||intValue == 3||intValue == 4)                   //////////Splash For PopUp Messages
    {    
        if(intValue == 2)                                 ////Splash Of Data Encrypted
        {
            labelImage.setIcon(new ImageIcon("images/saved.png"));
        }
        else
        if(intValue == 3)                                 ////Splash Of Image Saved
        {
            labelImage.setIcon(new ImageIcon("images/updated.png"));
        }
        else
        if(intValue == 4)                                 ////Splash Of Text File Saved
        {
            labelImage.setIcon(new ImageIcon("images/removed.png"));
        }
        
        
        int Width=229,Height=135,i,j;    
        int X=(Toolkit.getDefaultToolkit().getScreenSize().width-230);
        int Y=(Toolkit.getDefaultToolkit().getScreenSize().height-55);
        for(i=10;i<(Height);i=i+10)
        {
            try
            {
                Thread.sleep(50);
            }catch(InterruptedException E)
            {
            }
                this.setBounds(X,(Y-i),Width,(Width-(Width-i)+10));
                this.setVisible(true);
        }					
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException E)
        {
        }
        for(j=i-10;j>-20;j=j-10)
        {
            try
            {
                Thread.sleep(50);
            }catch(InterruptedException E)
            {
            }
            this.setBounds(X,(Y-j),Width,(Width-(Width-j)+10));
            this.setVisible(true);
        }
        this.dispose();  
    }
    
  }
 public void SetMessage(int value)
 {
 	this.intValue = value;
        SplashThread.start();
 }
public static void main(String args[])
{
new SplashScreen();
}
}
