import java.util.*;

public class Timer {
	
	private int day;
	private int turn;
	private List<Player> players;
	
	public Timer(List<Player> players) {
		this.day = 0;
		this.turn = 0;
		this.players = players;
	}
	
	// returns current day
	public int getDay() {
		return day;
	}
	
	// returns current player's turn
	public Player getActive() {
		return players.get(turn);
	}
	
	// next players turn
	public void nextTurn() {
		turn++;
	}
	
	// advance day and player turn
	public int nextDay() {
		day++;
		nextTurn();
	}
}