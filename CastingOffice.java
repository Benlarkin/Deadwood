import java.util.*;

public class CastingOffice extends Room {

  // cost of upgrades, cost[0][x] = dollars, cost[1][x] = credits
  // cost[x][0] = rank 2, cost[x][4] = rank 6;
  // cost[1][0] is price to upgrade to rank 2 in credits
  // cost[0][3] is price to upgrade to rank 5 in dollars
  private int[][] cost = setUpgradeCost();
  private Banker banker = new Banker();

  public CastingOffice() {

  }

  // Scanner must be system.in
  // it is ok if player is max rank
  // promote player via private function OR return "insufficient currency"
  // (put this function inside of loop during player turn?)
  public void promote(Player player, Scanner input) {
    if (player.getRank() == 6) {
      System.out.println("You are already max rank!");
      return;
    }
    if (player.getRank() == 5) {
      System.out.println("What rank would you like to promote to? (6). You are currently rank: " + player.getRank());
    } else {
      System.out.println("What rank would you like to promote to? (" + (player.getRank() + 1)
          + "-6). You are currently rank: " + player.getRank());
    }
    System.out
        .println("You currently have " + player.getDollars() + " dollars, and " + player.getCredits() + " credits");
    representCost();
    // print a pretty array with leveling costs here... do later
    int desiredRank = Integer.parseInt(input.next());
    System.out.println("What currency will you use to upgrade? (d/c)");
    // check if sufficient currency
    String desiredCurrency = input.next();
    if (currencyCheck(player, desiredRank, desiredCurrency)) {
      promote(player, desiredRank, desiredCurrency);
    } else {
      System.out.println("Insufficient Currency");
    }
  }

  private int promote(Player player, int desiredRank, String desiredCurrency) {
    // update rank
    player.setRank(desiredRank);
    // adjust number for cost array
    desiredRank -= 2;
    // deduct credits
    if (desiredCurrency.equals("c")) {
      int requiredCredits = cost[1][desiredRank];
      banker.takeCredits(player, requiredCredits);
    } else {
      // deduct dollars
      int requiredDollars = cost[0][desiredRank];
      banker.takeMoney(player, requiredDollars);
    }
    // return players current rank for GUI
    return player.getRank();
  }

  private int[][] setUpgradeCost() {
    int[][] a = new int[2][5];
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

  private boolean currencyCheck(Player player, int desiredRank, String desiredCurrency) {
    // adjust number for cost array
    desiredRank -= 2;
    // credits case
    if (desiredCurrency.equals("c")) {
      int requiredCredits = cost[1][desiredRank];
      if (checkCredits(player, requiredCredits)) {
        return true;
      }
      // dollars case
    } else {
      int requiredDollars = cost[0][desiredRank];
      if (checkDollars(player, requiredDollars)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkCredits(Player player, int requiredCredits) {
    if (player.getCredits() < requiredCredits) {
      return false;
    }
    return true;
  }

  private boolean checkDollars(Player player, int requiredDollars) {
    if (player.getDollars() < requiredDollars) {
      return false;
    }
    return true;
  }

  private void representCost() {
    for (int i = 0; i < cost.length; i++) {
      for (int j = 0; j < cost[i].length; j++) {
        System.out.print(cost[i][j] + " ");
      }
      System.out.println();
    }
  }

}
