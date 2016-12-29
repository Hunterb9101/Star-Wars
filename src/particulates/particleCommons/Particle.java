package particulates.particleCommons;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import particulates.explosion.Explosion;
import particulates.explosion.Firework3D;

public class Particle {
	Random rand = new Random();
	Explosion parent;
	public enum Shapes3D{SPHERE};
	
	public double currX;
	public double currY;
	public double currZ;
	
	public double deltX;
	public double deltY; 
	public double deltZ;
	
	public double deltAlpha;
	
	public static double indeterminateLifeSpan = .25;
	public int lifeSpan;
	public int currLife = 0;
	public boolean prematureDeath = false;
	
	public double alpha;
	private static int alphaLowRange = 64;
	private static int alphaHighRange = 255;
	
	public Color color;
	public Color startColor;
	private Color fadeTo;
	
	public double size = 6;
	public double velocityDecayMultiplier = .97; // .975 works well with 40 ticks
	
	public ArrayList<Effect> effects = new ArrayList<Effect>();
	
	public Particle(Explosion parent, ArrayList<Effect> effects){
		this.parent = parent;
		currX = parent.origin.getX();
		currY = parent.origin.getY();
		currZ = 0;
		color = parent.particleColor.generateColor();
		startColor = color;
		lifeSpan = rand.nextInt((int)(parent.lifeLength*indeterminateLifeSpan)) + parent.lifeLength;
		
		deltAlpha = 2*((deltZ > 0)? 1:-1); //Overall Change
		generateSphereDeltas();
		this.effects = effects;
	}
	
	public Particle(Point p, double size, double dX, double dY, double dZ, int life, Color iColor, ArrayList<Effect> effects){
		currX = p.getX();
		currY = p.getY();
		currZ = 0;
		deltX = dX;
		deltY = dY;
		deltZ = dZ;
		lifeSpan = life;
		color = iColor;
		startColor = iColor;
		
		
		this.effects = effects;
		deltAlpha = 2*((dZ > 0)? 1:-1); //Overall Change
		alpha = iColor.getAlpha();
		this.size = size;
	}
	
	public void generateSphereDeltas(){
			deltX = rand.nextDouble()*(2*parent.velocity) - (parent.velocity);
			deltY = rand.nextDouble()*(2*parent.velocity) - (parent.velocity);
			deltZ = rand.nextDouble()*(2*parent.velocity) - (parent.velocity);

			// Ensures no particles will be created on edges of sphere
			while(Math.pow(deltX, 2) + Math.pow(deltY,2) > (int) Math.pow(parent.velocity, 2)){
				deltX = rand.nextDouble()*(2*parent.velocity) - (parent.velocity);
				deltY = rand.nextDouble()*(2*parent.velocity) - (parent.velocity);
			}
			
			while(Math.pow(deltX, 2) + Math.pow(deltZ, 2) > (int) Math.pow(parent.velocity, 2) || Math.pow(deltY, 2) + Math.pow(deltZ, 2) > (int) Math.pow(parent.velocity, 2)){
				deltZ = rand.nextDouble()*(2*parent.velocity) - (parent.velocity);
			}
	}
	
	public void update(){
		for(int i = 0; i<effects.size(); i++){
			if(effects.get(i).startTime < currLife && (currLife < effects.get(i).endTime || effects.get(i).startTime > effects.get(i).endTime)){
				switch(effects.get(i).name){
				case "velocityDecay": applyVelocityDecay();break;
				case "distanceFade": applyDistanceFade();break;
				case "distanceSize": applyDistanceEffect();break;
				
				case "similarFade": 
					if(effects.get(i).startTime > effects.get(i).endTime){
						applySimilarFade(lifeSpan - effects.get(i).startTime,effects.get(i).colorRange);
					}
					else{
						applySimilarFade(effects.get(i).endTime - effects.get(i).startTime,effects.get(i).colorRange);
					}
					break;
				case "sparkle":
					applySparkle(effects.get(i).colorRange);
					break;
				case "fade": 
					if(effects.get(i).startTime > effects.get(i).endTime){
						applyFadeToColor(lifeSpan - effects.get(i).startTime,effects.get(i).color);
					}
					else{
						applyFadeToColor(effects.get(i).endTime - effects.get(i).startTime,effects.get(i).color);
					}
					break;
				
				case "explode":
					applyExplodeParticle(effects.get(i).color);
					break;
					
				default: System.out.println("OH NOES! A FEATURE WASN'T IMPLEMENTED: " + effects.get(i).name); break;
				}
			}
			
			if(effects.get(i).endTime == currLife){
				startColor = color;
			}
		}
		currLife++;
	}
	
	
////////////////////////////////////////////
//				EFFECTS					  //
////////////////////////////////////////////
	
	public void applyVelocityDecay(){
		deltX*=velocityDecayMultiplier;
		deltY*=velocityDecayMultiplier;
		deltZ*=velocityDecayMultiplier;
	}
	
	public void applyDistanceFade(){
		alpha+=deltAlpha;
		if(alpha > alphaHighRange){
			alpha = alphaHighRange;
		} else if(alpha < alphaLowRange){
			alpha = alphaLowRange;
		}
		color = new Color(color.getRed(),color.getGreen(),color.getBlue());
	}
	
	public void applyDistanceEffect(){
		size += deltZ*.03;
	}
	
	public void applySimilarFade(int life,ColorRange colorList){
		if(fadeTo == null){
			fadeTo = ColorRange.findClosestColor(color, colorList);
		}
		else{
			applyFadeToColor(life,fadeTo);
		}
	}
	
	public void applySparkle(ColorRange colorList){
		color = colorList.generateColor();
	}
	public void applyFadeToColor(int life,Color fadeColor){
		int r = (int) (((fadeColor.getRed()-startColor.getRed())/life) + color.getRed());
		int g = (int) (((fadeColor.getGreen()-startColor.getGreen())/life) + color.getGreen());
		int b = (int) (((fadeColor.getBlue()-startColor.getBlue())/life) + color.getBlue());
		
		if(r>255){r=255;}
		if(g>255){g=255;}
		if(b>255){b=255;}
		
		if(r<0){r=0;}
		if(g<0){g=0;}
		if(b<0){b=0;}
		color = new Color(r,g,b);
	}
	
	public void applyExplodeParticle(Color explodeColor){
		prematureDeath = true;
		
		ArrayList<Effect> explosionEffects = new ArrayList<Effect>();
		explosionEffects.add(new Effect("velocityDecay",0,-1));
		explosionEffects.add(new Effect("fade",15,-1,Color.BLACK));
		
		parent.allParticles.add(new Particle(new Point((int)currX,(int)currY),3 ,0,1,0,30,explodeColor, explosionEffects));
		parent.allParticles.add(new Particle(new Point((int)currX,(int)currY),3 ,1,0,0,30,explodeColor, explosionEffects));
		parent.allParticles.add(new Particle(new Point((int)currX,(int)currY),3 ,-1,0,0,30,explodeColor, explosionEffects));
		parent.allParticles.add(new Particle(new Point((int)currX,(int)currY),3 ,0,-1,0,30,explodeColor, explosionEffects));
	}
}
