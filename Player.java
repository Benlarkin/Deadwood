public class Player extends Graphic {
  private String name;
  private int dollars;
  private int credits;
  private int rank;
  private int rehearsalChips;
  private Room currentRoom;
  private Role currentRole;
  
  public Player(String playerName, int startingDollars, int startingCredits, int startingRank) {
    name = playerName;
    dollars = startingDollars;
    credits = startingCredits;
    rank = startingRank;
    rehearsalChips = 0;
    // new instance of trailers or wait to implement when baord has all locations
    // saved and it'll be a board position
    currentRoom = null;
    currentRole = null;
  }

  public Room move() {
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

  public void setDollars(int newDollars) {
    dollars = newDollars;
  }

  public void setCredits(int newCredits) {
    credits = newCredits;
  }

  public void setRank(int newRank) {
    rank = newRank;
  }

  public void setCurrentRole(Role newRole) {
    currentRole = newRole;
  }
}
