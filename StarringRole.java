
public class StarringRole extends Role {

	public StarringRole(String name, String line, int req) {
		super.actor = null;
		super.name = name;
		super.line = line;
		super.requirement = req;
	}

	protected int onSuccess(Player play) {
		Banker banker = new Banker();
		banker.payCredits(play, 2);
		return 2;
	}

	protected int onFail(Player player) {
		return 0;
	}

}
