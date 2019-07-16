public abstract class Role {
  protected Player actor;
  protected String name;
  protected String line;
  protected int requirement;


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

  public int act(Player player) {
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
