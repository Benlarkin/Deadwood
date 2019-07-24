public class ExtraRole extends Role {
   
   public ExtraRole(String name, String line, int req, Area location) {
      super.actor = null;
      super.name = name;
      super.line = line;
      super.requirement = req;
      super.location = location;
   }
   
   // On success, Player is paid one credit and one dollar.
   protected int onSuccess(Player player) {
      Banker.payCredits(player, 1);
      decrementShotCounter(player);
      return onFail(player);
   }
   
   // On fail, Player is paid one dollar.
   protected int onFail(Player player) {
      Banker.payMoney(player, 1);
      return 1;
   }
}
