package battleField;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import entity.*;
import entity.imperial.MagmaTrooper;
import entity.imperial.SandTrooper;
import entity.imperial.Stormtrooper;
import fireworks.explosion.*;
import fireworks.particleCommons.*;

public class Registry {
	enum Alignments{LEFT,CENTER,RIGHT};
	public static Firework3D firework1;
	public static Firework3D firework2;
	
	public static Entity getNewEntity(String entityName){
		switch(entityName){
		case "Rifleman": return new Rifleman();
		case "Stormtrooper": return new Stormtrooper();
		case "MagmaTrooper": return new MagmaTrooper();
		case "SandTrooper": return new SandTrooper();
		case "Rebel": return new Rebel();
		default: System.out.println("Oh No! Entity not found:" + entityName + "!");return new Rifleman();
		}
	}
	
	public static Image loadImage(String path) {
		Image img = null;
		try {
			if(path.substring(path.indexOf(".")).equals(".gif")){
				System.out.println("Found animation: " + path);
				img = new ImageIcon(path).getImage();
			}
			else{
				img = ImageIO.read(new File(path));
			}
		} catch (IOException|NullPointerException e) {
			System.out.println("Failed to load resource at: " + path);
		} 
		return img;
	}
	
	public static void registerAddons(){
		new Addon("Pauldron_Red",loadImage("stormtrooper/addons/Pauldron_Red.png"),1,14,false);
		new Addon("Pauldron_Blue",loadImage("stormtrooper/addons/Pauldron_Blue.png"),1,14,false);
		new Addon("Pauldron_Orange",loadImage("stormtrooper/addons/Pauldron_Orange.png"),1,14,false);
		new Addon("Pauldron_Black",loadImage("stormtrooper/addons/Pauldron_Black.png"),1,14,false);
		new Addon("Pauldron_White",loadImage("stormtrooper/addons/Pauldron_White.png"),1,14,false);
		new Addon("Backpack",loadImage("stormtrooper/addons/Backpack.png"),-4,12,false);
		
		new Addon("Head1",loadImage("rebel/addons/Rebel_Head1.png"),7,4,true);
		new Addon("Head2",loadImage("rebel/addons/Rebel_Head2.png"),7,4,true);
		new Addon("HelmetRebel",loadImage("rebel/addons/Rebel_Helmet.png"),3,0,true);
	}
	public static void registerFireworks(){
		ArrayList<Effect> myEffects = new ArrayList<Effect>();
		myEffects.add(new Effect("velocityDecay",0,-1));
		myEffects.add(new Effect("distanceFade",0,-1));
		myEffects.add(new Effect("distanceSize",0,-1));
		
		myEffects.add(new Effect("fade",0,25,Main.player1.color));
		myEffects.add(new Effect("sparkle",35,20,new ColorRange(Color.GRAY,Main.player1.color)));	
		
		myEffects.add(new Effect("fade",60,25,Main.player1.color));
		myEffects.add(new Effect("explode",85,-1,Color.WHITE));
		
		firework1 = new Firework3D(180,100,ColorRange.rainbow,myEffects);
		firework1.particleSize = 5;
		firework1.velocity = 6;
		firework1.particleShape = fireworks.explosion.Explosion.shapes.FILLEDCIRCLE;
		
		
		ArrayList<Effect> myEffects2 = new ArrayList<Effect>();
		myEffects2.add(new Effect("velocityDecay",0,-1));
		myEffects2.add(new Effect("distanceFade",0,-1));
		myEffects2.add(new Effect("distanceSize",0,-1));
		
		myEffects2.add(new Effect("fade",0,25,Main.player2.color));
		myEffects2.add(new Effect("sparkle",35,20,new ColorRange(Color.GRAY,Main.player2.color)));	
		
		myEffects2.add(new Effect("fade",60,25,Main.player2.color));
		myEffects2.add(new Effect("explode",85,-1,Color.WHITE));
		
		firework2 = new Firework3D(180,100,ColorRange.rainbow,myEffects2);
		firework2.particleSize = 5;
		firework2.velocity = 6;
		firework2.particleShape = fireworks.explosion.Explosion.shapes.FILLEDCIRCLE;
	}
	
	public static void drawText(Graphics g, String value, Alignments align, int x, int y){
		int width = g.getFontMetrics().stringWidth(value);
		switch(align){
			case LEFT: break;
			case CENTER:
				x = x - width/2;
				break;
			case RIGHT:
				x = x - width;
				break;
		}
		g.drawString(value, x, y);
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
