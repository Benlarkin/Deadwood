import java.util.Scanner;

public class Player extends Graphic {
  private String name;
  private int dollars;
  private int credits;
  private int rank;
  private int rehearsalChips;
  private Room currentRoom;
  private Role currentRole;
  private final String MOEVEMSG = "Where would you like to move?";
  private final String INVALIDMSG = "Invalid room. Choose again.";

  public Player(String playerName, int startingDollars, int startingCredits, int startingRank) {
    this.name = playerName;
    this.dollars = startingDollars;
    this.credits = startingCredits;
    this.rank = startingRank;
    this.rehearsalChips = 0;
    // new instance of trailers or wait to implement when baord has all locations
    // saved and it'll be a board position
    this.currentRoom = null;
    this.currentRole = null;
  }

  public void playerTurn(Player player) {

  }

  private void actScene(Player player) {

  }

  private void rehearseScene(Player player) {

  }

  public Room move(Player player) {
    Scanner sc = new Scanner(System.in);
    boolean flag = true;
    System.out.println(MOEVEMSG);
    for (String s : player.getCurrentRoom().getAdjacent()) {
      System.out.println(s);
    }
    String desiredRoom = sc.nextLine();
    for (Room r : player.getCurrentRoom().getNeighbors()) {
      if (r.getName().equals(desiredRoom)) {
        player.setCurrentRoom(r);
        return player.getCurrentRoom();
      }
    }
    System.out.println(INVALIDMSG);
    move(player);
    return null;
  }

  public int countScore() {
    return -1;
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
