package malz.gameengine.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import malz.gameengine.forms.Cube;

public class ReadNoiseMap {
	static int width, height;
	static ArrayList<Integer> Colors = new ArrayList<Integer>();
	
		  public static void reader() throws IOException{
		    BufferedImage img = null;
		    File f = null;

		    //read image
		    try{
		      f = new File("/Users/Jaya/eclipse-workspace/3D Game Engine v.1.1/rec/noise.png");
		      img = ImageIO.read(f);
		      
		      width = img.getWidth();
		      height = img.getHeight();
		      
		      int i = 0;
		      
		      
		      for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
					int pixel = img.getRGB(x,y);
					if(pixel == -16777216) {
						Screen.Cubes.add(new Cube(x, y, 3, 1, 1, 1, Color.gray));
					}else if(pixel == -1) {
						Screen.Cubes.add(new Cube(x, y, 2, 1, 1, 1, Color.green));
					}else if(pixel == -4210753) {
						Screen.Cubes.add(new Cube(x, y, 1, 1, 1, 1, Color.blue));
					}else if(pixel == -8421505) {
						Screen.Cubes.add(new Cube(x, y, 2, 1, 1, 1, Color.red));
					}else if(pixel == -12632257) {
						Screen.Cubes.add(new Cube(x, y, 3, 1, 1, 1, Color.yellow));
					}
					
					}
			}
		    }catch(IOException e){
		      System.out.println(e);
		    }		    
	}
}
