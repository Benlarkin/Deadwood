import java.util.*;

public class Board extends Graphic {


  private int cardsLeft;
  private List<Room> spaces;

// Creates a new Board from an XML file.
public Board(XMLReader reader) {
    try {
      spaces = reader.arrangeBoard();
      placeCards();
    }
    catch (Exception e) {
    	 
    }
  }




  public void placeCards() {
	  
  }

  public void FinishDay() {

  }

  public int getCardsLeft() {
    return cardsLeft;
  }

  public void removeCard() {

  }

  public List<Room> getSpaces() {
    return spaces;
  }

}
