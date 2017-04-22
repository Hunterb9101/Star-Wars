package planets;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Planet extends Object3D{
	public ArrayList<Point3D> vertices = new ArrayList<Point3D>();
	public int size = 100;
	public int centerX = 300;
	public int centerY = 300;
	
//////////////////////////////////////////////
//				MESH CREATION				//
//////////////////////////////////////////////
    public void createMesh(int recursionLevel) {
        int t = (int) (size*2/3 * (1.0 + Math.sqrt(5.0)) / 2.0); // It is 2/3 the size so that the radius is larger than it
        vertices.add(new Point3D((-size*2/3 + centerX),  t + centerY,  0));
        vertices.add(new Point3D( size*2/3 + centerX,  t + centerY,  0));
        vertices.add(new Point3D(-size*2/3 + centerX, -t + centerY,  0));
        vertices.add(new Point3D( size*2/3 + centerX, -t + centerY,  0));

        vertices.add(new Point3D( centerX, -size*2/3 + centerY,  t));
        vertices.add(new Point3D( centerX,  size*2/3 + centerY,  t));
        vertices.add(new Point3D( centerX, -size*2/3 + centerY, -t));
        vertices.add(new Point3D( centerX,  size*2/3 + centerY, -t));

        vertices.add(new Point3D( t + centerX,  centerY, -size));
        vertices.add(new Point3D( t + centerX,  centerY,  size));
        vertices.add(new Point3D(-t + centerX,  centerY, -size));
        vertices.add(new Point3D(-t + centerX,  centerY,  size));

        // 5 faces around point 0
        faces.add(new PlanetTile(vertices.get(0),vertices.get(11),vertices.get(5)));
        faces.add(new PlanetTile(vertices.get(0), vertices.get(5), vertices.get(1)));
        faces.add(new PlanetTile(vertices.get(0), vertices.get(1), vertices.get(7)));
        faces.add(new PlanetTile(vertices.get(0), vertices.get(7), vertices.get(10)));
        faces.add(new PlanetTile(vertices.get(0), vertices.get(10), vertices.get(11)));

        // 5 adjacent faces 
        faces.add(new PlanetTile(vertices.get(1), vertices.get(5), vertices.get(9)));
        faces.add(new PlanetTile(vertices.get(5), vertices.get(11), vertices.get(4)));
        faces.add(new PlanetTile(vertices.get(11), vertices.get(10), vertices.get(2)));
        faces.add(new PlanetTile(vertices.get(10), vertices.get(7), vertices.get(6)));
        faces.add(new PlanetTile(vertices.get(7), vertices.get(1), vertices.get(8)));

        // 5 faces around point 3
        faces.add(new PlanetTile(vertices.get(3), vertices.get(9), vertices.get(4)));
        faces.add(new PlanetTile(vertices.get(3), vertices.get(4), vertices.get(2)));
        faces.add(new PlanetTile(vertices.get(3), vertices.get(2), vertices.get(6)));
        faces.add(new PlanetTile(vertices.get(3), vertices.get(6), vertices.get(8)));
        faces.add(new PlanetTile(vertices.get(3), vertices.get(8), vertices.get(9)));

        // 5 adjacent faces 
        faces.add(new PlanetTile(vertices.get(4), vertices.get(9), vertices.get(5)));
        faces.add(new PlanetTile(vertices.get(2), vertices.get(4), vertices.get(11)));
        faces.add(new PlanetTile(vertices.get(6), vertices.get(2), vertices.get(10)));
        faces.add(new PlanetTile(vertices.get(8), vertices.get(6), vertices.get(7)));
        faces.add(new PlanetTile(vertices.get(9), vertices.get(8), vertices.get(1))); 
        
        for (int i = 0; i < recursionLevel; i++) {
        	ArrayList<Face2D> faces2 = new ArrayList<Face2D>();
        	for(int f = 0; f<faces.size(); f++){
        		Point3D a = createMiddlePoint(faces.get(f).vertices[0], faces.get(f).vertices[1]);
        		Point3D b = createMiddlePoint(faces.get(f).vertices[1], faces.get(f).vertices[2]);
        		Point3D c = createMiddlePoint(faces.get(f).vertices[2], faces.get(f).vertices[0]);

        		faces2.add(new PlanetTile(faces.get(f).vertices[0], a, c));
        		faces2.add(new PlanetTile(faces.get(f).vertices[1], b, a));
        		faces2.add(new PlanetTile(faces.get(f).vertices[2], c, b));
        		faces2.add(new PlanetTile(a, b, c));
        		faces.remove(f);
        		f--;
        	}
        	//System.out.println("Recursion Level: " + i + ", Faces:" + faces2.size() );
          faces.addAll(faces2);
        }
    }
    
    private Point3D createMiddlePoint(Point3D p1, Point3D p2){
        // not in cache, calculate it
        Point3D middle = new Point3D(
            (int)((p1.x + p2.x) / 2.0), 
            (int)((p1.y + p2.y) / 2.0), 
            (int)((p1.z + p2.z) / 2.0));
        middle.x -= centerX;
        middle.y -= centerY;
        // add vertex makes sure point is on unit sphere (Problem lies in these two statements)
        double magnitude = Math.sqrt(Math.pow(middle.x,2)+Math.pow(middle.y,2)+Math.pow(middle.z,2));
        double radius = (size*(1.0 + Math.sqrt(5.0)))/2;
        middle = new Point3D((int)((double)(middle.x)*radius/(double)magnitude) + centerX, (int)((double)(middle.y)*radius/(double)magnitude) + centerY, (int)((double)middle.z*radius/(double)magnitude));
        
        return middle;
    }
//////////////////////////////////////////////
//				FLOOD FILL  				//
//////////////////////////////////////////////   
    public void generateContinents(){
    	int facesToColor = faces.size();
    }
}
