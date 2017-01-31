package gui;

import java.awt.Graphics;
import java.util.ArrayList;

abstract class Clickable {
	ArrayList<Clickable> allElements = new ArrayList<>();
	public int x = 0;
	public int y = 0;
	public int width;
	public int height;
	
	abstract void draw(Graphics g);
	
	
}
