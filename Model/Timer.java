package Model;
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
		if(turn == players.size()) {
			turn = 0;
		}
		Player activePlayer = players.get(turn);
		activePlayer.playerTurn();
		turn++;
	}

	// advance day and player turn
	public int nextDay() {
		day++;
		return day;
	}

	// Sets the day to the given integer. For use with variant player sizes.
	public int setDay(int day) {
		this.day = day;
		return day;
	}

	// Returns the list of players in the game.
	public List<Player> getPlayers() {
		return players;
	}
}
