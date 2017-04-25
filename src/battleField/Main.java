package battleField;

import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import entity.Entity;
import fireworks.explosion.Explosion;
import gui.Clickable;
import gui.ConstructorClass;
import gui.UnitSpawnButton;

//need for music and sound

public class Main extends ConstructorClass {	
	public static Random rand = new Random();
	public static Player player1 = new Player();
	public static Player player2 = new Player();
	
	public static boolean roundFinished = false;
	public static int teamWon = -1;
	
	public static int defaultWidth = 800;
	public static int defaultHeight = 800;
	
	public static int leftPlayerSpawn = 150;
	public static int rightPlayerSpawn = 650;
	
	public static int bottomBorder = 725;
	public static int topBorder = 110;
	
	public static enum PlanetTypes{TROPICAL,MAGMA,DESERT,COLD,OTHER};
	public static PlanetTypes planet = PlanetTypes.TROPICAL;
	
	public static Image bg; // 800x615
	public static Image splash; // Dimensions 800x115
	
	public void doInitialization(int width, int height) {
		this.setSize(defaultWidth,defaultHeight);
		player2.team = 1;
		
		player1.color = Color.BLUE;
		player2.color = Color.RED;
		
		player1.allowedUnits.add(Registry.getNewEntity("Stormtrooper"));
		player1.allowedUnits.add(Registry.getNewEntity("MagmaTrooper"));
		player1.allowedUnits.add(Registry.getNewEntity("SandTrooper"));
		player1.allowedUnits.add(Registry.getNewEntity("Shoretrooper"));
		player1.allowedUnits.add(Registry.getNewEntity("StormtrooperCaptain"));
		player1.allowedUnits.add(Registry.getNewEntity("Shocktrooper"));
		
		Registry.registerFireworks();
		Registry.registerAddons();
		
		switch(planet){
		case MAGMA: 
			bg = Registry.loadImage("background/MagmaPlanetaryBg.png");
			splash = Registry.loadImage("background/MagmaPlanetarySplash.png"); break;
		case OTHER: 
			bg = Registry.loadImage("background/OtherPlanetaryBg.png");
			splash = Registry.loadImage("background/OtherPlanetarySplash.png");break;
		default: 
			bg = Registry.loadImage("background/OtherPlanetaryBg.png"); 
			splash = Registry.loadImage("background/OtherPlanetarySplash.png");break;
		}
		for(int i = 0; i<player1.allowedUnits.size(); i++){
			new UnitSpawnButton(defaultWidth/10*i + defaultWidth/20,737, 50, 50,player1.allowedUnits.get(i).compileImg,player1.allowedUnits.get(i).buttonImg);
		}
	} // doInitialization

	// All drawing is done here //
	synchronized public void drawFrame(Graphics g, int width, int height) {
		this.setSize(defaultWidth,defaultHeight);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, defaultWidth, defaultHeight);
		
		
		
		g.drawImage(splash, 0, 0, null);
		g.drawImage(bg, 0, topBorder, null);
		
		g.drawImage(colorImage(Registry.toBufferedImage(Registry.loadImage("Border.png")),player1.color),0,topBorder,null);
		g.drawImage(colorImage(Registry.toBufferedImage(Registry.loadImage("Border.png")),player2.color),rightPlayerSpawn,topBorder,null);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, bottomBorder, defaultWidth, defaultHeight-bottomBorder);
		
		if(!roundFinished){
			Entity.updateUnits(g);
			Bullet.updateBullets(g);
		}	
		else{
			Entity.allUnits.clear();
			Bullet.allBullets.clear();
			
			Explosion.drawAll(g);
			
			if(rand.nextDouble() < .05 && teamWon == 0){
				Registry.firework1.initParticles(new Point(rand.nextInt(defaultWidth),rand.nextInt(defaultHeight)));
				Registry.registerFireworks();
			}
			else if(rand.nextDouble() < .05 && teamWon == 1){
				Registry.firework2.initParticles(new Point(rand.nextInt(defaultWidth),rand.nextInt(defaultHeight)));
				Registry.registerFireworks();
			}
		}
		
		g.setColor(Color.BLACK);
		Player.checkForWin(g);
		
		g.setColor(player1.color);
		Registry.drawText(g, player1.name, Registry.Alignments.LEFT, 5,25);
		Registry.drawText(g, "Energy: " +String.valueOf(player1.currEnergy) + "/" + String.valueOf(player1.energy), Registry.Alignments.LEFT,5, 50);
		Registry.drawText(g, "Health: " + String.valueOf(player1.currHealth) + "/" + String.valueOf(player1.health), Registry.Alignments.LEFT,5, 75);
		
		g.setColor(player2.color);
		Registry.drawText(g, player2.name, Registry.Alignments.RIGHT, defaultWidth - 5,25);
		Registry.drawText(g, "Energy: " + String.valueOf(player2.currEnergy) + "/" + String.valueOf(player2.energy), Registry.Alignments.RIGHT,defaultWidth - 5, 50);
		Registry.drawText(g, "Health: " + String.valueOf(player2.currHealth) + "/" + String.valueOf(player2.health), Registry.Alignments.RIGHT,defaultWidth - 5, 75);	
	
		Clickable.update(g);
		
		if(!roundFinished){
			player1.regenEnergy();
			player2.regenEnergy();
			
			player2.autoSpawn();
		}
	}

	public void mousePressed(MouseEvent evt) {
		super.mousePressed(evt);
		if(!roundFinished){
			Clickable.checkClicks(evt.getX(),evt.getY());
			if(evt.getY() > topBorder && evt.getY() < bottomBorder && (evt.getX() < leftPlayerSpawn)){
				Entity.spawnCluster(player1.allowedUnits.get(UnitSpawnButton.idClicked).name, evt.getX(), evt.getY(), 75, 5, player1);
			}
		}
		else{
			// Go to main window //
		}
	}

	public void keyPressed(KeyEvent evt) {	
	}

	public void keyReleased(KeyEvent evt){
	}
	
	private static BufferedImage colorImage(BufferedImage image, Color color) {
	    int width = image.getWidth();
	    int height = image.getHeight();
	    WritableRaster raster = image.getRaster();

	    for (int xx = 0; xx < width; xx++) {
	      for (int yy = 0; yy < height; yy++) {
	        int[] pixels = raster.getPixel(xx, yy, (int[]) null);
	        pixels[0] = color.getRed();
	        pixels[1] = color.getGreen();
	        pixels[2] = color.getBlue();
	        raster.setPixel(xx, yy, pixels);
	      }
	    }
	    return image;
	  }
}



