package planets;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

abstract class Object3D {
	public ArrayList<Face2D> faces = new ArrayList<Face2D>();
	
	public void draw(Graphics g, int shaderSize){
		ArrayList<Integer> uniqueZAverages = new ArrayList<Integer>();
		for(int i = 0; i<faces.size(); i++){
			if(uniqueZAverages.indexOf(faces.get(i).getZAverage()) == -1){
				uniqueZAverages.add(faces.get(i).getZAverage());
			}
		}
		
		Collections.sort(uniqueZAverages);
		for(int z = 0; z<uniqueZAverages.size(); z++){
			for(int i = 0; i<faces.size(); i++){
				if(uniqueZAverages.get(z) == faces.get(i).getZAverage()){
					faces.get(i).draw(g, shaderSize);
				}
			}
		}
	}
	
	public int findLeastValue(int min){
		int val = 100000;
		for(int i = 0; i< faces.size(); i++){
			if(faces.get(i).getZAverage() < val && faces.get(i).getZAverage() != min){
				val = faces.get(i).getZAverage();
			}
		}
		return val;
		
	}
	
	public Boolean isLeastValue(int min){
		int val = findLeastValue(min);
		if(val == 100000){
			return false;
		}
		return true;
	}
}
