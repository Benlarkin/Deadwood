public class Main {
	private final static String PLAYMSG = "Please enter number of players.";
  public static void main(String[] args) {
	  if(args.length != 1) {
		  System.out.println(PLAYMSG);
	  }
	  else {
		  try {
			  Deadwood deadwood = Deadwood.newGame();
			  deadwood.startGame(Integer.parseInt(args[0]));
		  }
		  catch(Exception e) {
			  System.out.println(e);
		  }
	  }
  }
}
