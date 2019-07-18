import java.util.*;

public class Timer {
	private final String ACTIVE = "Active player %s is in %s (Rank %x, %x dollars and %x credits)\n";
	
	
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
		if(turn == players.size()) {
			turn = 0;
		}
		Player activePlayer = players.get(turn);
		printActive(activePlayer);
		activePlayer.makeActive();
		activePlayer.playerTurn();
		activePlayer.makeInactive();
		turn++;
	}

	private void printActive(Player player) {
		System.out.printf(ACTIVE, player.getName(), player.getCurrentRoom().getName(), 
								player.getRank(), player.getDollars(), player.getCredits());
	}
	
	
	// advance day and player turn
	public int nextDay() {
		day++;
		return day;
	}

	public int setDay(int day) {
		this.day = day;
		return day;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
