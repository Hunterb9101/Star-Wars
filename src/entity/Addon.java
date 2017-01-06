package entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import battleField.Registry;

// Adds images on a unit's art that wouldn't normally be there
public class Addon {
	public static ArrayList<Addon> addons = new ArrayList<>();
	public String name = "Stuff";
	
	public BufferedImage src;
	public int x;
	public int y;
	
	public final static int teamSwitch = -15; //Pixel offset for right side units
	
	public Addon(String name, Image src, int x, int y){
		this.name = name;
		this.src = Registry.toBufferedImage(src);
		this.x = x;
		this.y = y;
		addons.add(this);
	}
	
	public static Addon findByName(String name){
		for(int i = 0; i<addons.size();i++){
			if(name.equals(addons.get(i).name)){
				return addons.get(i);
			}
		}
		return null;
	}
}
