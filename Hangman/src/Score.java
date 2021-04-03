import java.io.Serializable;


public class Score implements Serializable{
	private int scorehuman;
	private int scorepc;
	
	
	public Score(){
		scorehuman=0;
		scorepc=0;
	}
	
	
	
	public Score(int scorehuman, int scorepc) {
		
		this.scorehuman = scorehuman;
		this.scorepc = scorepc;
	}



	public int getScorehuman() {
		return scorehuman;
	}
	public void setScorehuman(int scorehuman) {
		this.scorehuman = scorehuman;
	}
	public int getScorepc() {
		return scorepc;
	}
	public void setScorepc(int scorepc) {
		this.scorepc = scorepc;
	}
	
	

}
