public class Player extends Graphic {
  private String name;
  private int dollars;
  private int credits;
  private int rank;
  private int rehearsalChips;
  private Room currentRoom;
  private Role currentRole;

  public Player(String name) {

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
    return -1;
  }

  public void setDollars() {

  }

  public void setCredits() {

  }

  public void setRank(int newRank) {
    rank = newRank;
  }

  public void setCurrentRole() {

  }

}
