package gui;

import java.awt.Color;
import java.awt.Graphics;

import entity.Animation;

public class UnitSpawnButton extends Clickable{
	
	Animation src;
	static int buttonCount = 0;

	int id = 0;
	static int idClicked = -1; 
	
	public UnitSpawnButton(int x, int y, int width, int height, Animation src){
		super();
		this.src = src;
		id = buttonCount;
		buttonCount++;
		allElements.add(this);
	}
	
	public void draw(Graphics g){
		if(id == idClicked){
			System.out.println("In loop");
			g.setColor(Color.BLUE);
			g.fillRect(x,y,width,height);
			g.drawImage(src.play(100),x,y,width,height, null);
		}
		else{
			g.setColor(Color.GRAY);
			g.fillRect(x,y,width,height);
			g.drawImage(src.play(100),x,y,width,height, null);
			System.out.println("Image");
		}
	}
	
	public void onClick(){
		idClicked = id;
	}
}
