import java.util.ArrayList;
import java.util.Iterator;


public class Player {
	private String name;
	private Frame[] frames;
	private ArrayList<Frame> specialFrames = new ArrayList<Frame>();
	private int totalScore = 0;
	
	Player(String n){
		name = n;
		frames = new Frame[10];
		for(int i = 0; i < frames.length; i++) {
		    frames[i] = new Frame();
		}
	}
	
	public int getTotalScore(){
		return totalScore;
	}
	
	public String getName(){
		return name;
	}
	
	public Frame getFrame(int frameNumber){
		return frames[frameNumber];
	}
	
	//Add points to a previous frame in which a strike or spare was scored
	public void addBonusPoints(int score){
		if(!specialFrames.isEmpty()){
			Iterator<Frame> it = specialFrames.iterator();
			while(it.hasNext())
			{
			    Frame fr = it.next();
			    fr.addScore(-1,score);
			    fr.decrementStatus();
			    if(fr.getStatus()==0)
			    	it.remove();
			    totalScore+=score;
			}
		}
		
	}
	
	//Add score to a frame for a certain throw
	public void setFrameScore(int frameNumber, int round, int score){
		frames[frameNumber].addScore(round, score);
		
		//strike
		if(score==10){
			frames[frameNumber].increaseStatus(2);//strike means two throws will count towards bonus for the frame
			specialFrames.add(frames[frameNumber]);
		}
		
		//spare
		if(round==2 &&  frames[frameNumber].getTotal()==10){
			frames[frameNumber].increaseStatus(1); //spare means one throw will count towards bonus for the frame
			specialFrames.add(frames[frameNumber]);
		}
		
		totalScore+=score;
	}
}
