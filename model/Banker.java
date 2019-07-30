package model;
public class Banker {

   // Adds the given amount to the given player's total dollars.
   public static int payMoney(Player player, int amount) {
      int newCurrent = player.getDollars() + amount;
      player.setDollars(newCurrent);
      return newCurrent;
   }

   // Adds the given amount to the given player's total credits.
   public static int payCredits(Player player, int amount) {
      int newCurrent = player.getCredits() + amount;
      player.setCredits(newCurrent);
      return newCurrent;
   }

   // Removes the given amount from the given player's total dollars. Returns -1 if insufficient funds.
   public static int takeMoney(Player player, int amount) {
      int newCurrent = player.getDollars() - amount;
      if(newCurrent < 0) {
         return -1;
      }
      player.setDollars(newCurrent);
      return newCurrent;
   }

   // Removes the given amount from the given player's total credits. Returns -1 if insufficient funds.
   public static int takeCredits(Player player, int amount) {
      int newCurrent = player.getCredits() - amount;
      if(newCurrent < 0) {
         return -1;
      }
      player.setCredits(newCurrent);
      return newCurrent;
   }
}
