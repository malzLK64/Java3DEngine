package malz.gameengine.forms;

import java.awt.Color;

import malz.gameengine.main.DPolygon;
import malz.gameengine.main.Screen;

public class Plane {
	double x, y, z, width, height;
	Color c;

	DPolygon[] Polys = new DPolygon[1000000];
	
	public Plane(double x, double y, double z, double width, double height, Color c)
	{
		int k = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Polys[k] = new DPolygon(new double[]{i, i, i+1, i+1}, new double[]{j, j+1, j+1, j},  new double[]{0, 0, 0, 0}, c);
				Screen.DPolygons.add(Polys[k]);
				k++;
			}
		}
		
		this.c = c;
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
	}
}
