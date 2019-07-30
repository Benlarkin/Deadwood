package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;
import controller.*;

public class Card extends Graphic {

   private List<Role> roles;
   private String sceneName;
   private String line;
   private int budget;
   private int sceneNumber;
   private boolean revealed;

   // Creates a Card object to be used in the game.
   public Card(List<Role> roles, String sceneName, String line, int budget, int sceneNumber, String background) {
      this.roles = roles;
      this.sceneName = sceneName;
      this.line = line;
      this.budget = budget;
      this.sceneNumber = sceneNumber;
      this.background = background;
      revealed = false;
   }

   // Removes a Card from the board and handles its payout.
   public void finishCard(Player[] players) {
      List<Integer> rolls = new ArrayList<Integer>();
      for(int i = 0; i < budget; i++) {
         rolls.add(Dice.roll(0));
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
            Banker.payMoney(currPlayer, currPayout);
         }
         j++;
      }
   }

   // Reveals the Card.
   public void reveal() {
      revealed = true;
   }

   // Returns true if the Card is revealed.
   public boolean isRevealed() {
      return revealed;
   }

   // Returns the name of the scene.
   public String getSceneName() {
      return sceneName;
   }

   // Returns the flavor text line of the scene.
   public String getLine() {
      return line;
   }

   // Returns the budget of the scene.
   public int getBudget() {
      return budget;
   }

   // Returns the scene number.
   public int getSceneNumber() {
      return sceneNumber;
   }

   // Returns the Roles on this scene.
   public List<Role> getRoles() {
      return roles;
   }
}
