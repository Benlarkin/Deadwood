package model;
public class StarringRole extends Role {

   public StarringRole(String name, String line, int req, Area location) {
      super.actor = null;
      super.name = name;
      super.line = line;
      super.requirement = req;
      super.location = location;
   }

   // The player earns two credits for a successful acting attempt.
   protected int onSuccess(Player player) {
      Banker.payCredits(player, 2);
      decrementShotCounter(player);
      return 2;
   }

   // The player earns no money for a failed acting attempt.
   protected int onFail(Player player) {
      return 0;
   }
}
