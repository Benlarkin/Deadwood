import java.util.*;

public class Player extends Graphic {

  private String name;
  private int dollars;
  private int credits;
  private int rank;
  private int rehearsalChips;
  private Room currentRoom;
  private Role currentRole;

  public Player(String playerName, int startingDollars, int startingCredits, int startingRank) {
    this.name = playerName;
    this.dollars = startingDollars;
    this.credits = startingCredits;
    this.rank = startingRank;
    this.rehearsalChips = 0;
    // new instance of trailers or wait to implement when board has all locations
    // saved and it'll be a board position
    this.currentRoom = null;
    this.currentRole = null;
  }

  public void playerTurn() {
    String desiredAction = BLANK;

    if (!(getCurrentRole() == null)) {
      System.out.print(SCENEMSG);
      desiredAction = Input.playerInput();
      if (desiredAction.equals("a")) {
        actScene();
      } else {
        rehearseScene();
      }
    } else {
      handleTurnNoRole();
    }
  }

  private void handleTurnNoRole() {
    String moved = MOVEPLAYER;
    String worked = WORK;
    boolean takingAction = true;
    while (takingAction == true) {
      int actionHandled = handleAction(moved, worked);
      if (actionHandled == PLAYEREND) {
        takingAction = false;
      } else if (actionHandled == PLAYERMOVE) {
        moved = BLANK;
      } else if (actionHandled == PLAYERWORK) {
    	  worked = BLANK;
      } else if (actionHandled == INVALIDACTION) {
        System.out.println(INVALIDACTIONMSG);
      }
    }
  }

  private int handleAction(String moved, String worked) {
    Player player = this;
    String desiredAction;
    String currentRoomName = currentRoom.getName();
    boolean inOffice = currentRoomName.equalsIgnoreCase(OFFICE);
    boolean inTrailer = currentRoomName.equalsIgnoreCase(TRAILER);
    // check this boolean while testing
    if (inTrailer) {
      System.out.printf(TURNMSG, moved, BLANK, BLANK);
    } else if (currentRoomName.equalsIgnoreCase(OFFICE)) {
      System.out.printf(TURNMSG, moved, BLANK, PROMOTE);
    } else {
      System.out.printf(TURNMSG, moved, worked, WORK);
    }
    desiredAction = Input.playerInput();
    if (desiredAction.equalsIgnoreCase(WORKACTION) && worked.equals(WORK)) {
      // display roles
      MovieSet m = (MovieSet) getCurrentRoom();
      for (Role r : m.getExtras()) {
        System.out.println("Extra Role: " + r.getName() + "Requirement: " + r.getRequirement());
      }
      for (Role r : m.getScene().getRoles()) {
        System.out.println("Starring Role: " + r.getName() + "Requirement: " + r.getRequirement());
      }
      System.out.println("Enter the name of your desired role.");
      String desiredRole = Input.playerInput();
      // maybe in loop in case they mess up typing/aren't qualified
//      Iterator extraIter = m.getExtras().iterator();
//      Iterator starringIter = m.getScene().getRoles().iterator();
//      while (extraIter.hasNext() && starringIter.hasNext()) {
//        Role assignToPlayer = ((Role) extraIter.next());
//        String currentRoleCheckingName = assignToPlayer.getName();
//        if (currentRoleCheckingName.equalsIgnoreCase(desiredRole)) {
//          // set current role
//          this.setCurrentRole(assignToPlayer);
//          // remove from list of roles on that room
//          m.getExtras().remove(assignToPlayer);
//        } else {
//          assignToPlayer = (Role) starringIter.next();
//          currentRoleCheckingName = assignToPlayer.getName();
//          if (currentRoleCheckingName.equalsIgnoreCase(desiredRole)) {
//            // set current role
//            this.setCurrentRole(assignToPlayer);
//            // remove from list of roles on that room
//            m.getScene().getRoles().remove(assignToPlayer);
//          }
//        }
      Role selected = null;
       for (Role r : m.getExtras()) {
       if (desiredRole.equalsIgnoreCase(r.getName())) {
       // set current role
       this.setCurrentRole(r);
       selected = r;
       // remove from list of roles on that room

       }
       }
       if(selected != null) {
       m.getExtras().remove(selected);
       }
      // }
      // for (Role r : m.getScene().getRoles()) {
      // if (desiredRole.equalsIgnoreCase(r.getName())) {
      // // set current role
      // this.setCurrentRole(r);
      // // remove from list of roles on that room
      // m.getScene().getRoles().remove(r);
      // }
      // }
      // check input for roles
      // assign role to player
      return PLAYERWORK;
    } else if (desiredAction.equalsIgnoreCase(MOVE) && moved.equals(MOVEPLAYER)) {
      // call move function
      move();
      return PLAYERMOVE;
    } else if (desiredAction.equalsIgnoreCase(PROMOTE) && inOffice) {
      // promote here
      CastingOffice co = (CastingOffice) player.getCurrentRoom();
      co.promote(player);
      return SUCCESSACTION;
    } else if (desiredAction.equalsIgnoreCase(ACTIVE)) {
      printActive();
      return SUCCESSACTION;
    } else if (desiredAction.equalsIgnoreCase(WHERE)) {
      printLocation();
      return SUCCESSACTION;
    } else if (desiredAction.equalsIgnoreCase(END)) {
      return PLAYEREND;
    }
    return INVALIDACTION;
  }

