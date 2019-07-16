import java.util.*;

public class Board extends Graphic {

private final String ERROR = " error in Board.";
private final String COLON = ": ";
private final String ACTIVE = " (ACTIVE) ";
private final String COMMA = ", ";
private final String EMPTY = "";
private final String TRAILERS = "Trailers";
private final String CASTING = "Casting Office";
private final String IN = "is in ";
private final String SPACE = " ";

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
  List<Player> players = timer.getPlayers();
  for(Player current : players) {
    printLocation(current);
  }
}

private void printLocation(Player player) {
  System.out.print(player.getName() + SPACE);
  System.out.print(IN);
  if(player == timer.getActive()) {
    System.out.print(ACTIVE);
  }
  System.out.println(EMPTY);
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

  public Timer getTimer() {
    return timer;
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
