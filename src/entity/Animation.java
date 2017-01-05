package entity;

import java.awt.Image;
import java.awt.image.BufferedImage;

import battleField.Registry;

public class Animation {
	public Image[] frames;
	public int currFrame = 0;
	
	public long startFrameTime = System.currentTimeMillis();
	public long currTime = System.currentTimeMillis();
	public long timePerFrame;
	
	public boolean isPlaying = false;
	
	public Animation(Image[] frames){
		this.frames = frames;
	}
	
	public void restart(){
		currFrame = 0;
		startFrameTime = System.currentTimeMillis();
	}
	
	public BufferedImage play(int time){
		currTime = System.currentTimeMillis();
		
		if(!isPlaying){
			restart();
			isPlaying = true;
			timePerFrame = time/frames.length;
		}
		
		if(currTime > startFrameTime + timePerFrame){
			currFrame++; // Go to next frame
			startFrameTime = System.currentTimeMillis() - (currTime - (startFrameTime+timePerFrame));
		}
		
		if(currFrame == frames.length){
			currFrame = frames.length - 1; // Don't go over the amount of pictures
			isPlaying = false; // Remember to restart on the next run
		}
		
		return Registry.toBufferedImage(frames[currFrame]);
	}
}
