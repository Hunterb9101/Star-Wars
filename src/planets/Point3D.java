package planets;

public class Point3D {
	public Integer[] points;
	int x = 0;
	int y = 0;
	int z = 0;
	public Point3D(int x, int y, int z){
		points = new Integer[]{x,y,z};
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
