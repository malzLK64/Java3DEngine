package malz.gameengine.forms;

import java.awt.Color;

import malz.gameengine.main.DPolygon;
import malz.gameengine.main.Screen;


//TODO: Calculate Cylinder
public class Cylinder {
	double x, y, z, width, length, height, diameter, scope;
	Color c;
	DPolygon[] Polys = new DPolygon[5];
	
	public Cylinder(double x, double y, double z, double width, double height, double innerRects, double diameter, double scope, Color c)
	{
		for (int i = 1; i < innerRects; i++) {
			
		}
		
		this.c = c;
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.diameter = diameter;
		this.scope = scope;
		this.height = height;
	}
}
