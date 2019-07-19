import java.util.*;

public class Player extends Graphic {
	  private final String MOEVEMSG = "Where would you like to move? ";
	  private final String INVALIDMSG = "Invalid room. Choose again.";
	  private final String SCENEMSG = "Would you like to act or rehearse? (Type: a/r): ";
	  private final String TURNMSG = "It is your turn (Type: %s%sactive/board/end): ";
	  private final String ACTMSG = "You successfully acted your scene!";
	  private final String FAILACTMSG = "You failed to act your scene. Better luck next time!";
	  private final String REHEARSEMSG = "You rehearsed your scene and earned 1 rehearsal chip.";
	  private final String MOVESUCC = "%s moved from %s to %s.\n";
	  private final String WORK = "work/";
	  private final String PROMOTE = "promote/";
	  private final String TRAILER = "trailer";
	  private final String OFFICE = "office";
	  private final String END = "end";
	  private final String MOVEPLAYER = "move/";
	  private final String ACTIVE = "active";
	  private final String BOARD = "board";
	  private final String ACTIVEMSG = "Active player %s is in %s (Rank %x, %x dollars and %x credits)\n";
	  private final String BLANK = "";
	  private final String MOVE = "move";
	  
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
    String moved = MOVEPLAYER;
    while(!desiredAction.equalsIgnoreCase(END)) {	
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
		System.out.printf(ACTIVEMSG, name, currentRoom.getName(), 
				rank, dollars, credits);
    }
    else if(desiredAction.equalsIgnoreCase(BOARD)) {
    	// print board here
    }
    }
    }
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
