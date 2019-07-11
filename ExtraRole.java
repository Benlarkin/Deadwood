public class ExtraRole extends Role {

  public ExtraRole(Player actor, String name, String line, int requirement) {

  }

  protected int onSuccess() {
    return -1;
  }

  protected int onFail() {
    return -1;
  }

}
