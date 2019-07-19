
public class StarringRole extends Role {
   
   public StarringRole(String name, String line, int req) {
      super.actor = null;
      super.name = name;
      super.line = line;
      super.requirement = req;
   }
   
   // The player earns two credits for a successful acting attempt.
   protected int onSuccess(Player play) {
      Banker.payCredits(play, 2);
      return 2;
   }
   
   // The player earns no money for a failed acting attempt.
   protected int onFail(Player player) {
      return 0;
   }
   
}
