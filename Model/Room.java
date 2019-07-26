package Model;
import java.util.*;

public class Room extends Graphic {
   protected String name;
   protected List<String> adjacent;
   protected List<Room> neighbors;
   protected List<Player> players = new ArrayList<Player>();

   // Adds a player to this space.
   public Player addPlayer(Player newPlayer) {
      players.add(newPlayer);
      return newPlayer;
   }

   // Returns the name of this space.
   public String getName() {
      return name;
   }

   // Returns the list of players on this space.
   public List<Player> getPlayers() {
      return players;
   }

   // Returns the adjacent room names.
   public List<String> getAdjacent() {
      return adjacent;
   }

   // Sets the adjacent rooms in Room form.
   public List<Room> setNeighbors(List<Room> neighbors) {
      this.neighbors = neighbors;
      return neighbors;
   }

   // Returns the adjacent rooms in Room form.
   public List<Room> getNeighbors() {
      return neighbors;
   }
}
