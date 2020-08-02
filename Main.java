package malz.gameengine.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static JFrame Frame = new Main();
	Screen ScreenObject = new Screen();
	
	
	public Main()
	{
		add(ScreenObject);
		setUndecorated(true);
		setSize(ScreenSize);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{

	}
}
