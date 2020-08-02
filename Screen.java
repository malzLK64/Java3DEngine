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

	int k;
	
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
