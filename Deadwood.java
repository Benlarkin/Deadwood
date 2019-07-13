import java.util.*;

public class Deadwood {
private final String FILENAME = "cards.xml";

  public List<Player> players;
  public Board board;
  public List<Card> deck;
  public Banker banker;
  private Timer timer;

  public Deadwood() {
    try {
      XMLReader reader = new XMLReader();
    deck = reader.makeDeck(FILENAME);
    board = new Board(reader);
  }
  catch (Exception e) {
	  System.out.println("1");
  }
  }

  public void addPlayer(Player newPlayer) {
      players.add(newPlayer);
  }

  public void startGame() {

  }



}
