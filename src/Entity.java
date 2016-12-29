

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

abstract class Entity extends Main implements Cloneable{
	public static ArrayList<Entity> allUnits = new ArrayList<>();
	public static Random rand = new Random();
	
	public String name; // Name of unit //
	public int team; // Number of team //
	
	// Unit Statistics //
	public double hitPoints;
	public int attack;
	public int range;
	public double speed;
	public int energyCost;
	
	// Point //
	public double x;
	public double y;
	
	// Move To Point //
	public int nextX;
	
	public int width = 24;
	public int height = 24;
	
	
	private int reloadTime = 500; // Time that is used in Reload State
	private int attackTime = 300; // Time spent in Fire State
	private int prepTime = 800;   // Time in Prep State
	
	private long startStateTime = 0; // Total time spent in state
	private static long currTime = 0; // Current Time
	private boolean hasFired = false;
	
	// Unit Graphics //
	public enum entityState{IDLE,WALK,PREP,FIRE,RELOAD};
	
	public entityState state = entityState.WALK;
	public entityState prevState = entityState.IDLE;
	
	Image idleImg; // When the unit is idle
	Image walkImg; // When the unit is moving
	Image prepImg; // When the unit is preparing to fire
	Image fireImg; // When the unit is firing
	Image reloadImg; // When unit is in between rounds
	
	public Entity(String iName){
		name = iName;		
	}
	
	public void setImgSources(Image idle, Image walk, Image prep, Image fire, Image reload){
		idleImg = idle;
		walkImg = walk;
		prepImg = prep;
		fireImg = fire;
		reloadImg = reload;
	}
	
	public void spawn(int iX, int iY, Player p){
		x = iX;
		y = iY;
		this.team = p.team;
		allUnits.add(this);
	}
	
