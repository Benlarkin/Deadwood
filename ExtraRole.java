public class ExtraRole extends Role {

  public ExtraRole(String name, String line, int req) {
    super.actor = null;
    super.name = name;
    super.line = line;
    super.requirement = req;
  }

 
  protected int onSuccess(Player player) {
	banker.payCredits(player, 1);
	return onFail(player);
  }

  protected int onFail(Player player) {
	banker.payMoney(player, 1);
    return 1;
  }

}
