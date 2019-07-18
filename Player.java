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
  private final String MOEVEMSG = "Where would you like to move?";
  private final String INVALIDMSG = "Invalid room. Choose again.";
  private final String SCENEMSG = "Would you like to act or rehearse? (Type: a/r): ";
  private final String TURNMSG = "Would you like to move or take a role? (Type: m/t): ";
  private final String ACTMSG = "You successfully acted your scene!";
  private final String FAILACTMSG = "You failed to act your scene. Better luck next time!";
  private final String REHEARSEMSG = "You rehearsed your scene and earned 1 rehearsal chip.";

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
    String desiredAction = "";
    Player player = this;
    if (!(player.getCurrentRole() == null)) {
      System.out.print(SCENEMSG);
      desiredAction = Input.playerInput();
      if (desiredAction.equals("a")) {
        player.actScene();
      } else {
        player.rehearseScene();
      }
    }
    System.out.print(TURNMSG);
    desiredAction = Input.playerInput();
    if (desiredAction.equals("t")) {
      // display roles
      MovieSet m = (MovieSet)player.getCurrentRoom();
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
    } else {
      // call move function
      move();
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
    Scanner sc = new Scanner(System.in);
    System.out.println(MOEVEMSG);
    Room currRoom = player.getCurrentRoom();
    for (String s : currRoom.getAdjacent()) {
      System.out.println(s);
    }
    String desiredRoom = sc.nextLine();
    sc.close();
    for (Room r : currRoom.getNeighbors()) {
      if (r.getName().equals(desiredRoom)) {
        player.setCurrentRoom(r);
        currRoom.getPlayers().remove(player);
        r.getPlayers().add(player);
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
