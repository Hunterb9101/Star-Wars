package particulates.particleCommons;

import java.awt.Color;
import java.util.Arrays;

public class Effect{
	String name;
	int startTime;
	int endTime;
	
	double proportion;
	ColorRange colorRange;
	Color color;
	
	String[] genericNames = new String[]{"velocityDecay","distanceFade","distanceSize"};
	String[] colorRangeNames = new String[]{"similarFade","sparkle"};
	String[] colorNames = new String[]{"fade","explode"};
	public Effect(String name,int startTime, int life){
		this.name = name;
		this.startTime = startTime;
		this.endTime = startTime + life;
		
		if(!Arrays.asList(genericNames).contains(name)){
			throw new IllegalArgumentException();
		}
	}
	
	
	// Use this constructor for similarFade //
	public Effect(String name,int startTime, int life, ColorRange cr){
		this.name = name;
		this.startTime = startTime;
		this.endTime = startTime + life;
		
		colorRange = cr;
		
		if(!Arrays.asList(colorRangeNames).contains(name)){
			throw new IllegalArgumentException();
		}
	}
	
	// Use this constructor for fade to color //
	public Effect(String name,int startTime, int life, Color c){
		this.name = name;
		this.startTime = startTime;
		this.endTime = startTime + life;
		
		color = c;
		
		if(!Arrays.asList(colorNames).contains(name)){
			throw new IllegalArgumentException();
		}
	}
	
	public Effect(String name, int startTime, int life, double proportion){
		this(name,startTime,life);
		this.proportion = proportion;
	}
	
	public Effect(String name,int startTime, int life, ColorRange cr, double proportion){
		this(name,startTime,life,cr);
		this.proportion = proportion;
	}
	
	public Effect(String name,int startTime, int life, Color c, double proportion){
		this(name,startTime,life,c);
		this.proportion = proportion;
	}
}
