package planets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Triangle extends Face2D{
	Random rand = new Random();
	public Triangle(Point3D point3d, Point3D point3d2, Point3D point3d3 ){
		vertices = new Point3D[]{point3d,point3d2,point3d3};
		xVals = new int[]{(int) point3d.x,(int) point3d2.x,(int) point3d3.x};
		yVals = new int[]{(int) point3d.y,(int) point3d2.y,(int) point3d3.y};
	}
	
	public void draw(Graphics g, int shaderSize){
		g.setColor(Color.BLACK);
		g.drawPolygon(xVals,yVals,3);
		double shader = (double)(vertices[0].z + vertices[1].z + vertices[2].z)/(3*shaderSize)/2 + 1;
		//g.setColor(new Color((int)(128 * shader) ,(int)(128 * shader) ,(int)(128 * shader)));
		g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
		g.fillPolygon(xVals,yVals,3);
	}
}
