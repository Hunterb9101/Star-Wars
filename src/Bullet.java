

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Bullet {
	static ArrayList<Bullet> allBullets = new ArrayList<Bullet>();
	public int velocity = 5;
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
			g.setColor(Color.RED);
			for(int j = 0; j<Entity.allUnits.size(); j++){
				if(Entity.allUnits.get(j).contains((int)allBullets.get(i).x,(int)allBullets.get(i).y) && Entity.allUnits.get(j).team != allBullets.get(i).parent.team){
					Entity.allUnits.get(j).hitPoints -= allBullets.get(i).parent.attack;
					if(Entity.allUnits.get(j).hitPoints < 0){
						Entity.allUnits.remove(j);
					}
					
					allBullets.get(i).dieOnNext = true;
				}
			}
			g.fillRect((int)allBullets.get(i).x, (int)allBullets.get(i).y, 8, 1);			
		}
		for(int i = 0; i<allBullets.size(); i++){
			if(allBullets.get(i).dieOnNext){
				allBullets.remove(i);
			}
		}
	}
}
