package entity.imperial;

import java.awt.Image;

import battleField.Main;
import battleField.Player;
import battleField.Registry;
import entity.Addon;
import entity.Animation;
import entity.Entity;

public class StormtrooperCaptain extends Entity {
	
	// 
	// Art on all maps except tropical
	//
	
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
	Animation compileImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperCompile.png")}, new int[]{0});
	
	// 
	// Art on tropical maps
	//
	
	Animation idleImg2 = new Animation(new Image[]{Registry.loadImage("shorecaptain/ShoreCaptainIdle.png")}, new int[]{0});
	Animation walkImg2 = new Animation(
			new Image[]{
					Registry.loadImage("shorecaptain/walk/ShoreCaptainWalk0.png"),
					Registry.loadImage("shorecaptain/walk/ShoreCaptainWalk1.png"),
					Registry.loadImage("shorecaptain/walk/ShoreCaptainWalk2.png"),
					Registry.loadImage("shorecaptain/walk/ShoreCaptainWalk3.png"),
					Registry.loadImage("shorecaptain/walk/ShoreCaptainWalk4.png"),
					Registry.loadImage("shorecaptain/walk/ShoreCaptainWalk5.png"),},
			new int[]{0,0,-8,0,-8,-8});
	
	Animation prepImg2 = new Animation(new Image[]{Registry.loadImage("shorecaptain/ShoreCaptainPrep.png")}, new int[]{0});
	Animation fireImg2 = new Animation(new Image[]{Registry.loadImage("shorecaptain/ShoreCaptainFire.png")}, new int[]{0});
	Animation reloadImg2 = new Animation(new Image[]{Registry.loadImage("shorecaptain/ShoreCaptainPrep.png")}, new int[]{0});
	Animation compileImg2 = new Animation(new Image[]{Registry.loadImage("shorecaptain/ShoreCaptainCompile.png")}, new int[]{0});
	
	public StormtrooperCaptain() {
		super("StormtrooperCaptain");
		
		if(Main.planet == Main.PlanetTypes.TROPICAL){
			setImgSources(idleImg2,walkImg2,prepImg2,fireImg2,reloadImg2,compileImg2);
			buttonImg = Registry.loadImage("button/buttonTropical.png");
		}
		else{
			setImgSources(idleImg,walkImg,prepImg,fireImg,reloadImg,compileImg);
			buttonImg = Registry.loadImage("button/buttonOther.png");
		}
		
		width = 35;
		height = 49;
		speed = 2;
		range = 120;
		hitPoints = 100;
		attack = 25;
		energyCost = 5;
	}
	
	public void spawn(int iX,int iY, Player p){
		super.spawn(iX, iY, p);
		
		if(Main.planet != Main.PlanetTypes.TROPICAL){
			appliedAddons = new Addon[]{Addon.findByName("Pauldron_Red")};
		}

	}
}
