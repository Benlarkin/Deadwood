package model;
import controller.*;
public abstract class Role extends Graphic {
  protected Player actor;
  protected String name;
  protected String line;
  protected int requirement;

  // Returns the Player that has taken this role.
  public Player getActor() {
    return actor;
  }

  // Returns the name of the Role.
  public String getName() {
    return name;
  }

  // Returns the flavor text line of the Role.
  public String getLine() {
    return line;
  }

  // Returns the fame required to take this Role.
  public int getRequirement() {
    return requirement;
  }

  // Handles when a Player successfully acts this Role.
  protected abstract int onSuccess(Player player);

  // Handles when a Player fails to act this Role.
  protected abstract int onFail(Player player);

  // Decrements the shot counter for the Room with this Role.
  protected void decrementShotCounter(Player player){
    MovieSet m = (MovieSet)player.getCurrentRoom();
    m.decrementShotCounter();
  }
}
