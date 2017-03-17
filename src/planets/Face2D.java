package planets;

import java.awt.Graphics;

abstract class Face2D {
	Point3D[] vertices;
	protected int[] xVals;
	protected int[] yVals;
	
	public int getZAverage(){
		int sum = 0;
		for(int i = 0; i<vertices.length;i++){
			sum+=vertices[i].z;
		}
		return (int)(sum/vertices.length);
	}
	
	abstract void draw(Graphics g, int shaderSize);
}
