package entity;

import java.awt.Image;

import battleField.Player;
import battleField.Registry;

public class Rebel extends Entity {
	Animation idleImg = new Animation(new Image[]{Registry.loadImage("rebel/RebelIdle.png")}, new int[]{0});
	Animation walkImg = new Animation(
			new Image[]{
					Registry.loadImage("rebel/walk/RebelWalk0.png"),
					Registry.loadImage("rebel/walk/RebelWalk1.png"),
					Registry.loadImage("rebel/walk/RebelWalk2.png"),
					Registry.loadImage("rebel/walk/RebelWalk3.png"),
					Registry.loadImage("rebel/walk/RebelWalk4.png"),
					Registry.loadImage("rebel/walk/RebelWalk5.png")},
			new int[]{0,0,0,0,0,0});
	
	Animation prepImg = new Animation(new Image[]{Registry.loadImage("rebel/RebelFire.png")}, new int[]{0});
	Animation fireImg = new Animation(new Image[]{Registry.loadImage("rebel/RebelFire.png")}, new int[]{0});
	Animation reloadImg = new Animation(new Image[]{Registry.loadImage("rebel/RebelFire.png")}, new int[]{0});
	Animation compileImg = new Animation(new Image[]{Registry.loadImage("rebel/RebelIdle.png")}, new int[]{0});
	
	public Rebel() {
		super("Rebel");
		setImgSources(idleImg,walkImg,prepImg,fireImg,reloadImg,compileImg);
		width = 49;
		height = 49;
		speed = 2;
		range = 120;
		hitPoints = 30;
		attack = 30;
		energyCost = 5;
	}
	
	public void spawn(int iX,int iY, Player p){
		super.spawn(iX, iY, p);
		double choice = Math.random();
		if(choice < .25){
			appliedAddons = new Addon[]{Addon.findByName("Head1"),Addon.findByName("HelmetRebel")};
		}
		else if(choice > .25 && choice < .5){
			appliedAddons = new Addon[]{Addon.findByName("Head2"),Addon.findByName("HelmetRebel")};
		}
		else if(choice > .5 && choice < .75){
			appliedAddons = new Addon[]{Addon.findByName("Head1")};
		}
		else{
			appliedAddons = new Addon[]{Addon.findByName("Head2")};
		}
	}
}
