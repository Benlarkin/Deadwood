import java.util.List;
import java.awt.*;

public class MovieSet extends Room {

  private List<Role> extras;
  private int reqSuccess;
  private Card scene;

  public MovieSet(String name, int takes, List<String> adjacent, List<Role> extras) {
    super.name = name;
    reqSuccess = takes;
    super.adjacent = adjacent;
    this.extras = extras;
    scene = null;
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
    reqSuccess--;
  }
}
