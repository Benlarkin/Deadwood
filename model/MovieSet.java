package model;
import java.util.List;
import java.util.ArrayList;
import controller.*;

public class MovieSet extends Room {

  private List<Role> extras;
  private int reqSuccess;
  private Card scene;
  private ArrayList<Area> takeLocations;

  public MovieSet(String name, int takes, List<String> adjacent, List<Role> extras, Area location, ArrayList<Area> takeLocations) {
    super.name = name;
    this.reqSuccess = takes;
    super.adjacent = adjacent;
    this.extras = extras;
    this.scene = null;
    super.location = location;
    this.takeLocations = takeLocations;
  }

  public ArrayList<Area> getTakeLocations(){
    return takeLocations;
  }

  // Returns the list of Roles on the Space.
  public List<Role> getExtras() {
    return extras;
  }

  // Returns the shot counter's status.
  public int getReqSuccess() {
    return reqSuccess;
  }

  // Returns the scene currently on this Room.
  public Card getScene() {
    return scene;
  }

  // Sets the scene on this Room to the given scene.
  public void setScene(Card scene) {
    this.scene = scene;
  }

  // Decrements the shot counter. If it hits 0, the scene
  // is removed and any actors on or off the Card are given their
  // respective payout.
  public void decrementShotCounter() {
	Controller.removeShotCounter(takeLocations, reqSuccess, takeLocations.size() - (reqSuccess - 1));
    this.reqSuccess--;
    // if scene is wrapped
    if (this.reqSuccess == 0) {
      // payout starring roles
      this.getScene().finishCard(findStarringPlayers());
      // payout extras
      // remove roles from players
      for (Player p : super.getPlayers()) {
        p.resetRehearsalChips();
        if (p.getCurrentRole() instanceof ExtraRole) {
          Banker.payMoney(p, p.getCurrentRole().getRequirement());
        }
        p.setCurrentRole(null);
      }
      scene = null;
    }
  }

 // Finds the starring actors on the scene on this Room.
 private Player[] findStarringPlayers() {
    Player[] pArr = new Player[8];
    int i = 0;
    for (Player p : super.getPlayers()) {
      if (this.getScene().getRoles().contains(p.getCurrentRole())) {
        pArr[i] = p;
        i++;
      }
    }
    return pArr;
  }
}
