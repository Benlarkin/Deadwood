import java.util.*;

public class Deadwood {


  public List<Player> players;
  public Board board;
  public Banker banker;
  private Timer timer;

  
  // Creates a new game of Deadwood.
  public Deadwood() {
    try {
    board = new Board();
    players = new ArrayList<Player>();
    timer = new Timer(players);
    banker = new Banker();
    }
  catch (Exception e) {
	
  	}
  }

  public void addPlayer(Player newPlayer) {
      players.add(newPlayer);
  }

  public void startGame() {

  }



}
