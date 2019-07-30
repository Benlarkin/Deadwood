package model;
import java.util.*;
import controller.*;

public class Dice extends Graphic {

   private static Random rand = new Random();

   // rolls number between 1-6 then adds the rehearsal points (aka additions)
   public static int roll(int additions) {
      return 1 + rand.nextInt(6) + additions;
   }
}
