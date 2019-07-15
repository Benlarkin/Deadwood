import java.util.*;

public class Deadwood {


  public List<Player> players;
  public Board board;
  public Banker banker;



  // Creates a new game of Deadwood.
  public Deadwood() {
    try {
    players = new ArrayList<Player>();
    board = new Board(players);
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
