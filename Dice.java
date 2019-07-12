import java.util.*;

public class Dice extends Graphic {

  private Random rand = new Random();

  public Dice() {

  }

  // rolls number between 1-6 then adds the rehearsal points (aka additions)
  public int roll(int additions) {
    return 1 + rand.nextInt(6) + additions;
  }


}
