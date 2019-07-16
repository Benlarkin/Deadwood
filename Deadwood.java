import java.util.*;

public class Deadwood {

  private final String PLAYERMSG = "Please Input Number of players: ";
  private final String NAME = " Name: ";
  private final String INPUT = "Input Player ";
  private final String PLAYERERR = "Must have 2-8 players.";

  public List<Player> players;
  public Board board;
  public Banker banker;



  // Creates a new game of Deadwood.
  public Deadwood() {
    try {
    players = new ArrayList<Player>();
    board = new Board(players);
    banker = new Banker();
    }
  catch (Exception e) {

  	}
  }

  private void addPlayer(Player newPlayer) {
      players.add(newPlayer);
  }

  public void startGame() {
    Timer timer = board.getTimer();
    int startDollars = 0;
    int startCredits = 0;
    int startRank = 1;
    System.out.print(PLAYERMSG);
    Scanner input = new Scanner(System.in);
    int playerNum = input.nextInt();
    input.nextLine();
    if(playerNum < 2 || playerNum > 8) {
      System.out.println(PLAYERERR);
      startGame();
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
    timer.nextDay();
  }
}

}
