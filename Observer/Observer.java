package Observer;
import View.*;
import Model.*;
import java.util.*;

public class Observer {
  private static Deadwood game;
  private static DeadwoodFrame frame;
  private static Observer observer = new Observer();

  private Observer() {
    game = Deadwood.newGame();
    frame = new DeadwoodFrame();
  }

  public static Observer getObserver() {
    return observer;
  }

  public void startGame(int playerNum) {
    List<String> names = frame.getNameInput(playerNum);
    frame.initDice(playerNum);
    frame.setVisible(true);
    game.startGame(names, playerNum);
  }

  public static void moveButtonPressed() {

  }

  public void initDice(){
    
  }

}
