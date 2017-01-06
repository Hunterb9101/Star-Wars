package battleField;

import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import entity.Entity;
import fireworks.explosion.Explosion;

//need for music and sound

public class Main extends ConstructorClass {	
	public static Random rand = new Random();
	public static Player player1 = new Player();
	public static Player player2 = new Player();
	
	public static boolean roundFinished = false;
	public static int teamWon = -1;
	
	public static boolean disableControls = false;
	public static int defaultWidth = 800;
	public static int defaultHeight = 800;
	
	public static int leftPlayerSpawn = 150;
	public static int rightPlayerSpawn = 650;
	
	public static int bottomBorder = 800;
	public static int topBorder = 150;

	public void doInitialization(int width, int height) {
		this.setSize(defaultWidth,defaultHeight);
		player2.team = 1;
		player2.color = Color.RED;
		player1.color = Color.BLUE;
		Registry.registerFireworks();
		Registry.registerAddons();
	} // doInitialization

	// All drawing is done here //
	synchronized public void drawFrame(Graphics g, int width, int height) {
		this.setSize(defaultWidth,defaultHeight);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, defaultWidth, defaultHeight);
		
		g.setColor(player1.color);
		g.fillRect(0, 0, leftPlayerSpawn, defaultHeight);
		
		g.setColor(player2.color);
		g.fillRect(rightPlayerSpawn, 0, defaultHeight-rightPlayerSpawn, defaultHeight);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, defaultWidth, topBorder);
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
	
		
		if(!roundFinished){
			player1.regenEnergy();
			player2.regenEnergy();
			
			player2.autoSpawn();
		}
	}

	public void mousePressed(MouseEvent evt) {
		super.mousePressed(evt);
		
		if(!roundFinished){
			Entity.spawnCluster("Stormtrooper", evt.getX(), evt.getY(), 125, 5, player1);
		}
		else{
			// Go to main window //
		}
	}

	public void keyPressed(KeyEvent evt) {
	}

	public void keyReleased(KeyEvent evt){
	}
}



