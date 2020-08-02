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
	public static String path;
	
		  public static void reader() throws IOException{
		    BufferedImage img = null;
		    File file = null;

		    //read image
		    try{
		      //Add Path here
		      file = new File("");
		      img = ImageIO.read(file);
		      path = file.getAbsolutePath();
		      
		      width = img.getWidth();
		      height = img.getHeight();
		      
		      int i = 0;
		      
		      //Set size of Map in Blocks.
		      int size = 20;
		      
		      for (int x = 0; x < size; x++) {
				for (int y = 0; y < size; y++) {
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
