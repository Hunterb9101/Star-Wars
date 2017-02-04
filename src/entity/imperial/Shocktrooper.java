package entity.imperial;

import java.awt.Image;

import battleField.Player;
import battleField.Registry;
import entity.Addon;
import entity.Animation;
import entity.Entity;

public class Shocktrooper extends Entity {
	Animation idleImg = new Animation(new Image[]{Registry.loadImage("shocktrooper/ShocktrooperIdle.png")}, new int[]{0});
	Animation walkImg = new Animation(
			new Image[]{
					Registry.loadImage("shocktrooper/walk/ShocktrooperWalk0.png"),
					Registry.loadImage("shocktrooper/walk/ShocktrooperWalk1.png"),
					Registry.loadImage("shocktrooper/walk/ShocktrooperWalk2.png"),
					Registry.loadImage("shocktrooper/walk/ShocktrooperWalk3.png"),
					Registry.loadImage("shocktrooper/walk/ShocktrooperWalk4.png"),
					Registry.loadImage("shocktrooper/walk/ShocktrooperWalk5.png"),},
			new int[]{0,-8,-8,0,-8,-8});
	
	Animation prepImg = new Animation(new Image[]{Registry.loadImage("shocktrooper/ShocktrooperPrep.png")}, new int[]{0});
	Animation fireImg = new Animation(new Image[]{Registry.loadImage("shocktrooper/ShocktrooperFire.png")}, new int[]{0});
	Animation reloadImg = new Animation(new Image[]{Registry.loadImage("shocktrooper/ShocktrooperPrep.png")}, new int[]{0});
	Animation compileImg = new Animation(new Image[]{Registry.loadImage("shocktrooper/ShocktrooperCompile.png")}, new int[]{0});
	public Shocktrooper() {
		super("Shocktrooper");
		setImgSources(idleImg,walkImg,prepImg,fireImg,reloadImg,compileImg);
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
	}
}
