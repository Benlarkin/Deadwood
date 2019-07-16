import java.util.List;
import java.awt.*;

public class Card extends Graphic {

  private List<Role> roles;
  private String sceneName;
  private String line;
  private int budget;
  private int sceneNumber;
  
  // Creates a Card object to be used in the game.
  public Card(List<Role> roles, String sceneName, String line, int budget, int sceneNumber, Image background) {
	  this.roles = roles;
	  this.sceneName = sceneName;
	  this.line = line;
	  this.budget = budget;
	  this.sceneNumber = sceneNumber;
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

  public int getSceneNumber() {
	    return sceneNumber;
  }
  
  public List<Role> getRoles() {
    return roles;
  }



}
