import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;

public class Card extends Graphic {

  private List<Role> roles;
  private String sceneName;
  private String line;
  private int budget;
  private int sceneNumber;
  private Banker banker = new Banker();
  
  // Creates a Card object to be used in the game.
  public Card(List<Role> roles, String sceneName, String line, int budget, int sceneNumber, Image background) {
	  this.roles = roles;
	  this.sceneName = sceneName;
	  this.line = line;
	  this.budget = budget;
	  this.sceneNumber = sceneNumber;
  }

  public void finishCard(Player[] players) {
	  List<Integer> rolls = new ArrayList<Integer>();
	  Dice dice = new Dice();
	  for(int i = 0; i < budget; i++) {
		  rolls.add(dice.roll(0));
	  }
	  Collections.sort(rolls);
	  int j = 0;
	  while(!rolls.isEmpty()) {
		 if(j == players.length) {
			 j = 0;
		 }
		 Player currPlayer = players[j];
		 int currPayout = rolls.remove(0);
		 if(players[j] != null) {
			 banker.payMoney(currPlayer, currPayout);
		 }
		 j++;
	  }
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
