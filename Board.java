import java.util.*;

public class Board extends Graphic {

private final String ERROR = " error in Board.";
private final String COLON = ": ";
private final String ACTIVE = " (ACTIVE) ";
private final String COMMA = ", ";
private final String EMPTY = "";
private final String TRAILERS = "Trailers";
private final String CASTING = "Casting Office";

  public List<Card> deck;
  private int cardsLeft;
  private List<Room> spaces;
  private Player active;
  private Timer timer;

// Creates a new Board from an XML file.
public Board(List<Player> players) {
    try {
      XMLReader reader = new XMLReader();
      deck = reader.makeDeck();
      spaces = reader.arrangeBoard();
      placeCards();
      active = null;
      timer = new Timer(players);
    }
    catch (Exception e) {
    	 System.out.println(e + ERROR);
    }
  }


public void findPlayers() {
  for(Room current : spaces) {
    List<Player> players = current.getPlayers();
    int size = players.size();
    if(!players.isEmpty()) {
      System.out.print(current.getName() + COLON);
      Player currPlayer;
      for(int i = 0; i < size - 2; i++) {
        currPlayer = players.get(i);
        System.out.print(currPlayer.getName());
        if(currPlayer == timer.getActive()) {
          System.out.print(ACTIVE + COMMA);
        }
      }
      String active = EMPTY;
      currPlayer = players.get(size - 1);
        if(currPlayer == timer.getActive()) {
          active = ACTIVE;
        }
      System.out.println(currPlayer.getName() + active);
    }
  }
}

  public void placeCards() {
	  for(int i = 0; i < spaces.size(); i++) {
		  Room current = spaces.get(i);
		  if(!current.getName().equals(TRAILERS) && !current.getName().equals(CASTING)) {
			  MovieSet currentSet = (MovieSet) current;
		  	  currentSet.setScene(deck.remove(i));
		  	  cardsLeft++;
		  }
	  }
  }

  public void FinishDay() {

  }


  public int getCardsLeft() {
    return cardsLeft;
  }

  public void removeCard() {
	  cardsLeft--;
  }

  public List<Room> getSpaces() {
    return spaces;
  }

}
