import java.util.*;

public class Room extends Graphic {
  protected String name;
  protected List<String> adjacent;
  protected List<Room> neighbors;
  protected List<Player> players = new ArrayList<Player>();

  public Player addPlayer(Player newPlayer) {
    players.add(newPlayer);
    return newPlayer;
  }

  public String getName() {
    return name;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public List<String> getAdjacent() {
	  return adjacent;
  }

  public List<Room> setNeighbors(List<Room> neighbors) {
    this.neighbors = neighbors;
    return neighbors;
  }

  public List<Room> getNeighbors() {
    return neighbors;
  }
}