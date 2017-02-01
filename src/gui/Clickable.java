package gui;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Clickable {
	static ArrayList<Clickable> allElements = new ArrayList<>();
	
	public int x = 0;
	public int y = 0;
	public int width;
	public int height;
	
	public Clickable(){
	}
	
	abstract void draw(Graphics g);
	abstract void onClick();
	
	public static void checkClicks(int clickX, int clickY){
		for(int i = 0; i<allElements.size(); i++){
			if(allElements.get(i).isClicked(clickX, clickY)){
				allElements.get(i).onClick();
			}
		}
	}
	public boolean isClicked(int clickX, int clickY){
		if((clickX > x && clickX < width+x) && (clickY > y && clickY < height+y)){
			return true;
		}
		return false;
	}
	
	public static void update(Graphics g){
		for(int i = 0; i<allElements.size(); i++){
			allElements.get(i).draw(g);
			System.out.println(allElements.size());
		}
	}
}
