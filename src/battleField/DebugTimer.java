package battleField;

public class DebugTimer {
	String name;
	long startTime;
	
	int lap = 0;
	int totalTime;
	
	int longestLap;
	int shortestLap;
	double avgLap;
	
	public DebugTimer(String name){
		this.name = name;
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	public void keep(){
		long keepLap = System.currentTimeMillis();
		
		if(keepLap - startTime > longestLap){
			longestLap = (int) (keepLap - startTime);
		}
		else if(keepLap - startTime < shortestLap){
			shortestLap = (int) (keepLap - startTime);
		}
		totalTime += keepLap - startTime;
		lap++;
		avgLap = (double)totalTime/(double)lap;
		startTime = System.currentTimeMillis(); 
	}
	
}
