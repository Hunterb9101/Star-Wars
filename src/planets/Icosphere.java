package planets;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Icosphere extends Object3D{
	public ArrayList<Point3D> vertices = new ArrayList<Point3D>();
	int size = 100;
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
        
        for (int i = 0; i < recursionLevel; i++) {
        	ArrayList<Face2D> faces2 = new ArrayList<Face2D>();
          for(int f = 0; f<faces.size(); f++){
              // replace triangle by 4 triangles
              Point3D a = createMiddlePoint(faces.get(i).vertices[0], faces.get(i).vertices[1]);
              Point3D b = createMiddlePoint(faces.get(i).vertices[1], faces.get(i).vertices[2]);
              Point3D c = createMiddlePoint(faces.get(i).vertices[2], faces.get(i).vertices[0]);

              faces2.add(new Triangle(faces.get(i).vertices[0], a, c));
              faces2.add(new Triangle(faces.get(i).vertices[1], b, a));
              faces2.add(new Triangle(faces.get(i).vertices[2], c, b));
              faces2.add(new Triangle(a, b, c));
              faces.remove(f);
              f--;
          }
          faces.addAll(faces2);
          System.out.println(faces.size());
        }
    }
    
    private Point3D createMiddlePoint(Point3D p1, Point3D p2){
        // not in cache, calculate it
        Point3D middle = new Point3D(
            (int)((p1.x + p2.x) / 2.0), 
            (int)((p1.y + p2.y) / 2.0), 
            (int)((p1.z + p2.z) / 2.0));

        // add vertex makes sure point is on unit sphere
        double magnitude = Math.sqrt(Math.pow(middle.x-x,2)+Math.pow(middle.y-y,2)+Math.pow(middle.z,2));
        System.out.println(magnitude);
        middle = new Point3D((int)((double)middle.x *size*3/2/(double)magnitude), (int)((double)middle.y*size*3/2/(double)magnitude), (int)((double)middle.z*size*3/2/(double)magnitude));
        //middle = new Point3D((int)((middle.x + x)*size/magnitude),(int)((middle.y+y)*size/magnitude),(int)((middle.z+70000)*size/magnitude));
        //vertices.add(middle); 
        return middle;
    }
}
