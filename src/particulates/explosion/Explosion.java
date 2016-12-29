package particulates.explosion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import particulates.particleCommons.*;

public class Explosion {
	public static ArrayList<Explosion> allExplosions = new ArrayList<>();
	protected Random rand = new Random(); //for random number generation

	public Point origin; //Explosion Origin
	public int velocity = 2;
	
	public enum shapes{SQUARE,CIRCLE,FILLEDSQUARE,FILLEDCIRCLE,ASTERIK};
	public shapes particleShape = shapes.SQUARE;
	public int particleSize = 1;
	public ColorRange particleColor;
	
	public ArrayList<Particle> allParticles = new ArrayList<>();
	protected int particleNum; // Number of particles
	protected int particleMinLife;

	protected int maxExplosionLife = 0; //Current frame of Explosion
	public int lifeLength; //Maximum life of Explosion

	public boolean changesColor = false;
	
	public Explosion(int particleNum, int lifeLength, ColorRange particleColor){
		this.lifeLength = lifeLength;
		particleMinLife = (int) (.075*lifeLength);
		this.particleNum = particleNum;
		this.particleColor = particleColor;
	}

	public void initParticles(Point origin){
		this.origin = origin;
		allExplosions.add(this);
	}
	
	public static void drawAll(Graphics g){
		for(int i = 0; i<allExplosions.toArray().length; i++){
			allExplosions.get(i).draw(g);
		}
	}
	
	public void draw(Graphics g){
		for(int i = 0; i<allParticles.toArray().length;i++){
			Particle p = allParticles.get(i);
			if(p.lifeSpan < p.currLife || p.prematureDeath){allParticles.remove(i);} //Needed for cleanup
			else{
				// Move Particle //
				p.currX+=allParticles.get(i).deltX;
				p.currY+=allParticles.get(i).deltY;
				p.update();
				// Set Color //
				g.setColor(p.color);

				// Draw Shape //				
				int drawX = (int) (p.currX -(p.size/2));
				int drawY = (int) (p.currY -(p.size/2));
				
				switch(particleShape){
					case SQUARE:
						g.drawRect(drawX,drawY, (int) p.size, (int) p.size);
						break;
					case CIRCLE:
						g.drawOval(drawX,drawY, (int) p.size, (int) p.size);
						break;
					case FILLEDSQUARE:
						g.fillRect(drawX,drawY, (int) p.size, (int) p.size);
						break;
					case FILLEDCIRCLE:
						g.fillOval(drawX,drawY, (int) p.size, (int) p.size);
						break;
					case ASTERIK:
						g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(p.size*3))); 
						g.drawString("*", drawX, drawY);
						break;
					}
				}
			}
		if(allParticles.size() == 0){allExplosions.remove(this);}
	}
}

