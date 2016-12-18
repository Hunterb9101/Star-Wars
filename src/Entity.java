

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/*
 * Note to me:
 * 
 * Abstract Classes are kinda like a template for other sub-classes. It can have methods that
 * 		are totally normal, but the class can never be instantized directly.
 * 
 * Interfaces are for creating a format of unimplemented functions, and forcing people
 * 		to work under that framework.
 */

abstract class Entity extends Main implements Cloneable{
	public static ArrayList<Entity> allUnits = new ArrayList<>();
	public static Random rand = new Random();
	
	public String name; // Name of unit
	public int team; // Number of team
	
	
	// Unit Statistics //
	public double hitPoints;
	public int attack;
	public int range;
	public double speed;
	
	// Point //
	public double x;
	public double y;
	public int width = 24;
	public int height = 24;
	
	public int nextX;
	
	private int attackSpeed = 800;
	public int currAttackFrame = 0;
	public int attackFrames = 30;
	private long startTime = 0;
	private long currTime = 0;
	// Unit Graphics //
	public enum entityState{IDLE,WALK,PREP,FIRE,RELOAD};
	
	public entityState state = entityState.IDLE;
	public entityState prevState = entityState.IDLE;
	
	Image idleImg; // When the unit is idle
	Image walkImg; // When the unit is moving
	Image prepImg; // When the unit is preparing to fire
	Image fireImg; // When the unit is firing
	Image reloadImg; // When unit is in between rounds
	
	public Entity(String iName, int iTeam){
		name = iName;
		team = iTeam;
		
	}
	
	public void spawn(int iX, int iY){
		x = iX;
		y = iY;
		allUnits.add(this);
	}
	public static void spawnCluster(Entity e, int x, int y, int radius, int n){
	}
	
	public void setImgSources(Image idle, Image walk, Image prep, Image fire, Image reload){
		idleImg = idle;
		walkImg = walk;
		prepImg = prep;
		fireImg = fire;
		reloadImg = reload;
	}
	
	public void moveToPoint(int dest){
		nextX = dest;
		if(x != nextX){
			x += speed;
			state = entityState.WALK;
		}
		else{
			state = entityState.IDLE;
		}
	}
	

	public static void drawAllUnits(Graphics g){
		AffineTransform tx;
		AffineTransformOp op;
		
		tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-30, 0);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		
		for(int i = 0; i<allUnits.toArray().length; i++){
			int offset = 0;
			BufferedImage image;
			
			switch(allUnits.get(i).state){
				case IDLE:
					image = toBufferedImage(allUnits.get(i).idleImg);
					break;
				case WALK:
					image = toBufferedImage(allUnits.get(i).walkImg);
					break;
				case PREP:
					image = toBufferedImage(allUnits.get(i).prepImg);
					offset = 7;
					break;
				case FIRE:
					image = toBufferedImage(allUnits.get(i).fireImg);
					offset = 7;
					break;
				case RELOAD:
					image = toBufferedImage(allUnits.get(i).reloadImg);
					offset = 7;
					break;
				default:
					image = toBufferedImage(allUnits.get(i).walkImg);
					break;
			}
			
			if(allUnits.get(i).team == 1){
				image = op.filter(image, null);
				
			}
			else{
				
			}
			g.drawImage(image,(int)allUnits.get(i).x+offset,(int)allUnits.get(i).y,allUnits.get(i).width,allUnits.get(i).height,null);
			
		}
	}
	
	public void attack(){
		currTime = System.currentTimeMillis();
		
		// Set up the Entity for firing //
		if(startTime == 0){
				startTime = System.currentTimeMillis();
				currAttackFrame = 0;
				prepImg.flush();
				fireImg.flush();
			if(prevState != entityState.FIRE && prevState != entityState.RELOAD){
				setState(entityState.PREP);
			}
			else{
				setState(entityState.RELOAD);
				//setState(entityState.RELOAD);
			}
		}
		
		// Fire //
		if(currTime >= startTime+attackSpeed && (state == entityState.FIRE || state == entityState.PREP || state == entityState.RELOAD)){
			setState(entityState.FIRE);
			if(currAttackFrame == 0){
				new Bullet(this);
			}
			if(currAttackFrame == attackFrames){
				startTime = 0;
				if(!enemyFound()){
					setState(entityState.WALK);
				}
			}
			else{
				currAttackFrame++;
			}
		}
	}
	
	public void setState(entityState newState){
		prevState = state;
		state = newState;
		
	}
	public boolean enemyFound(){
		for(int i = 0; i<allUnits.size(); i++){
			if(this.team != allUnits.get(i).team){
				if( Math.abs(allUnits.get(i).x - this.x) < this.range && Math.abs(allUnits.get(i).y - this.y) < 10){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean contains(int x, int y){
		if(x>this.x && x<this.x+this.width && y>this.y && y<this.y+this.height){
			return true;
		}
		return false;
	}
}
