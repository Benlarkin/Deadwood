import java.util.*;

public class Board extends Graphic {

private final String FILENAME = "board.xml";

  private int cardsLeft;
  private List<Room> spaces;

public Board(XMLReader reader) {


    try {
      spaces = reader.arrangeBoard(FILENAME);
    }
    catch (Exception e) {
    	 System.out.println("2");
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
