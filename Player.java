public class Player extends Graphic {
   
   private String name;
   private int dollars;
   private int credits;
   private int rank;
   private int rehearsalChips;
   private Room currentRoom;
   private Role currentRole;
   
   public Player(String playerName, int startingDollars, int startingCredits, int startingRank) {
      this.name = playerName;
      this.dollars = startingDollars;
      this.credits = startingCredits;
      this.rank = startingRank;
      this.rehearsalChips = 0;
      this.currentRoom = null;
      this.currentRole = null;
   }
   
   public void playerTurn() {
      String desiredAction = BLANK;
      
      if (!(getCurrentRole() == null)) {
         System.out.print(SCENEMSG);
         desiredAction = Input.playerInput();
         if (desiredAction.equals("a")) {
            actScene();
         } else {
            rehearseScene();
         }
      } else {
         handleTurnNoRole();
      }
   }
   
   private void handleTurnNoRole() {
      String moved = MOVEPLAYER;
      String worked = WORK;
      boolean takingAction = true;
      while (takingAction == true) {
         int actionHandled = handleAction(moved, worked);
         if (actionHandled == PLAYEREND) {
            takingAction = false;
         } else if (actionHandled == PLAYERMOVE) {
            moved = BLANK;
         } else if (actionHandled == PLAYERWORK) {
            worked = BLANK;
         } else if (actionHandled == INVALIDACTION) {
            System.out.println(INVALIDACTIONMSG);
         }
      }
   }
   
   private int handleAction(String moved, String worked) {
      String currentRoomName = currentRoom.getName();
      boolean inOffice = currentRoomName.equalsIgnoreCase(OFFICE);
      boolean inTrailer = currentRoomName.equalsIgnoreCase(TRAILER);
      // check this boolean while testing
      if (inTrailer) {
         System.out.printf(TURNMSG, name, moved, BLANK, BLANK);
      } else if (currentRoomName.equalsIgnoreCase(OFFICE)) {
         System.out.printf(TURNMSG, name, moved, BLANK, PROMOTE);
      } else {
         System.out.printf(TURNMSG, name, moved, worked, WORK);
      }
      return processAction(Input.playerInput(), moved, worked, inOffice);
      
   }
   
   private int processAction(String desiredAction, String moved, String worked, boolean inOffice) {
	   if (desiredAction.contains(WORKACTION) && worked.equals(WORK)) {
		   if(currentRoom instanceof MovieSet && ((MovieSet) currentRoom).getScene() != null) {
	         if (handleWork(extract(desiredAction, WORKACTION)) == FOUND) {
	            return PLAYERWORK;
	         }
		   }
	      } else if (desiredAction.contains(MOVE) && moved.equals(MOVEPLAYER)) {
	         // call move function
	         if(move(extract(desiredAction, MOVE)) == FOUND){
	            return PLAYERMOVE;
	         }
	      } else if (desiredAction.equalsIgnoreCase(PROMOTE) && inOffice) {
	         // promote here
	         handlePromotion();
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
   
   private Role selectRole(MovieSet m, String desiredRole) {
	   Role selected = null;
	   for (Role r : m.getExtras()) {
	         if (desiredRole.equalsIgnoreCase(r.getName())) {
	            // set current role
	            this.setCurrentRole(r);
	            selected = r;
	            // remove from list of roles on that room
	         }
	      }
	   return selected;
   }
   
   private String extract(String base, String remove) {
      if(base.trim().equalsIgnoreCase(remove)) {
         return base.trim();
      }
      String result = base.substring(remove.length() + 1);
      return result.trim();
   }
   
   
   private void handlePromotion() {
	   CastingOffice co = (CastingOffice) currentRoom;
       co.promote(this);
   }
   
   private void printActive() {
      System.out.printf(ACTIVEMSG, name, dollars, credits, rank);
      if (currentRole != null) {
         System.out.printf(ROLEMSG, currentRole.getName(), currentRole.getLine());
      }
   }
   
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
   
   private void actScene() {
      Player player = this;
      Role cRole = player.getCurrentRole();
      if (Dice.roll(player.getRehearsalChips()) >= cRole.getRequirement()) {
         // success case 1: starring
         cRole.onSuccess(player);
         System.out.println(ACTMSG);
      } else {
         cRole.onFail(player);
         System.out.println(FAILACTMSG);
      }
   }
   
   private void rehearseScene() {
      Player player = this;
      player.incRehearsalChips();
      System.out.println(REHEARSEMSG);
   }
   
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
            currentRoom = r;
            currentRoom.getPlayers().remove(this);
            r.getPlayers().add(this);
            checkCard(r);
            return FOUND;
         }
      }
      System.out.println(INVALIDMSG);
      return NOTFOUND;
   }

   private void checkCard(Room r) {
	   if(r instanceof MovieSet) {
		   Card scene = ((MovieSet) r).getScene();
       		if(!scene.isRevealed()) {
       			scene.reveal();
       		}
       }
   }

  public int countScore() {
    return dollars + credits + (5 * rank);
  }

  public String getName() {
    return name;
  }

  public int getDollars() {
    return dollars;
  }

  public int getCredits() {
    return credits;
  }

  public int getRank() {
    return rank;
  }

  public int getRehearsalChips() {
    return rehearsalChips;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public Role getCurrentRole() {
    return currentRole;
  }

  public int incRehearsalChips() {
    this.rehearsalChips++;
    return rehearsalChips;
  }

  public Room setCurrentRoom(Room r) {
    this.currentRoom = r;
    return currentRoom;
  }

  public void setDollars(int newDollars) {
    this.dollars = newDollars;
  }

  public void setCredits(int newCredits) {
    this.credits = newCredits;
  }

  public void setRank(int newRank) {
    this.rank = newRank;
  }

  public void setCurrentRole(Role newRole) {
    this.currentRole = newRole;
  }

}
