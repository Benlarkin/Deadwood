import java.util.*;

public class Room extends Graphic {
  protected String name;
  protected List<Player> players;
  protected List<String> adjacent;

  public Player addPlayer(Player newPlayer) {
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
}
