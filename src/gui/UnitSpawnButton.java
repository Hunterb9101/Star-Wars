package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import entity.Animation;

public class UnitSpawnButton extends Clickable{
	
	Animation src;
	Image bg;
	static int buttonCount = 0;

	int id = 0;
	public static int idClicked = 0; 
	
	public UnitSpawnButton(int x, int y, int width, int height, Animation src, Image bg){
		this.src = src;
		this.bg = bg;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		id = buttonCount;
		buttonCount++;
		allElements.add(this);
	}
	
	public void draw(Graphics g){
		if(id == idClicked){
			g.setColor(Color.BLUE);
			g.fillRect(x - 5,y - 5,width + 10,height + 10);
			g.drawImage(bg, x - 5, y - 5, width + 10, height + 10,null);
			g.drawImage(src.play(100),x,y,width,height, null);
		}
		else{
			g.setColor(Color.GRAY);
			g.fillRect(x - 5,y - 5,width + 10,height + 10);
			g.drawImage(bg, x - 5, y - 5, width + 10, height + 10,null);
			g.drawImage(src.play(100),x,y,width,height, null);
	
		}
	}
	
	public void onClick(){
		idClicked = id;
	}
}
