import java.util.*;
import java.util.List;
import javax.swing.*;

public class Deadwood extends Globals {


  private static Deadwood game = new Deadwood();
  public static DeadwoodFrame frame = new DeadwoodFrame(game);
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
  private void startGame(int playerNum) {
    Timer timer = board.getTimer();
    if(playerNum < 2 || playerNum > 8) {
      System.out.println(PLAYERERR);
      Input.close();
      throw new IndexOutOfBoundsException();
    }
    else {
      frame.setVisible(true);
      updatePlayerBasedRules(playerNum, timer);
      runTurns(timer);
      Input.close();
    }
  }

  // Edits the base rules of the game based on how many players are playing.
  private void updatePlayerBasedRules(int playerNum, Timer timer) {
	int startDollars = 0;
	int startCredits = 0;
	int startRank = 1;
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
    getPlayerNames(playerNum, startDollars, startCredits, startRank);
  }

  // Adds players to the game of Deadwood.
  private void getPlayerNames(int playerNum, int startDollars, int startCredits, int startRank) {
      List<JTextField> list = frame.makeTextPanel(playerNum);
      for(int i = 0; i < list.size(); i++) {
    	addPlayer(new Player(list.get(i).getText(), startDollars, startCredits, startRank));
      }
	  //panel.get
      
  }

  // Runs each turn of Deadwood. Ends the game when the timer hits 5 days.
  private void runTurns(Timer timer) {
    while(timer.getDay() < 5) {
      board.placeCards();
      board.sendToTrailers(players);
      while(board.getCardsLeft() > 1) {
    	  timer.nextTurn();
      }
      timer.nextDay();
    }
    finishGame();
  }

  // Finishes the game of Deadwood.
  private void finishGame() {
    List<Player> scoreList = sortByScore();
    printWinners(scoreList);
  }

  // Returns a List of Players sorted by score.
  private List<Player> sortByScore() {
    List<Player> scoreList = new ArrayList<Player>();
    int length = players.size();
    for(int i = 0; i < length; i++) {
      scoreList.add(maxPlayer());
    }
    return scoreList;
  }

  // Returns the Player with the highest score, removing them from the list of Players.
  private Player maxPlayer() {
    int maxScore = -1;
    int index = 0;
    int i = 0;
    for(Player iter : players) {
      int score = iter.countScore();
      if(score > maxScore) {
        maxScore = score;
        index = i;
      }
      i++;
    }
    return players.remove(index);
  }

  // Prints the Players in order of highest to lowest score.
  private void printWinners(List<Player> playScore) {
    int i = 1;
    for(Player iter : playScore) {
      System.out.printf(SCOREMSG, i, iter.getName(), iter.countScore());
      i++;
    }
  }
}
