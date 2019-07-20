import java.util.List;
import java.util.Arrays;;

public class MovieSet extends Room {

  private List<Role> extras;
  private int reqSuccess;
  private Card scene;

  public MovieSet(String name, int takes, List<String> adjacent, List<Role> extras) {
    super.name = name;
    this.reqSuccess = takes;
    super.adjacent = adjacent;
    this.extras = extras;
    this.scene = null;
  }

  public List<Role> getExtras() {
    return extras;
  }

  public int getReqSuccess() {
    return reqSuccess;
  }

  public Card getScene() {
    return scene;
  }

  public void setScene(Card scene) {
    this.scene = scene;
  }

  public void decrementShotCounter() {
    this.reqSuccess--;
    // if scene is wrapped
    if (this.reqSuccess == 0) {
      // payout starring roles
      this.getScene().finishCard(findStarringPlayers());
      // payout extras
      // remove roles from players
      for (Player p : super.getPlayers()) {
        if (getExtras().contains(p.getCurrentRole())) {
          Banker.payMoney(p, p.getCurrentRole().getRequirement());
        }
        p.setCurrentRole(null);
      }
    }
  }

  Player[] findStarringPlayers() {
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
