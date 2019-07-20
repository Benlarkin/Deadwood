import java.util.*;

public class Board extends Graphic {
   
   public List<Card> deck;
   private int cardsLeft;
   private List<Room> spaces;
   private Timer timer;
   
   // Creates a new Board from an XML file.
   public Board(List<Player> players) {
      try {
         XMLReader reader = new XMLReader();
         deck = reader.makeDeck();
         Collections.shuffle(deck);
         spaces = reader.arrangeBoard();
         assignNeighbors();
         placeCards();
         timer = new Timer(players);
      }
      catch (Exception e) {
         System.out.println(e + ERROR);
      }
   }
   
   // Finds and prints the location of each player.
   public void findPlayers() {
      List<Player> players = timer.getPlayers();
      for(Player current : players) {
         printLocation(current);
      }
   }
   
   // Prints the location of the given player.
   private void printLocation(Player player) {
      System.out.print(player.getName() + SPACE);
      System.out.print(IN);
      if(player == timer.getActive()) {
         System.out.print(ACTIVE);
      }
      System.out.println(EMPTY);
   }
   
   // Places 10 Moveset cards on the board.
   public void placeCards() {
      for(int i = 0; i < spaces.size(); i++) {
         Room current = spaces.get(i);
         if(!current.getName().equals(TRAILER) && !current.getName().equals(CASTING)) {
            MovieSet currentSet = (MovieSet) current;
            currentSet.setScene(deck.remove(i));
            cardsLeft++;
         }
      }
   }
   
   // Returns the players to the trailers at the start of a new day.
   public void sendToTrailers(List<Player> players) {
      Room trailers = null;
      for(int i = 0; i < spaces.size(); i++) {
         Room current = spaces.get(i);
         if(current.getName() == TRAILER) {
            trailers = current;
         }
         current.getPlayers().clear();
      }
      List<Player> trailerPlayers = trailers.getPlayers();
      for(Player currPlayer : players) {
         currPlayer.setCurrentRoom(trailers);
         trailerPlayers.add(currPlayer);
      }
   }
   
   public void FinishDay() {
      
   }
   
   
   public Timer getTimer() {
      return timer;
   }
   
   public int getCardsLeft() {
      return cardsLeft;
   }
   
   public void removeCard() {
      cardsLeft--;
   }
   
   public List<Room> getSpaces() {
      return spaces;
   }
   
   // Assigns neighbors in Room form to each Room based on their neighbors in String fomr.
   private void assignNeighbors() {
      for(Room currRoom : spaces) {
         List<Room> neighbors = new ArrayList<Room>();
         for(String currAdj : currRoom.getAdjacent()) {
            for(Room innerCurrRoom : spaces) {
               if(innerCurrRoom.getName().equals(currAdj)) {
                  neighbors.add(innerCurrRoom);
                  break;
               }
            }
         }
         currRoom.setNeighbors(neighbors);
      }
   }
}
