package battleField;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import bitBattles.Entity;


public class Bullet {
	static ArrayList<Bullet> allBullets = new ArrayList<Bullet>();
	public int velocity = 5;
	public Color bulletColor = Color.BLACK;
	
	public Entity parent;
	double x;
	double y;
	
	boolean dieOnNext = false;
	
	public Bullet(Entity parent){
		if(parent.team == 1){
			velocity *= -1;
		}
		this.x = parent.x + 3;
		this.y = parent.y + 8;
		this.parent = parent;
		allBullets.add(this);
	}
	
	public static void updateBullets(Graphics g){
		for(int i = 0; i<allBullets.size(); i++){
			allBullets.get(i).x += allBullets.get(i).velocity;
			
			try{
				for(int j = 0; j<Entity.allUnits.size(); j++){
					if(Entity.allUnits.get(j).isTouching((int)allBullets.get(i).x,(int)allBullets.get(i).y) && Entity.allUnits.get(j).team != allBullets.get(i).parent.team){
						Entity.allUnits.get(j).onHit(allBullets.get(i),j);
						allBullets.get(i).onHit(i);
						if(i == 0){
							
						}
						else{
							i--;
						}
					}
					else{
						g.setColor(allBullets.get(i).bulletColor);
						g.fillRect((int)allBullets.get(i).x, (int)allBullets.get(i).y, 8, 1);
					}
				}
			}catch(IndexOutOfBoundsException e){}			
		}
	}
	
	public void onHit(int idx){
		allBullets.remove(idx);
	}
}
