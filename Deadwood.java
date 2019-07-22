import java.util.*;

public class Deadwood extends Globals {

  private static Deadwood game = new Deadwood();
  public List<Player> players;
  public Board board;

  // Creates a new game of Deadwood, then starts it.
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


  // Creates a new game of Deadwood.
  private Deadwood() {
    try {
    players = new ArrayList<Player>();
    board = new Board(players);
    }
  catch (Exception e) {

  	}
  }
  
  // Returns the game of Deadwood (Singleton design).
  public static Deadwood newGame() {
	  return game;
  }

  // Adds a Player to the game of Deadwood
  private void addPlayer(Player newPlayer) {
      players.add(newPlayer);
  }

  // Starts the game of Deadwood. Variables are edited
  // based on the number of players.
  public void startGame(int playerNum) {
    Timer timer = board.getTimer();
    int startDollars = 0;
    int startCredits = 0;
    int startRank = 1;
    if(playerNum < 2 || playerNum > 8) {
      System.out.println(PLAYERERR);
      Input.close();
      throw new IndexOutOfBoundsException();
    }
    else {
      if(playerNum < 4) {
        timer.setDay(1);
      }
      if(playerNum == 5) {
        startCredits = 2;
      }
      if(playerNum == 6) {
        startCredits = 4;
      }
      if(playerNum == 7 || playerNum == 8) {
        startRank = 2;
      }
    for(int i = 0; i < playerNum; i++) {
      System.out.print(INPUT + (i+1) + NAMEREQ);
      addPlayer(new Player(Input.playerInput(), startDollars, startCredits, startRank));
    }
    while(timer.getDay() < 5) {
      board.placeCards();
      board.sendToTrailers(players);
      while(board.getCardsLeft() > 1) {
    	  timer.nextTurn();
      }
      timer.nextDay();
    }
    Input.close();
  }
}
}
