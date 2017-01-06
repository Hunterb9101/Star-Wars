package battleField;
import java.awt.Color;
import java.awt.Graphics;

import entity.Entity;

public class Player {
	public String name = "Defacto";
	public int energy = 500;
	public int currEnergy = energy;
	public int health = 50;
	public double regenEnergy = 1;
	public int currHealth = health;
	
	public Color color = Color.BLUE;
	public int team = 0;
	
	public Player(){
	}
	
	public static void checkForWin(Graphics g){
		if(Main.player1.currHealth <= 0){
			Main.roundFinished = true;
			Main.teamWon = 1;
			Registry.drawText(g,"Player 2/" + Main.player2.name + " wins!", Registry.Alignments.CENTER,Main.defaultWidth/2, Main.defaultHeight/2);
			
		}
		else if(Main.player2.currHealth <= 0){
			Main.roundFinished = true;
			Main.teamWon = 0;
			Registry.drawText(g,"Player 1/" + Main.player1.name + " wins!", Registry.Alignments.CENTER,Main.defaultWidth/2, Main.defaultHeight/2);
		}
	}
	
	public void regenEnergy(){
		if(currEnergy < energy){
			currEnergy+=regenEnergy;
		}
	}
	
	public void autoSpawn(){
		if(Main.rand.nextDouble() < .125){
			Entity.spawnCluster("Stormtrooper", Main.rand.nextInt(Main.defaultWidth - Main.rightPlayerSpawn) + Main.rightPlayerSpawn, Main.rand.nextInt(Main.defaultHeight), 45, 5, this);
		}
	}
}
