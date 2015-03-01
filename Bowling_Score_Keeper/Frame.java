
public class Frame {
	private int score1=0; //points gained in throw 1
	private int score2=0; //point gained in throw 2
	private int bonus=0; //bonus points for this frame
	private int total=0; //total points for this frame
	private int status = 0; //number of throws left that will count towards the bonus points
	
	public void addScore(int round, int score){
		if(round==1)
			score1 += score;
		if(round==2)
			score2 += score;
		if(round==-1)
			bonus +=score;
		total+=score;
	}
	
	public int getScore1(){
		return score1;
	}
	
	public int getScore2(){
		return score2;
	}
	
	public int getBonus(){
		return bonus;
	}
	
	public int getTotal(){
		return total;
	}
	
	public int getStatus(){
		return status;
	}
	
	public void decrementStatus(){
		status--;
	}
	
	public void increaseStatus(int n){
		status+=n;
	}
}
