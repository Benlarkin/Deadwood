package model;

import controller.*;
public class Player extends Graphic {

   private String name;
   private int dollars;
   private int credits;
   private int rank;
   private int rehearsalChips;
   private Room currentRoom;
   private Role currentRole;
   private boolean takingTurn;

   public int lastRoll;

   public Player(String playerName, int startingDollars, int startingCredits, int startingRank, String background) {
      this.name = playerName;
      this.dollars = startingDollars;
      this.credits = startingCredits;
      this.rank = startingRank;
      this.rehearsalChips = 0;
      this.currentRoom = null;
      this.currentRole = null;
      this.lastRoll = -1;
      super.background = background;
      takingTurn = false;
   }

   // Begins the Player's turn.
   public void playerTurn() {
      handleTurn();
   }

   // Processes the actions that a Player can take on their turn.
   // A Player can check information about the active player,
   //   see where they are on the board, and end their turn
   //   at any time. In addition:
   // If they are in the Trailers, they can move once per turn.
   // If they are on a Movie Set and do not have a Role, they
   //   can move or take a role once per turn.
   // If they are on a Movie Set and do have a Role, they can
   //   act or rehearse once per turn.
   // If they are in the Casting Office, they can Promote.
   private void handleTurn() {
      String moved = MOVEPLAYER;
      String worked = WORK;
      takingTurn = true;
//      Controller.updateActive();
      Controller.hideButtons(currentRoom, this);
      while (takingTurn == true) {
        System.out.print("");
      }
   }

   public void endTurn() {
     takingTurn = false;
   }
   // Prints the message to the Player, showing what actions they
   //   are allowed to take based on their location and Role status.
   private int handleAction(String moved, String worked) {
      // JButton move = frame.getMoveButton();
      // JButton act = frame.getActButton();
      // JButton rehearse = frame.getRehearseButton();
      // move.setEnabled(false);
      // act.setEnabled(false);
      // rehearse.setEnabled(false);
      if (currentRoom instanceof Trailers) {
        // Show Move
        // move.setEnabled(true);
        // enable work button in move()
         System.out.printf(TURNMSG, name, moved, BLANK, BLANK);
      } else if (currentRoom instanceof CastingOffice) {
        // Show Promote, Move
        // move.setEnabled(true);
        // Make button for Promote
         System.out.printf(TURNMSG, name, moved, PROMOTE, BLANK);
      } else if (currentRole != null && (moved != BLANK && worked != BLANK)){
        // Show Act, Rehearse
        // act.setEnabled(true);
        // rehearse.setEnabled(true);
        // set buttons to false in these functions.
         System.out.printf(TURNMSG, name, ACT, REHEARSE, BLANK);
      } else {
        // Show Work, Move
        // move.setEnabled(true);
        //make button for work.
         System.out.printf(TURNMSG, name, moved, worked, WORK);
      }
      return processAction(Input.playerInput(), moved, worked);
   }

   // Processes the Action that is receieved from the Player, returns whether it was a valid command.
   // Returns PLAYERWORK if the Player chose to Work and successfully picked a Role.
   // Returns PLAYERMOVE if the Player chose to Move and successfully moved Rooms.
   // Returns PLAYEREND if the Player chose to end their turn.
   // Returns SUCCESSACTACTION if the Player chose to act or rehearse.
   // Returns SUCCESSACTION if the Player made a different valid command.
   // Returns INVALIDACTION otherwise.
   private int processAction(String desiredAction, String moved, String worked) {
      if (desiredAction.contains(WORKACTION) && worked.equals(WORK)) {
         if(currentRoom instanceof MovieSet && ((MovieSet) currentRoom).getScene() != null) {
            if (handleWork(extract(desiredAction, WORKACTION)) == FOUND) {
               return PLAYERWORK;
            }
         }
      } else if (desiredAction.equals(ACTACTION) && currentRole != null && (moved != BLANK && worked != BLANK)){
         actScene();
         return SUCCESSACTACTION;
      } else if(desiredAction.equalsIgnoreCase(REHEARSEACTION) && currentRole != null && (moved != BLANK && worked != BLANK)) {
         rehearseScene();
         return SUCCESSACTACTION;
      } else if (desiredAction.contains(MOVE) && moved.equals(MOVEPLAYER)) {
         // call move function
         if(move(extract(desiredAction, MOVE)) == FOUND){
            return PLAYERMOVE;
         }
      } else if (desiredAction.equalsIgnoreCase(PROMOTEACTION) && currentRoom instanceof CastingOffice) {
         // promote here
        //  handlePromotion();
         return SUCCESSACTION;
      } else if (desiredAction.equalsIgnoreCase(ACTIVE)) {
         printActive();
         return SUCCESSACTION;
      } else if (desiredAction.equalsIgnoreCase(WHERE)) {
         printLocation();
         return SUCCESSACTION;
      } else if (desiredAction.equalsIgnoreCase(END)) {
         return PLAYEREND;
      }
      return INVALIDACTION;
   }

