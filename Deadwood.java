import java.util.*;

public class Deadwood {


  public List<Player> players;
  public Board board;
  public List<Card> deck;
  public Banker banker;
  private Timer timer;

  
  // Creates a new game of Deadwood.
  public Deadwood() {
    try {
      XMLReader reader = new XMLReader();
    deck = reader.makeDeck();
    board = new Board(reader);
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
