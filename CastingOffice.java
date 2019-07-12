import java.util.*;

public class CastingOffice extends Room {

  // cost of upgrades, cost[x][0] = dollars, cost[x][1] = credits
  // cost[0][x] = rank 2, cost[4][x] = rank 6;
  // cost[0][1] is price to upgrade to rank 2 in credits
  // cost[3][0] is price to upgrade to rank 5 in dollars
  private int[][] cost = setUpgradeCost();

  public CastingOffice() {

  }

  public int promote(Player player, Scanner input) {
    return -1;
  }

  private int promote(Player player, int currency) {
    return -1;
  }

  private int[][] setUpgradeCost() {
    int[][] a = new int[5][2];
    // initialize dollars for rank 2-6
    a[0][0] = 4;
    a[0][1] = 10;
    a[0][2] = 18;
    a[0][3] = 28;
    a[0][4] = 40;
    // intiialize credits for rank 2-6
    a[1][0] = 5;
    a[1][1] = 10;
    a[1][2] = 15;
    a[1][3] = 20;
    a[1][4] = 25;
    return a;
  }

}
