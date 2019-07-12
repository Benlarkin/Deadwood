
public class StarringRole extends Role {

	public StarringRole(String name, String line, int req) {
		super.actor = null;
		super.name = name;
		super.line = line;
		super.requirement = req;
	}

	protected int onSuccess() {
		return -1;
	}

	protected int onFail() {
		return -1;
	}

}
