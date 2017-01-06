package entity;

import java.awt.Image;

import battleField.Registry;

public class Stormtrooper extends Entity {
	Animation idleImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperIdle.png")});
	Animation walkImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/walk/StormtrooperWalk0.png"),
			Registry.loadImage("stormtrooper/walk/StormtrooperWalk1.png"),
			Registry.loadImage("stormtrooper/walk/StormtrooperWalk2.png"),
			Registry.loadImage("stormtrooper/walk/StormtrooperWalk3.png"),
			Registry.loadImage("stormtrooper/walk/StormtrooperWalk4.png")});
	Animation prepImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperPrep.png")});
	Animation fireImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperFire.png")});
	Animation reloadImg = new Animation(new Image[]{Registry.loadImage("stormtrooper/StormtrooperPrep.png")});
	
	public Stormtrooper() {
		super("Stormtrooper");
		setImgSources(idleImg,walkImg,prepImg,fireImg,reloadImg);
		width = 35;
		height = 49;
		speed = 2;
		range = 120;
		hitPoints = 15;
		attack = 30;
		energyCost = 1;
	}

}
