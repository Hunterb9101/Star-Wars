package planets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class PlanetTile extends Face2D{
	Random rand = new Random();
	public static Color c = Color.red;
	public PlanetTile(Point3D point3d, Point3D point3d2, Point3D point3d3 ){
		vertices = new Point3D[]{point3d,point3d2,point3d3};
		xVals = new int[]{(int) point3d.x,(int) point3d2.x,(int) point3d3.x};
		yVals = new int[]{(int) point3d.y,(int) point3d2.y,(int) point3d3.y};
	}
	
	public void draw(Graphics g, int shaderSize){
		Random rand = new Random();
		
		if(c == Color.red){
			c = Color.green;
		}
		else if(c==Color.green){
			c = Color.blue;
		}
		else{
			c = Color.red;
		}
		boolean water = rand.nextBoolean();
		
		g.setColor(c);
		g.fillPolygon(xVals,yVals,3);
		
		g.setColor(Color.BLACK);
		//g.drawPolygon(xVals,yVals,3);
	}
}
