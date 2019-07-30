import controller.*;


public class Main {

  // Creates a new game of Deadwood, then starts it.
  public static void main(String[] args) {
	  if(args.length != 1) {
		  System.out.println("ERROR");
	  }
	  else {
		  try {
        Controller observer = Controller.getObserver();
        observer.startGame(Integer.parseInt(args[0]));
		  }
		  catch(Exception e) {
			  System.out.println(e);
		  }
	  }
  }
}
