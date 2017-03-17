package planets;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Icosphere extends Object3D{
	public ArrayList<Point3D> vertices = new ArrayList<Point3D>();
	int size = 50;
	int x = 300;
	int y = 300;
    public void create(int recursionLevel) {

        int t = (int) (size*(1.0 + Math.sqrt(5.0)) / 2.0);
        
        vertices.add(new Point3D(-size + x,  t + y,  0));
        vertices.add(new Point3D( size + x,  t + y,  0));
        vertices.add(new Point3D(-size + x, -t + y,  0));
        vertices.add(new Point3D( size + x, -t + y,  0));

        vertices.add(new Point3D( x, -size + y,  t));
        vertices.add(new Point3D( x,  size + y,  t));
        vertices.add(new Point3D( x, -size + y, -t));
        vertices.add(new Point3D( x,  size + y, -t));

        vertices.add(new Point3D( t + x,  y, -size));
        vertices.add(new Point3D( t + x,  y,  size));
        vertices.add(new Point3D(-t + x,  y, -size));
        vertices.add(new Point3D(-t + x,  y,  size));



        // 5 faces around point 0
        faces.add(new Triangle(vertices.get(0),vertices.get(11),vertices.get(5)));
        faces.add(new Triangle(vertices.get(0), vertices.get(5), vertices.get(1)));
        faces.add(new Triangle(vertices.get(0), vertices.get(1), vertices.get(7)));
        faces.add(new Triangle(vertices.get(0), vertices.get(7), vertices.get(10)));
        faces.add(new Triangle(vertices.get(0), vertices.get(10), vertices.get(11)));

        // 5 adjacent faces 
        faces.add(new Triangle(vertices.get(1), vertices.get(5), vertices.get(9)));
        faces.add(new Triangle(vertices.get(5), vertices.get(11), vertices.get(4)));
        faces.add(new Triangle(vertices.get(11), vertices.get(10), vertices.get(2)));
        faces.add(new Triangle(vertices.get(10), vertices.get(7), vertices.get(6)));
        faces.add(new Triangle(vertices.get(7), vertices.get(1), vertices.get(8)));

        // 5 faces around point 3
        faces.add(new Triangle(vertices.get(3), vertices.get(9), vertices.get(4)));
        faces.add(new Triangle(vertices.get(3), vertices.get(4), vertices.get(2)));
        faces.add(new Triangle(vertices.get(3), vertices.get(2), vertices.get(6)));
        faces.add(new Triangle(vertices.get(3), vertices.get(6), vertices.get(8)));
        faces.add(new Triangle(vertices.get(3), vertices.get(8), vertices.get(9)));

        // 5 adjacent faces 
        faces.add(new Triangle(vertices.get(4), vertices.get(9), vertices.get(5)));
        faces.add(new Triangle(vertices.get(2), vertices.get(4), vertices.get(11)));
        faces.add(new Triangle(vertices.get(6), vertices.get(2), vertices.get(10)));
        faces.add(new Triangle(vertices.get(8), vertices.get(6), vertices.get(7)));
        faces.add(new Triangle(vertices.get(9), vertices.get(8), vertices.get(1)));     
    }
}
