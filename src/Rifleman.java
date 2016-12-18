

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Rifleman extends Entity{
	public ArrayList<Rifleman> allEntities = new ArrayList<>();
	private Image img  = new ImageIcon("redcoat/RedcoatIdle.gif").getImage();
	private Image img2 = new ImageIcon("redcoat/RedcoatWalk.gif").getImage();
	private Image img3 = new ImageIcon("redcoat/RedcoatPrep.gif").getImage();
	private Image img4 = new ImageIcon("redcoat/RedcoatFire.gif").getImage();
	private Image img5 = new ImageIcon("redcoat/RedcoatReload.gif").getImage();
	
	public Rifleman(int iTeam) {
		super("Rifleman", iTeam);
		setImgSources(img,img2,img3,img4,img5);
		speed = 1;
		range = 30;
		hitPoints = 30;
		attack = 15;
		allEntities.add(this);
	}

}
