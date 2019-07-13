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
  // promote player via private function OR return "insufficient currency"
  // (put this function inside of loop during player turn?)
  public void promote(Player player, Scanner input) {
    if (player.getRank() == 6) {
      System.out.println("You are already max rank!");
      return;
    }
    if (player.getRank() == 5) {
      System.out.println("What rank would you like to promote to? (6). You are currently rank: " + player.getRank());
    }
    System.out.println("What rank would you like to promote to? (" + (player.getRank() + 1)
        + "-6). You are currently rank: " + player.getRank());
    int desiredRank = Integer.parseInt(input.next());
    System.out.println("What currency will you use to upgrade? (d/c)");
    System.out.println("You currently have " + player.getDollars() + "dollars, and " + player.getCredits() + "credits");
    // check if sufficient currency
    String desiredCurrency = input.next();
    if(currencyCheck(player, desiredRank, desiredCurrency)){
      promote(player, desiredRank);
    }
    else{
      System.out.println("Insufficient Currency");
    }
  }

  private int promote(Player player, int desiredRank) {
    player.setRank(desiredRank);
    // return players current rank for GUI
    return player.getRank();
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

  private boolean currencyCheck(Player player, int desiredRank, String desiredCurrency) {
    // adjust number for cost array
    desiredRank -= 2;
    // credits case
    if (desiredCurrency == "c") {
      int requiredCredits = cost[desiredRank][1];
      if (checkCredits(player, requiredCredits)) {
        return true;
      }
      // dollars case
    } else {
      int requiredDollars = cost[desiredRank][0];
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

}
