import java.awt.*;
import java.util.*;

public class Room {
  protected String name;
  protected Image background;
  protected List<Player> players;

  public Player addPlayer(Player newPlayer) {
    return newPlayer;
  }

  public String getName() {
    return name;
  }

  public Image getBackground() {
    return background;
  }

  public List<Player> getPlayers() {
    return players;
  }

}
