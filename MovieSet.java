import java.util.List;
import java.awt.*;
public class MovieSet extends Room {


  private List<Role> extras;
  private int reqSuccess;

  public MovieSet(String name, int takes, List<String> adjacent, List<Role> extras) {
    super.name = name;
    reqSuccess = takes;
    super.adjacent = adjacent;
    this.extras = extras;
  }


  public List<Role> getExtras() {
    return extras;
  }

  private int getReqSuccess() {
    return reqSuccess;
  }
}
