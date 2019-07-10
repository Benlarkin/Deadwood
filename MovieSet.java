import java.util.*;

public class MovieSet extends Room {

  private Card scene;
  private List<Role> extras;
  private int reqSucess;

  public MovieSet() {

  }

  public Card getScene() {
    return scene;
  }

  public List<Role> getExtras() {
    return extras;
  }

  private int getReqSuccess() {
    return reqSuccess;
  }
}
