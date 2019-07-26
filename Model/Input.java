package Model;
import java.util.*;

public class Input {

   // Creates a consistent Scanner to read System.in across multiple classes.
   private static Scanner input = new Scanner(System.in);

   // Returns the System input Scanner.
   public static Scanner getScanner() {
      return input;
   }

   // Closes the System input Scanner.
   public static void close() {
      input.close();
   }

   // Returns input from the user.
   public static String playerInput() {
      return(input.nextLine());
   }
}
