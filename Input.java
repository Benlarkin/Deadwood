import java.util.*;

public class Input {
	
   // Creates a consistent Scanner to read System.in across multiple classes.
   private static Scanner input = new Scanner(System.in);
   
   public static Scanner getScanner() {
      return input;
   }
   
   public static void close() {
      input.close();
   }
   
   public static String playerInput() {
      return(input.nextLine());
   }
}
