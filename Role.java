public abstract class Role {
  protected Player actor;
  protected String name;
  protected String line;
  protected int requirement;
  protected Banker banker = new Banker();

  public Player getActor() {
    return actor;
  }

  public String getName() {
    return name;
  }

  public String getLine() {
    return line;
  }

  public int getRequirement() {
    return requirement;
  }

  public int act() {
    //return die roll
	
    return -1;
  }

  public int rehearse(Player player) {
    //return rehearsal chips
    return -1;
  }

  protected abstract int onSuccess(Player player);

  protected abstract int onFail(Player player);

}
