import java.util.*;

public class Player extends Graphic {

  private String name;
  private int dollars;
  private int credits;
  private int rank;
  private int rehearsalChips;
  private boolean activePlayer;
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
    activePlayer = false;
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
    }
    else {
    	handleTurnNoRole();
    }
  }
  


private void handleTurnNoRole() {
    String moved = MOVEPLAYER;
    boolean takingAction = true;
    while(takingAction == true) {	
    	int actionHandled = handleAction(moved);
    	if(actionHandled == PLAYEREND) {
    		takingAction = false;
    	}
    	else if(actionHandled == PLAYERMOVE) {
    		moved = BLANK;
    	}
    	else if(actionHandled == INVALIDACTION) {
    		
    	}
    }
}

private int handleAction(String moved) {
	String desiredAction;
	String currentRoomName = currentRoom.getName();
    boolean inOffice = currentRoomName.equalsIgnoreCase(OFFICE);
    if(inOffice) {
    	System.out.printf(TURNMSG, moved, BLANK);
    }
    else if(currentRoomName.equalsIgnoreCase(OFFICE)) {
    	System.out.printf(TURNMSG, moved, PROMOTE);
    }
    else {
    	System.out.printf(TURNMSG, moved, WORK);
    }
    desiredAction = Input.playerInput();
    if (desiredAction.equalsIgnoreCase(WORK)) {
      // display roles
      MovieSet m = (MovieSet) getCurrentRoom();
      for (Role r : m.getExtras()) {
        System.out.println("Extra Role: " + r.getName() + "Requirement: " + r.getRequirement());
      }
      for (Role r : m.getScene().getRoles()) {
        System.out.println("Starring Role: " + r.getName() + "Requirement: " + r.getRequirement());
      }
      // for (Role r : m)
      // check input for roles
      // assign role to player
      // end turn
    } else if(desiredAction.equalsIgnoreCase(MOVE) && moved.equals(MOVEPLAYER)){
      // call move function
      move();
      moved = BLANK;
    }
    else if(desiredAction.equalsIgnoreCase(PROMOTE) && inOffice) {
    	// promote here
    }
    else if(desiredAction.equalsIgnoreCase(ACTIVE)) {
    	printActive();
    }
    else if(desiredAction.equalsIgnoreCase(WHERE)) {
    	printLocation();
    }
    else if(desiredAction.equalsIgnoreCase(END)) {
    	return PLAYEREND;
    }
    return INVALIDACTION;
}
	

  
  private void printActive() {
	System.out.printf(ACTIVEMSG, name, credits, dollars, rank);
	if(currentRole != null) {
		System.out.printf(ROLEMSG, currentRole.getName(), currentRole.getLine());
	}
  }
  
  private void printLocation() {
	  
  }
  
  private void actScene() {
    Player player = this;
    Dice d = new Dice();
    Role cRole = player.getCurrentRole();
    if (d.roll(player.getRehearsalChips()) >= cRole.getRequirement()) {
      // success case 1: starring
      cRole.onSuccess(player);
      System.out.println(ACTMSG);
    } else {
      cRole.onFail(player);
      System.out.println(FAILACTMSG);
    }
  }

  public void makeActive() {
    activePlayer = true;
  }

  public void makeInactive() {
    activePlayer = false;
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
