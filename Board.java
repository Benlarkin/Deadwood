import java.util.*;

public class Board extends Graphic {


  private int cardsLeft;
  private List<Room> spaces;

public Board(XMLReader reader) {


    try {
      spaces = reader.arrangeBoard();
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
