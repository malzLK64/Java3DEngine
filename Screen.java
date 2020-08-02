package malz.gameengine.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import malz.gameengine.forms.Cube;
import malz.gameengine.forms.Cylinder;
import malz.gameengine.forms.Plane;

public class Screen extends JPanel implements KeyListener {
	double SleepTime = 1000/30, lastRefresh = 0;
	static double[] ViewFrom = new double[] {10, 10, 10};
	static double[] ViewTo = new double[] {1, 1, 1.5};
	static int NumberOfPolygons = 0, NumberOf3DPolygons = 0;
	static PolygonObject[] DrawablePolygons = new PolygonObject[100000];
	int[] NewOrder;
	boolean[] Keys = new boolean[8];
	long frames, currentFrame, firstFrame, fps;
	static boolean Lines = true;
	
	ReadNoiseMap rnm = new ReadNoiseMap();
	
	public static ArrayList<DPolygon> DPolygons = new ArrayList<DPolygon>();
		
	//List of 3D Objects
	static ArrayList<Cube> Cubes = new ArrayList<Cube>();
	static ArrayList<Plane> Planes = new ArrayList<Plane>();
	static ArrayList<Cylinder> Cylinders = new ArrayList<Cylinder>();

	
	public Screen()
	{
		addKeyListener(this);
		setFocusable(true);

		try {
			ReadNoiseMap.reader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Cube			   x   y  z  w	l  h    c
		//Cubes.add(new Cube(0, -5, 0, 2, 2, 2, Color.gray));
		
		// Plane			 x	y  z   w   h     c
		//Planes.add(new Plane(10, 10, 0, 20, 20, Color.green));
	}
	
	public void paintComponent(Graphics g)
	{
		
		Controls();
		
		g.clearRect(0, 0, 2000, 1200);
		g.drawString("FPS: " + fps  + "   Polygons: " + NumberOf3DPolygons, 50, 50);
		g.drawString("Camera", 50, 80);
		g.drawString("ViewTo     X:" + Math.round(ViewTo[0]) + " Y: " + Math.round(ViewTo[1]) + " Z: " + Math.round(ViewTo[2]), 50, 95);
		g.drawString("ViewFrom   X:" + Math.round(ViewFrom[0]) + " Y: " + Math.round(ViewFrom[1]) + " Z: " + Math.round(ViewFrom[2]), 50, 110);
		
		frames++;
		currentFrame = System.currentTimeMillis();
		if(currentFrame > firstFrame + 1000){
		     firstFrame = currentFrame;
		     fps = frames;
		     frames = 0;
}
		
		for(int i = 0; i < DPolygons.size(); i++)
			DPolygons.get(i).updatePolygon();
		
		setOrder();

		for(int i = 0; i < NumberOfPolygons; i++) {
			DrawablePolygons[NewOrder[i]].drawPolygon(g);
		}
		
		SleepAndRefresh();
	}
	
	void setOrder()
	{
		double[] k = new double[NumberOfPolygons];
		NewOrder = new int[NumberOfPolygons];
		
		for(int i = 0; i < NumberOfPolygons; i++)
		{
			k[i] = DrawablePolygons[i].AvgDistance;	
			NewOrder[i] = i;
		}
		
	    double temp;
	    int tempr;	    
		for (int a = 0; a < k.length-1; a++)
			for (int b = 0; b < k.length-1; b++)
				if(k[b] < k[b + 1])
				{
					temp = k[b];
					tempr = NewOrder[b];
					NewOrder[b] = NewOrder[b + 1];
					k[b] = k[b + 1];
					   
					NewOrder[b + 1] = tempr;
					k[b + 1] = temp;
				}
	}
	
	void SleepAndRefresh()
	{
		while(true)
		{
			if(System.currentTimeMillis() - lastRefresh > SleepTime)
			{
				lastRefresh = System.currentTimeMillis();
				repaint();
				break;
			}
			else
			{
				try 
				{
					Thread.sleep((long)(System.currentTimeMillis() - lastRefresh));
				} 
				catch (Exception e) 
				{

				}
			}
		}
	}

	void Controls()
	{
		if(Keys[0]) {
			ViewTo[0] += 0.2;
		}
		
		if(Keys[1]) {
			ViewTo[0] -= 0.2;
		}
		
		if(Keys[2]) {
			ViewTo[2] += 0.2;
		}
		
		if(Keys[3]) {
			ViewTo[2] -= 0.2;
		}
		
		Vector ViewVector = new Vector(ViewTo[0] - ViewFrom[0], ViewTo[1] - ViewFrom[1], ViewTo[2] - ViewFrom[2]);
		if(Keys[4])
		{
			ViewFrom[0] += ViewVector.x;
			ViewFrom[1] += ViewVector.y;
			ViewFrom[2] += ViewVector.z;
			ViewTo[0] += ViewVector.x;
			ViewTo[1] += ViewVector.y;
			ViewTo[2] += ViewVector.z;
		}
		
		if(Keys[6])
		{
			ViewFrom[0] -= ViewVector.x;
			ViewFrom[1] -= ViewVector.y;
			ViewFrom[2] -= ViewVector.z;
			ViewTo[0] -= ViewVector.x;
			ViewTo[1] -= ViewVector.y;
			ViewTo[2] -= ViewVector.z;
		}
		
		Vector VerticalVector = new Vector(0, 0, 1);
		Vector SideViewVector = ViewVector.CrossProduct(VerticalVector);
		
		if(Keys[5])
		{
			ViewFrom[0] += SideViewVector.x;
			ViewFrom[1] += SideViewVector.y;
			ViewFrom[2] += SideViewVector.z;
			ViewTo[0] += SideViewVector.x;
			ViewTo[1] += SideViewVector.y;
			ViewTo[2] += SideViewVector.z;
		}
		
		if(Keys[7])
		{
			ViewFrom[0] -= SideViewVector.x;
			ViewFrom[1] -= SideViewVector.y;
			ViewFrom[2] -= SideViewVector.z;
			ViewTo[0] -= SideViewVector.x;
			ViewTo[1] -= SideViewVector.y;
			ViewTo[2] -= SideViewVector.z;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			Keys[0] = true;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			Keys[1] = true;
		if(e.getKeyCode() == KeyEvent.VK_UP)
			Keys[2] = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			Keys[3] = true;
		if(e.getKeyCode() == KeyEvent.VK_W)
			Keys[4] = true;
		if(e.getKeyCode() == KeyEvent.VK_A)
			Keys[5] = true;
		if(e.getKeyCode() == KeyEvent.VK_S)
			Keys[6] = true;
		if(e.getKeyCode() == KeyEvent.VK_D)
			Keys[7] = true;
		if(e.getKeyCode() == KeyEvent.VK_R) 
			Keys[8] = true;
		if(e.getKeyCode() == KeyEvent.VK_X) 
			Keys[9] = true;
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			Keys[0] = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			Keys[1] = false;
		if(e.getKeyCode() == KeyEvent.VK_UP)
			Keys[2] = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			Keys[3] = false;
		if(e.getKeyCode() == KeyEvent.VK_W)
			Keys[4] = false;
		if(e.getKeyCode() == KeyEvent.VK_A)
			Keys[5] = false;
		if(e.getKeyCode() == KeyEvent.VK_S)
			Keys[6] = false;
		if(e.getKeyCode() == KeyEvent.VK_D)
			Keys[7] = false;
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}