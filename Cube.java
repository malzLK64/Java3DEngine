package malz.gameengine.forms;

import java.awt.Color;

import malz.gameengine.main.DPolygon;
import malz.gameengine.main.Screen;

public class Cube {
	double x, y, z, width, length, height;
	Color c;

	//List of 6 Polygons for each side of the Cube
	DPolygon[] Polys = new DPolygon[6];
	
	public Cube(double x, double y, double z, double width, double length, double height, Color c)
	{
		Polys[0] = new DPolygon(new double[]{x, x+width, x+width, x}, new double[]{y, y, y+length, y+length},  new double[]{z, z, z, z}, c);
		Screen.DPolygons.add(Polys[0]);
		Polys[1] = new DPolygon(new double[]{x, x+width, x+width, x}, new double[]{y, y, y+length, y+length},  new double[]{z+height, z+height, z+height, z+height}, c);
		Screen.DPolygons.add(Polys[1]);
		Polys[2] = new DPolygon(new double[]{x, x, x+width, x+width}, new double[]{y, y, y, y},  new double[]{z, z+height, z+height, z}, c);
		Screen.DPolygons.add(Polys[2]);
		Polys[3] = new DPolygon(new double[]{x+width, x+width, x+width, x+width}, new double[]{y, y, y+length, y+length},  new double[]{z, z+height, z+height, z}, c);
		Screen.DPolygons.add(Polys[3]);
		Polys[4] = new DPolygon(new double[]{x, x, x+width, x+width}, new double[]{y+length, y+length, y+length, y+length},  new double[]{z, z+height, z+height, z}, c);
		Screen.DPolygons.add(Polys[4]);
		Polys[5] = new DPolygon(new double[]{x, x, x, x}, new double[]{y, y, y+length, y+length},  new double[]{z, z+height, z+height, z}, c);
		Screen.DPolygons.add(Polys[5]);
		
		this.c = c;
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.length = length;
		this.height = height;
	}
}
