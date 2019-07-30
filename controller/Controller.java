package controller;
import model.*;
import view.*;
import model.Timer;
import java.util.*;
import javax.swing.*;

public class Controller {
  private static final String NOPSBLEROLES = "You do not have enough fame \n to take any roles here.";
  private static final String DOLLARS = "Dollars";
  private static final String CREDITS = "Credits";
  private static final String REHEARSALPOPUP = "%s spent their turn rehearsing. They now have %d rehearsal chips.";
  private static final String ACTPOPUP = "%s %s their role this turn.";
  private static final String SUCCESS = "succeeded on acting";
  private static final String FAIL = "failed to act";
  
  private static Deadwood game;
  private static DeadwoodFrame frame;
  private static Controller Controller = new Controller();
  private static Timer timer;

  private Controller() {
    game = Deadwood.newGame();
    timer = game.board.getTimer();
    frame = new DeadwoodFrame();
  }

  public static Controller getObserver() {
    return Controller;
  }

  public void startGame(int playerNum) {
    List<String> names = frame.getNameInput(playerNum);
    frame.initDice(playerNum);
    frame.setVisible(true);
    game.startGame(names, playerNum);
  }

  public static void moveButtonPressed() {
    Player activePlayer = timer.getActive();
    String[] adjacent = arrayListToArray(activePlayer.getCurrentRoom().getAdjacent());
    String desiredRoom = frame.getRoomInput(adjacent);
    if(desiredRoom != null && desiredRoom.length() > 0) {
      game.board.getTimer().getActive().move(desiredRoom);
    }
  }

  private static String[] arrayListToArray(List<String> list) {
    int size = list.size();
    String[] array = new String[size];
    for(int i = 0; i < size; i++) {
      array[i] = list.get(i);
    }
    return array;
  }

  public static void takeButtonPressed() {
    Player activePlayer = timer.getActive();
    MovieSet room = ((MovieSet) activePlayer.getCurrentRoom());
    String[] possibleRoles = getPossibleRoles(room.getScene().getRoles(), room.getExtras(), activePlayer.getRank());
    if(possibleRoles.length > 0) {
      activePlayer.takeRole(frame.getTakeInput(possibleRoles));
    }
    else {
      frame.errorMessagePopup(NOPSBLEROLES);
    }
  }

  private static String[] getPossibleRoles(List<Role> starring, List<Role> extra, int rank) {
    List<String> roles = new ArrayList<String>();
    for(int i = 0; i < starring.size(); i++) {
      Role currStar = starring.get(i);
      if(currStar.getRequirement() <= rank) {
        roles.add(currStar.getName());
      }
    }
    for(int j = 0; j < extra.size(); j++) {
      Role currExtra = extra.get(j);
      if(currExtra.getRequirement() <= rank) {
        roles.add(currExtra.getName());
      }
    }
    return arrayListToArray(roles);
  }

  public static void hideButtons(Room room, Player active) {
    if(room instanceof Trailers) {
      defaultButtons(false, false, true, false, false);
    }
    else if(room instanceof CastingOffice) {
      defaultButtons(false, false, true, false, true);
    }
    else if(room instanceof MovieSet) {
      if(((MovieSet) room).getScene() == null) {
        defaultButtons(false, false, true, false, false);

      }
      else if(active.getCurrentRole() == null){
            defaultButtons(false, false, true, true, false);
      }
      else {
        defaultButtons(true, true, false, false, false);
      }
    }
  }

  private static void defaultButtons(boolean actShow, boolean rehearseShow,
    boolean moveShow, boolean takeShow, boolean promoteShow) {
    JButton act = frame.getActButton();
    JButton rehearse = frame.getRehearseButton();
    JButton move = frame.getMoveButton();
    JButton take = frame.getTakeButton();
    JButton promote = frame.getPromoteButton();
    act.setVisible(actShow);
    rehearse.setVisible(rehearseShow);
    move.setVisible(moveShow);
    take.setVisible(takeShow);
    promote.setVisible(promoteShow);
  }


  public static void endCurrentTurn() {
    timer.getActive().endTurn();
  }

  public static void actionTaken(Room room) {

  }

  public static void promoteClicked() {
    Player active = timer.getActive();
    String desiredCurrency = frame.getDesiredCurrency();
    try {
    if(desiredCurrency.equals(DOLLARS)) {
      active.handlePromotion(Integer.parseInt(frame.getPromoteRankDollars(active.getDollars(), active.getRank())), desiredCurrency);
    }
    else if(desiredCurrency.equals(CREDITS)) {
        active.handlePromotion(Integer.parseInt(frame.getPromoteRankCredits(active.getCredits(), active.getRank())), desiredCurrency);
    }
  }
  catch(Exception e) {

  }

  }

  public static int[][] getUpgradeCost() {
    return CastingOffice.getCost();
  }

  public static void rehearseClicked() {
    Player active = timer.getActive();
    active.incRehearsalChips();
    frame.errorMessagePopup(String.format(REHEARSALPOPUP, active.getName(), active.getRehearsalChips()));
  }

  public static void actClicked() {
    Player active = timer.getActive();
    if(active.actSuccess()) {
      frame.errorMessagePopup(String.format(ACTPOPUP, active.getName(), SUCCESS));
    }
    else {
      frame.errorMessagePopup(String.format(ACTPOPUP, active.getName(), FAIL));
    }
  }

}
