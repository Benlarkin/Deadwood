import java.util.*;

public class CastingOffice extends Room {

  // cost of upgrades, cost[x][0] = dollars, cost[x][1] = credits
  // cost[0][x] = rank 2, cost[4][x] = rank 6;
  // cost[0][1] is price to upgrade to rank 2 in credits
  // cost[3][0] is price to upgrade to rank 5 in dollars
  private int[][] cost = setUpgradeCost();

  public CastingOffice() {

  }

  // Scanner must be system.in
  // it is ok if player is max rank
  public int promote(Player player, Scanner input) {
    if (player.getRank() == 6) {
      System.out.println("You are already max rank!");
      return 6;
    }
    if (player.getRank() == 5) {
      System.out.println("What rank would you like to promote to? (6). You are currently rank: " + player.getRank());
    }
    System.out.println("What rank would you like to promote to? (" + (player.getRank() + 1)
        + "-6). You are currently rank: " + player.getRank());
    int response = Integer.parseInt(input.next());
    System.out.println("What currency will you use to upgrade? (d/c)");
    System.out.println("You currently have " + player.getDollars() + "dollars, and " + player.getCredits() + "credits");
    // check if sufficient currency
    // promote player via private function OR return "insufficient currency"
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