	public static boolean spawnCluster(String entityName, int x, int y, int radius, int n, Player p){
		int checkX;
		int checkY;
		int spawned = 0;
		
		if(p.currEnergy < Registry.getNewEntity(entityName).energyCost*n){
			return false; // don't spawn units if not enough energy //
		}
		
		for(int i = 0; i<n; i++){
			checkX = ((rand.nextBoolean())? 1:-1) * rand.nextInt(radius) + x;
			checkY = ((rand.nextBoolean())? 1:-1) * rand.nextInt(radius) + y;
			boolean passed = false;
			
			int passCheck = 25;
			int currPass = 0;
			
			while(!passed && currPass < passCheck ){
				if(checkX > 0 && checkX < Main.leftPlayerSpawn - radius && p.team == 0){
					if(checkY > topBorder && checkY < bottomBorder - Registry.getNewEntity(entityName).height){
						passed = true;
					}
					else{
						checkY = ((rand.nextBoolean())? 1:-1) * rand.nextInt(radius) + y;
					}
				}
				else{
					checkX = ((rand.nextBoolean())? 1:-1) * rand.nextInt(radius) + x;
				}
				
				if(checkX < defaultWidth && checkX > Main.rightPlayerSpawn && p.team == 1){
					if(checkY > topBorder && checkY < bottomBorder - Registry.getNewEntity(entityName).height){
						passed = true;
					}
					else{
						checkY = ((rand.nextBoolean())? 1:-1) * rand.nextInt(radius) + y;
					}
				}
				else{
					checkX = ((rand.nextBoolean())? 1:-1) * rand.nextInt(radius) + x;
				}
				currPass++;
			}
			
			if(passed){
				Registry.getNewEntity("entityName").spawn(checkX, checkY, p);
				spawned++;
			}
		}
		
		if(spawned == n){
			p.currEnergy -= Registry.getNewEntity(entityName).energyCost*n;
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void updateUnits(Graphics g){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-30, 0);		
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			
		int offset = 0;
		BufferedImage image;
		
		currTime = System.currentTimeMillis();
		
		for(int i = 0; i<allUnits.toArray().length; i++){
			offset = 0;
			switch(allUnits.get(i).state){
				case IDLE:
					image = Registry.toBufferedImage(allUnits.get(i).idleImg);
					offset = 7;
					if(!allUnits.get(i).allyFound(i)){
						allUnits.get(i).setState(entityState.WALK);
					}
					break;
					
				case WALK:
					image = Registry.toBufferedImage(allUnits.get(i).walkImg);
					
					if(allUnits.get(i).enemyFound()){
						allUnits.get(i).prepImg.flush();
						allUnits.get(i).setState(entityState.PREP);
					}
					else if(allUnits.get(i).allyFound(i)){
						allUnits.get(i).setState(entityState.IDLE);
					}
					else if(allUnits.get(i).x > Main.defaultWidth && allUnits.get(i).team == 0){
						allUnits.remove(i);
						Main.player2.currHealth -= 1;
					}
					else if(allUnits.get(i).x < 0 && allUnits.get(i).team == 1){
						allUnits.remove(i);
						Main.player1.currHealth -= 1;
					}
					else{
						allUnits.get(i).x+= ((Entity.allUnits.get(i).team == 0)? 1:-1) * Entity.allUnits.get(i).speed;
					}
					break;
					
				case PREP:
					image = Registry.toBufferedImage(allUnits.get(i).prepImg);
					offset = 7;
					
					if(currTime >= allUnits.get(i).startStateTime + allUnits.get(i).prepTime){
						allUnits.get(i).fireImg.flush();
						allUnits.get(i).setState(entityState.FIRE);
					}
					break;
					
				case FIRE:
					image = Registry.toBufferedImage(allUnits.get(i).fireImg);
					offset = 7;
					
					if(!allUnits.get(i).hasFired){
						allUnits.get(i).hasFired = true;
						new Bullet(allUnits.get(i));
					}
					
					if(currTime > allUnits.get(i).attackTime + allUnits.get(i).startStateTime){
						if(!allUnits.get(i).enemyFound()){
							allUnits.get(i).setState(entityState.WALK);
						}
						else{
							allUnits.get(i).setState(entityState.RELOAD);
						}
					}
					break;
					
				case RELOAD:
					image = Registry.toBufferedImage(allUnits.get(i).reloadImg);
					offset = 7;
					
					if(currTime > allUnits.get(i).startStateTime + allUnits.get(i).reloadTime){
						allUnits.get(i).hasFired = false;
						allUnits.get(i).fireImg.flush();
						allUnits.get(i).setState(entityState.FIRE);
					}
					break;
					
				default:		
					image = null;
					break;
			}
			
			if(allUnits.get(i).team == 1){
				image = op.filter(image, null);
				offset*=-1;			
			}
			g.drawImage(image,(int)allUnits.get(i).x+offset,(int)allUnits.get(i).y,allUnits.get(i).width,allUnits.get(i).height,null);
			
		}
	}
	
	public void setState(entityState newState){
		prevState = state;
		state = newState;
		startStateTime = System.currentTimeMillis();
	}
	
	public boolean enemyFound(){
		for(int i = 0; i<allUnits.size(); i++){
			if(this.team != allUnits.get(i).team){
				if(allUnits.get(i).x - this.x > -this.range && allUnits.get(i).x - this.x < 0 && Math.abs(allUnits.get(i).y - this.y)<10  && team == 1){
					return true;
				}
				else if(allUnits.get(i).x - this.x < this.range && allUnits.get(i).x - this.x > 0 && Math.abs(allUnits.get(i).y - this.y)<10 && team == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean allyFound(int idx){
		for(int i = 0; i<allUnits.size(); i++){
			if(this.team == allUnits.get(i).team){
				if(allUnits.get(i).x - this.x > -8 && allUnits.get(i).x - this.x < 0 && Math.abs(allUnits.get(i).y - this.y)<5 && idx != i && team == 1){
					return true;
				}
				else if(allUnits.get(i).x - this.x < 8 && allUnits.get(i).x - this.x > 0 && Math.abs(allUnits.get(i).y - this.y)<5 && idx != i && team == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean allyFoundBehind(int idx){
		for(int i = 0; i<allUnits.size(); i++){
			if(this.team == allUnits.get(i).team){
				if(allUnits.get(i).x - this.x > -8 && allUnits.get(i).x - this.x < 0 && Math.abs(allUnits.get(i).y - this.y)<5 && idx != i && team == 0){
					return true;
				}
				else if(allUnits.get(i).x - this.x < 8 && allUnits.get(i).x - this.x > 0 && Math.abs(allUnits.get(i).y - this.y)<5 && idx != i && team == 1){
					return true;
				}
			}
		}
		return false;
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
	
	public boolean isTouching(int x, int y){
		if(x>this.x && x<this.x+this.width && y>this.y && y<this.y+this.height){
			return true;
		}
		return false;
	}
	
	public void onHit(Bullet bullet, int idx){
		hitPoints -= bullet.parent.attack;
		
		if(Entity.allUnits.get(idx).hitPoints <= 0){
			Entity.allUnits.get(idx).onDeath(idx);
		}
	}
	
	public void onDeath(int index){
		allUnits.remove(index);
	}
}
