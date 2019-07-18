import java.util.*;

public class Deadwood {

  private final String NAME = " Name: ";
  private final String INPUT = "Input Player ";
  private final String PLAYERERR = "Must have 2-8 players.";

  private static Deadwood game = new Deadwood();
  public List<Player> players;
  public Board board;



  // Creates a new game of Deadwood.
  private Deadwood() {
    try {
    players = new ArrayList<Player>();
    board = new Board(players);
    }
  catch (Exception e) {

  	}
  }
  
  public static Deadwood newGame() {
	  return game;
  }

  private void addPlayer(Player newPlayer) {
      players.add(newPlayer);
  }

  public void startGame(int playerNum) {
    Timer timer = board.getTimer();
    int startDollars = 0;
    int startCredits = 0;
    int startRank = 1;
    Scanner input = new Scanner(System.in);
    if(playerNum < 2 || playerNum > 8) {
      System.out.println(PLAYERERR);
      input.close();
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
      System.out.print(INPUT+ (i+1) + NAME);
      addPlayer(new Player(input.nextLine(), startDollars, startCredits, startRank));
    }
    input.close();
    while(timer.getDay() < 5) {
      board.placeCards();
      board.sendToTrailers(players);
      while(board.getCardsLeft() > 1) {
    	  timer.nextTurn();
      }
      timer.nextDay();
    }
  }
}

}
