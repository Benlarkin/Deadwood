import java.util.List;
import java.awt.*;
public class MovieSet extends Room {

  private Card scene;
  private List<Role> extras;
  private int reqSuccess;

  public MovieSet(Image background, List<Role> extras, int reqSuccess) {

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
