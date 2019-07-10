import java.awt.*;
import java.util.*;

public class Card {

  private List<Role> roles;
  private String sceneName;
  private String line;
  private Image background;
  private int budget;
  private Area local;

  public Card() {

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

  public Image getBackground() {
    return background;
  }

  public int getBudget() {
    return budget;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public Area getLocal() {
    return local;
  }


}