  private void printActive() {
    System.out.printf(ACTIVEMSG, name, credits, dollars, rank);
    if (currentRole != null) {
      System.out.printf(ROLEMSG, currentRole.getName(), currentRole.getLine());
    }
  }

  private void printLocation() {
	  String location = currentRoom.getName();
	  if(currentRole == null) {
		  System.out.printf(WHEREMSG, location);
	  }
	  else {
		  Card currentCard = ((MovieSet) currentRoom).getScene();
		  System.out.printf(WHEREMSGWITHROLE, location, currentCard.getSceneName(), currentCard.getSceneNumber());
	  }
  }

  private void actScene() {
    Player player = this;
    Role cRole = player.getCurrentRole();
    if (Dice.roll(player.getRehearsalChips()) >= cRole.getRequirement()) {
      // success case 1: starring
      cRole.onSuccess(player);
      System.out.println(ACTMSG);
    } else {
      cRole.onFail(player);
      System.out.println(FAILACTMSG);
    }
  }

  private void rehearseScene() {
    Player player = this;
    player.incRehearsalChips();
    System.out.println(REHEARSEMSG);
  }

  public Room move() {
    Player player = this;
    System.out.println(MOEVEMSG);
    Room currRoom = player.getCurrentRoom();
    for (String s : currRoom.getAdjacent()) {
      System.out.println(s);
    }
    String desiredRoom = Input.playerInput();
    for (Room r : currRoom.getNeighbors()) {
      if (r.getName().equals(desiredRoom)) {
        player.setCurrentRoom(r);
        currRoom.getPlayers().remove(player);
        r.getPlayers().add(player);
        System.out.printf(MOVESUCC, name, currRoom.getName(), desiredRoom);
        return player.getCurrentRoom();
      }
    }
    System.out.println(INVALIDMSG);
    move();
    return null;
  }

  public int countScore() {
    return dollars + credits + (5 * rank);
  }

  public String getName() {
    return name;
  }

  public int getDollars() {
    return dollars;
  }

  public int getCredits() {
    return credits;
  }

  public int getRank() {
    return rank;
  }

  public int getRehearsalChips() {
    return rehearsalChips;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public Role getCurrentRole() {
    return currentRole;
  }

  public int incRehearsalChips() {
    rehearsalChips++;
    return rehearsalChips;
  }

  public Room setCurrentRoom(Room r) {
    this.currentRoom = r;
    return currentRoom;
  }

  public void setDollars(int newDollars) {
    this.dollars = newDollars;
  }

  public void setCredits(int newCredits) {
    this.credits = newCredits;
  }

  public void setRank(int newRank) {
    this.rank = newRank;
  }

  public void setCurrentRole(Role newRole) {
    this.currentRole = newRole;
  }
}
