package Model;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import Observer.Observer;

public class Deadwood extends Globals {


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
  public void startGame(List<String> names, int playerNum) {
    Timer timer = board.getTimer();
    if(playerNum < 2 || playerNum > 8) {
      System.out.println(PLAYERERR);
      Input.close();
      throw new IndexOutOfBoundsException();
    }
    else {
    //  frame.setVisible(true);
      updatePlayerBasedRules(names, playerNum, timer);
      runTurns(timer);
      Input.close();
    }
  }

  private void addPlayersToGame(List<String> names, int playerNum, int startDollars, int startCredits, int startRank) {
    for(int i = 0; i < playerNum; i++) {
      addPlayer(new Player(names.get(i), startDollars, startCredits, startRank));
    }
  }

  // Edits the base rules of the game based on how many players are playing.
  private void updatePlayerBasedRules(List<String> names, int playerNum, Timer timer) {
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
    addPlayersToGame(names, playerNum, startDollars, startCredits, startRank);
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
