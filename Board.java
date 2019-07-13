import java.util.*;

public class Board extends Graphic {

  public List<Card> deck;
  private int cardsLeft;
  private List<Room> spaces;

// Creates a new Board from an XML file.
public Board() {
    try {
      XMLReader reader = new XMLReader();
      deck = reader.makeDeck();
      spaces = reader.arrangeBoard();
      placeCards();
    }
    catch (Exception e) {
    	 
    }
  }




  public void placeCards() {
	  for(int i = 0; i < spaces.size(); i++) {
		  Room current = spaces.get(i);
		  if(!current.getName().equals("Trailers") && !current.getName().equals("Casting Office")) {
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
