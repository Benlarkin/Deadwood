package Model;
import java.util.*;

public class CastingOffice extends Room {

  // cost of upgrades, cost[0][x] = dollars, cost[1][x] = credits
  // cost[x][0] = rank 2, cost[x][4] = rank 6;
  // cost[1][0] is price to upgrade to rank 2 in credits
  // cost[0][3] is price to upgrade to rank 5 in dollars
  private int[][] cost = setUpgradeCost();

  public CastingOffice(List<String> adjacent, Area location) {
    super.name = OFFICE;
    super.adjacent = adjacent;
    super.location = location;
  }

  // it is ok if player is max rank
  // promote player via private function OR return "insufficient currency"
  // (put this function inside of loop during player turn?)
  public void promote(Player player) {
    if (player.getRank() == 6) {
      System.out.println(MAXRANK);
      return;
    }
    // only displays 1 rankup option
    if (player.getRank() == 5) {
      System.out.println(RANKREQUEST5 + player.getRank());
    } else {
      // basic flow case
      System.out.println(RANKREQUEST + (player.getRank() + 1) + RANKREQUEST_PT2 + player.getRank());
    }
    System.out.println(CURRENCYMSG1 + player.getDollars() + CURRENCYMSG2 + player.getCredits() + CURRENCYMSG3);
    // print a pretty array with leveling costs here
    representCost();
    int desiredRank = Integer.parseInt(Input.playerInput());
    System.out.println(DESIREDCURRENCYMSG);
    // check if sufficient currency
    String desiredCurrency = Input.playerInput();
    if (currencyCheck(player, desiredRank, desiredCurrency)) {
      promote(player, desiredRank, desiredCurrency);
    } else {
      System.out.println(INSUFFICIENTCURRENCY);
    }
  }

  private int promote(Player player, int desiredRank, String desiredCurrency) {
    // update rank
    player.setRank(desiredRank);
    // adjust number for cost array
    desiredRank -= 2;
    // deduct credits
    if (desiredCurrency.equals(CREDITS_AS_STRING)) {
      int requiredCredits = cost[1][desiredRank];
      Banker.takeCredits(player, requiredCredits);
    } else {
      // deduct dollars
      int requiredDollars = cost[0][desiredRank];
      Banker.takeMoney(player, requiredDollars);
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
    if (desiredCurrency.equals(CREDITS_AS_STRING)) {
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

  // checks if players have enough credits
  private boolean checkCredits(Player player, int requiredCredits) {
    if (player.getCredits() < requiredCredits) {
      return false;
    }
    return true;
  }

  // checks if players have enough dollars
  private boolean checkDollars(Player player, int requiredDollars) {
    if (player.getDollars() < requiredDollars) {
      return false;
    }
    return true;
  }

  // pretty prints array with dollar/currency cost for upgrading rank
  private void representCost() {
    String currency = DOLLAR_STRING;
    for (int i = 0; i < cost.length; i++) {
      for (int j = 0; j < cost[i].length; j++) {
        if (j > 1) {
          currency = CREDITS_STRING;
        }
        System.out.println(RANK_STRING + (j + 2) + COST_EQUALS_STRING + cost[i][j] + currency);
      }
      System.out.println();
    }
  }

}