   public void takeRole(String desiredRole) {
     handleWork(desiredRole);
     super.location = currentRole.getLocation();
   }
   // Allows a Player to pick a Role. Displays the Roles if no Role is given with the action.
   private int handleWork(String desiredRole) {
      // display roles
      MovieSet m = (MovieSet) getCurrentRoom();
      if(desiredRole.equals(WORKACTION)) {
         for (Role r : m.getExtras()) {
            System.out.printf(EXTRAROLEMSG, r.getName(), r.getRequirement());
         }
         for (Role r : m.getScene().getRoles()) {
            System.out.printf(STARRINGROLEMSG, r.getName(), r.getRequirement());
         }
         System.out.println(PICKROLE);
         desiredRole = Input.playerInput();
      }
      Role selected = selectRole(m, desiredRole);
      if(selected != null) {
         m.getExtras().remove(selected);
         return FOUND;
      }
      return NOTFOUND;
   }

   // Allows the Player to select their Role, removing it from the list of possible Roles.
   private Role selectRole(MovieSet m, String desiredRole) {
      Role selected = null;
      for (Role r : m.getExtras()) {
         if (desiredRole.equalsIgnoreCase(r.getName()) && r.getRequirement() <= rank) {
            // set current role
            this.setCurrentRole(r);
            selected = r;
            // remove from list of roles on that room
         }
      }
      if(selected == null) {
         for (Role r : m.getScene().getRoles()) {
            if (desiredRole.equalsIgnoreCase(r.getName()) && r.getRequirement() <= rank) {
               // set current role
               this.setCurrentRole(r);
               selected = r;
               // remove from list of roles on that room
            }
         }
      }
		Controller.updateActive(this);
      return selected;
   }

   // Extracts the second String from the first, returning the new String.
   private String extract(String base, String remove) {
      if(base.trim().equalsIgnoreCase(remove)) {
         return base.trim();
      }
      String result = base.substring(remove.length() + 1);
      return result.trim();
   }

   // Allows the Player to access the Casting Office for fame promotion/
   public void handlePromotion(int newRank, String desiredCurrency) {
     if(newRank > 0) {
      CastingOffice co = (CastingOffice) currentRoom;
      co.promote(this, newRank, desiredCurrency);
    }
   }

   // Prints the status of the Active Player.
   private void printActive() {
      System.out.printf(ACTIVEMSG, name, dollars, credits, rank);
      if (currentRole != null) {
         System.out.printf(ROLEMSG, currentRole.getName(), currentRole.getLine());
      }
   }

   // Prints the Location of the Active Player, and says what Role they are working on (if any).
   private void printLocation() {
      String location = currentRoom.getName();
      if(currentRole == null) {
         System.out.printf(WHEREMSG, location);
      }
      else {
         Card currentCard = ((MovieSet) currentRoom).getScene();
         System.out.printf(WHEREMSGWITHROLE, location, currentCard.getSceneName(), currentCard.getSceneNumber());
      }
   }

   public boolean actSuccess() {
     return actScene();
   }

