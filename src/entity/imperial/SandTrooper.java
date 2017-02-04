package entity.imperial;

import java.awt.Image;

import battleField.Main;
import battleField.Player;
import battleField.Registry;
import entity.Addon;
import entity.Animation;
import entity.Entity;

public class SandTrooper extends Entity {
	Animation idleImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperIdle.png")}, new int[]{0});
	Animation walkImg = new Animation(
			new Image[]{
					Registry.loadImage("stormtrooper/walk/StormtrooperWalk0.png"),
					Registry.loadImage("stormtrooper/walk/StormtrooperWalk1.png"),
					Registry.loadImage("stormtrooper/walk/StormtrooperWalk2.png"),
					Registry.loadImage("stormtrooper/walk/StormtrooperWalk3.png"),
					Registry.loadImage("stormtrooper/walk/StormtrooperWalk4.png"),},
			new int[]{0,0,-9,0,-7});
	
	Animation prepImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperPrep.png")}, new int[]{0});
	Animation fireImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperFire.png")}, new int[]{0});
	Animation reloadImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperPrep.png")}, new int[]{0});
	Animation compileImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/SandTrooperCompile.png")}, new int[]{0});
	public SandTrooper() {
		super("SandTrooper");
		setImgSources(idleImg,walkImg,prepImg,fireImg,reloadImg,compileImg);
		buttonImg = Registry.loadImage("button/buttonDesert.png");
		width = 35;
		height = 49;
		speed = 2;
		range = 120;
		hitPoints = 125;
		attack = 25;
		
		if(Main.planet == Main.PlanetTypes.DESERT){
			hitPoints *= 1.1;
			attack *= 1.25;
		}
		
		energyCost = 5;
	}
	
	public void spawn(int iX,int iY, Player p){
		super.spawn(iX, iY, p);
		appliedAddons = new Addon[]{Addon.findByName("Pauldron_Orange"),Addon.findByName("Backpack")};
	}
}
