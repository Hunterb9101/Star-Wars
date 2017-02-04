package entity.imperial;

import java.awt.Image;

import battleField.Main;
import battleField.Player;
import battleField.Registry;
import entity.Addon;
import entity.Animation;
import entity.Entity;

public class Shoretrooper extends Entity {
	Animation idleImg = new Animation(new Image[]{Registry.loadImage("shoretrooper/ShoretrooperIdle.png")}, new int[]{0});
	Animation walkImg = new Animation(
			new Image[]{
					Registry.loadImage("shoretrooper/walk/ShoretrooperWalk0.png"),
					Registry.loadImage("shoretrooper/walk/ShoretrooperWalk1.png"),
					Registry.loadImage("shoretrooper/walk/ShoretrooperWalk2.png"),
					Registry.loadImage("shoretrooper/walk/ShoretrooperWalk3.png"),
					Registry.loadImage("shoretrooper/walk/ShoretrooperWalk4.png"),
					Registry.loadImage("shoretrooper/walk/ShoretrooperWalk5.png")},
			new int[]{0,0,-8,0,-8,-8});
	
	Animation prepImg = new Animation(new Image[]{Registry.loadImage("shoretrooper/ShoretrooperPrep.png")}, new int[]{0});
	Animation fireImg = new Animation(new Image[]{Registry.loadImage("shoretrooper/ShoretrooperFire.png")}, new int[]{0});
	Animation reloadImg = new Animation(new Image[]{Registry.loadImage("shoretrooper/ShoretrooperPrep.png")}, new int[]{0});
	Animation compileImg = new Animation(new Image[]{Registry.loadImage("shoretrooper/ShoretrooperCompile.png")}, new int[]{0});
	
	public Shoretrooper() {
		super("Shoretrooper");
		buttonImg = Registry.loadImage("button/buttonTropical.png");
		setImgSources(idleImg,walkImg,prepImg,fireImg,reloadImg,compileImg);
		width = 35;
		height = 49;
		speed = 2;
		range = 120;
		hitPoints = 125;
		attack = 25;
		if(Main.planet == Main.PlanetTypes.TROPICAL){
			hitPoints *= 1.1;
			attack *= 1.25;
		}
		
		energyCost = 5;
	}
	
	public void spawn(int iX,int iY, Player p){
		super.spawn(iX, iY, p);
		appliedAddons = new Addon[]{};
	}
}