   // Allows a Player to act on their Role, regardless of being an ExtraRole or StarringRole.
   private boolean actScene() {
      Role cRole = currentRole;
      MovieSet playerRoom = (MovieSet) currentRoom;
      int budget = playerRoom.getScene().getBudget();
      lastRoll = Dice.roll(rehearsalChips);
      if (lastRoll >= budget) {
         // success case 1: starring
         cRole.onSuccess(this);
         if(currentRole == null) {
        	 Controller.removeCard(currentRoom);
         }
        return true;
      } else {
         cRole.onFail(this);
         return false;
      }
   }

   // Allows a Player to rehearse their scene, getting +1 to their attempts to Act.
   private void rehearseScene() {
      Player player = this;
      player.incRehearsalChips();
      System.out.println(REHEARSEMSG);
		Controller.updateActive(this);
   }

   // Allows a Player to move to a new, adjacent Room.
   public int move(String desiredRoom) {
      if(desiredRoom.equalsIgnoreCase(MOVE)) {
         System.out.println(MOEVEMSG);
         for (String s : currentRoom.getAdjacent()) {
            System.out.println(s);
         }
         desiredRoom = Input.playerInput();
      }
      for (Room r : currentRoom.getNeighbors()) {
         if (r.getName().equals(desiredRoom)) {
            System.out.printf(MOVESUCC, name, currentRoom.getName(), desiredRoom);
            this.setCurrentRoom(r);
            currentRoom.getPlayers().remove(this);
            r.getPlayers().add(this);
            checkCard(r);
    		Controller.updateActive(this);
            return FOUND;
         }
      }
      System.out.println(INVALIDMSG);
      return NOTFOUND;
   }

   // Checks to see if the given Room is a MovieSet, and if its Card has not been flipped.
   // If not, then the Card is revealed.
   private void checkCard(Room r) {
      if(r instanceof MovieSet) {
         Card scene = ((MovieSet) r).getScene();
         if(!scene.isRevealed()) {
            scene.reveal();
            Controller.flipCard(currentRoom, scene.getBackground());
         }
      }
   }

   // Returns the score of the Player ($ + credits + 5 x Rank).
   public int countScore() {
      return dollars + credits + (5 * rank);
   }

   // Returns the name of the Player.
   public String getName() {
      return name;
   }

   // Returns how many dollars the Player has.
   public int getDollars() {
      return dollars;
   }

   // Returns how many credits the Player has.
   public int getCredits() {
      return credits;
   }

   // Returns the rank of the Player.
   public int getRank() {
      return rank;
   }

   // Returns how many Rehearsal Chips the Player has.
   public int getRehearsalChips() {
      return rehearsalChips;
   }

   // Returns where the Player currently is.
   public Room getCurrentRoom() {
      return currentRoom;
   }

   // Returns the Player's current role.
   public Role getCurrentRole() {
      return currentRole;
   }

   // Increases the number of Rehearsal Chips the Player has by one.
   public int incRehearsalChips() {
      this.rehearsalChips++;
	  Controller.updateActive(this);
      return rehearsalChips;
   }

   // Moves the Player to the given Room.
   public Room setCurrentRoom(Room r) {
      this.currentRoom = r;
      super.location = r.getLocation();
      return currentRoom;
   }

   // Gives the Player the given number of dollars.
   public void setDollars(int newDollars) {
      this.dollars = newDollars;
   }

   // Gives the Player the given number of credits.
   public void setCredits(int newCredits) {
      this.credits = newCredits;
   }

   // resets rehearsal chips to 0, called when scene is wrapped
   public void resetRehearsalChips(){
      this.rehearsalChips = 0;
   }

   // Sets the Player's rank to the given rank.
   public void setRank(int newRank) {
      this.rank = newRank;
      Controller.updatePlayer();
   }

   // Gives the Player the given Role.
   public void setCurrentRole(Role newRole) {
      this.currentRole = newRole;
      if(newRole != null) {
      super.location = newRole.getLocation();
      }
      Controller.updatePlayer();
   }
}
