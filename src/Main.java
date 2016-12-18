
import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

//need for music and sound

public class Main extends ConstructorClass {
	
	public Random rand = new Random();
	// ********Global Variables
	int defaultWidth = 600;
	int defaultHeight = 600;
	Entity rifleman;
	Entity rifleman2;

	public void doInitialization(int width, int height) {
		this.setSize(600,600);
		
		
		for(int i = 0; i<4; i++){
			for(int i1 = 0; i1<20; i1++){
				rifleman = new Rifleman(0);
				rifleman.spawn(i*26 + 28, i1*25+28);
			}
		}

		for(int i = 0; i<4; i++){
			for(int i1 = 0; i1<20; i1++){
				rifleman = new Rifleman(1);
				rifleman.spawn(540-i*26 + 28, i1*25+28);
				rifleman.state = Entity.entityState.WALK;
			}
		}
		
		/*
		rifleman = new Rifleman(0);
		rifleman.spawn(40, 40);
		rifleman = new Rifleman(1);
		rifleman.spawn(80, 40);
		*/
	} // doInitialization

	// All drawing is done here //
	synchronized public void drawFrame(Graphics g, int width, int height) {
		this.setSize(600,600);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
	

		Entity.drawAllUnits(g);
		Bullet.updateBullets(g);
		
		for(int i = 0; i<Entity.allUnits.toArray().length; i++){
			if(Entity.allUnits.get(i).enemyFound()){
				Entity.allUnits.get(i).attack();
			}
			else{
				if(Entity.allUnits.get(i).team == 0){
					Entity.allUnits.get(i).x+=Entity.allUnits.get(i).speed;
				}
				else{
					Entity.allUnits.get(i).x-=Entity.allUnits.get(i).speed;
				}
				Entity.allUnits.get(i).setState(Entity.entityState.WALK);
			}
		}		
	
	}

	public void mousePressed(MouseEvent evt) {
		super.mousePressed(evt);
		
		// Spawn 4 Rifleman
		int radius = 45;
		for(int i = 0; i<4; i++){
			(new Rifleman(0)).spawn(((rand.nextBoolean())? 1:-1) * rand.nextInt(radius) + evt.getX(), ((rand.nextBoolean())? 1:-1) * rand.nextInt(radius) + evt.getY());
		}
	}

	public void keyPressed(KeyEvent evt) {
	}

	public void keyReleased(KeyEvent evt){
	}
	
	
	public static BufferedImage toBufferedImage(Image img){
	    if (img instanceof BufferedImage) {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	
	public static BufferedImage colorify(BufferedImage img){
		for(int y = 0; y < img.getHeight(null); y++)
		    for(int x = 0; x < img.getWidth(null); x++)
		    {
		        Color imageColor = new Color(img.getRGB(x, y));
		        //mix imageColor and desired color 
		        if(img.getRGB(x, y) != 0){
		        	if(img.getRGB(x,y) == -3932160){
		        		img.setRGB(x, y, new Color(0,0,196).getRGB());
		        	}
		        }
		    }
		return img;
	}
}



