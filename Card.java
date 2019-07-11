import java.util.List;
import java.awt.*;
public class Card extends Graphic{

  private List<Role> roles;
  private String sceneName;
  private String line;
  private int budget;

  public Card(List<Role> roles, String sceneName, String line, int budget, Image background) {

  }

  public Area setLocal(Area newLocal) {
    return newLocal;
  }

  public String getSceneName() {
    return sceneName;
  }

  public String getLine() {
    return line;
  }

  public int getBudget() {
    return budget;
  }

  public List<Role> getRoles() {
    return roles;
  }



}
