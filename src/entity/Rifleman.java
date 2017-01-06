package entity;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Rifleman extends entity.Entity{
	private Image img  = new ImageIcon("redcoat/RedcoatIdle.gif").getImage();
	private Image img2 = new ImageIcon("redcoat/RedcoatWalk.gif").getImage();
	private Image img3 = new ImageIcon("redcoat/RedcoatPrep.gif").getImage();
	private Image img4 = new ImageIcon("redcoat/RedcoatFire.gif").getImage();
	private Image img5 = new ImageIcon("redcoat/RedcoatReload.gif").getImage();
	
	public Rifleman() {
		super("Rifleman");
		setImgSources(
				new Animation(new Image[]{img},new int[]{7}),
				new Animation(new Image[]{img2},new int[]{0}),
				new Animation(new Image[]{img3},new int[]{7}),
				new Animation(new Image[]{img4},new int[]{7}),
				new Animation(new Image[]{img5},new int[]{7}));
		speed = 1;
		range = 90;
		width = 49;
		height = 49;
		hitPoints = 30;
		attack = 15;
		energyCost = 5;
	}

}
