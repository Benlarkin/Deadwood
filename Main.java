public class Main {
  public static void main(String[] args) {
	  if(args.length != 1) {
		  System.out.println("Please enter number of players.");
	  }
	  else {
		  try {
			  Deadwood deadwood = Deadwood.newGame();
			  deadwood.startGame(Integer.parseInt(args[0]));
		  }
		  catch(Exception e) {
			  System.out.println("Invalid parameters.");
		  }
	  }
  }
}
