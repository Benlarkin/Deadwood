import java.util.*;

public class Timer {
	
	private int day;
	private int turn;
	private List<Player> players;
	
	public Timer(List<Player> players) {
		
	}
	
	public int getDay() {
		return day;
	}
	
	public Player getActive() {
		return players.get(turn);
	}
	
	public void nextTurn() {
		
	}
	
	public int nextDay() {
		return -1;
	}
}