package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

import controller.*;
import model.Room;

import java.util.List;
import java.util.*;

public class DeadwoodFrame extends JFrame {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelGameBoard;
    private List<JLabel> labelCards = new ArrayList<JLabel>();
    private JLabel labelPlayer;
    private JLabel labelMenu;
    private JPanel panelActive;
    private JLabel labelActive;
    private JLabel label_p1;
    private JLabel label_p2;
    private JLabel label_p3;
    private JLabel label_p4;
    private JLabel label_p5;
    private JLabel label_p6;
    private JLabel label_p7;
    private JLabel label_p8;

    private List<JLabel> playerDice = new ArrayList<JLabel>();
    private int turn = 0;

    private JButton buttonAct;
    private JButton buttonRehearse;
    private JButton buttonMove;
    private JButton buttonTake;
    private JButton buttonPromote;
    private JButton buttonEnd;
    private JLayeredPane paneDeadwood;


    private ImageIcon iconGameBoard;

    private static final String DEADWOOD_TITLE = "Deadwood";
    private static final String GAME_BOARD_IMAGE = "images/board.jpg";
    private static final String CARD_IMAGE = "images/Card-Back.png";
    private static final String DICE_IMAGE = "images/r1.png";
    private static final String MENU_LABEL_TEXT = "MENU";
    private static final String ACT_BUTTON_TEXT = "ACT";
    private static final String TAKE_BUTTON_TEXT = "TAKE";
    private static final String REHEARSE_BUTTON_TEXT = "REHEARSE";
    private static final String PROMOTE_BUTTON_TEXT = "PROMOTE";
    private static final String END_BUTTON_TEXT = "END";
    private static final String MOVE_BUTTON_TEXT = "MOVE";
    protected static final String PLAYERMSG = "Player %d name: ";


    protected static final String P1DICE = "images/dice/b%d.png";
    protected static final String P2DICE = "images/dice/c%d.png";
    protected static final String P3DICE = "images/dice/g%d.png";
    protected static final String P4DICE = "images/dice/o%d.png";
    protected static final String P5DICE = "images/dice/p%d.png";
    protected static final String P6DICE = "images/dice/r%d.png";
    protected static final String P7DICE = "images/dice/v%d.png";
    protected static final String P8DICE = "images/dice/y%d.png";

    public DeadwoodFrame() {
        super(DEADWOOD_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeLabels();
        initializeButtons();
    }

    private void initializeLabels() {
        setupGameBoardLabel();
        setupActivePanel();
        setupMenuLabel();
    }

    private void setupGameBoardLabel() {
        labelGameBoard = new JLabel();
        iconGameBoard = new ImageIcon(GAME_BOARD_IMAGE);
        labelGameBoard.setIcon(iconGameBoard);
        labelGameBoard.setBounds(0, 0, iconGameBoard.getIconWidth(), iconGameBoard.getIconHeight());
        setSize(iconGameBoard.getIconWidth() + 200, iconGameBoard.getIconHeight()); // Set the size of the GUI
    }


    private void setupCardLabel(Area area) {
        JLabel card = new JLabel();
        ImageIcon cardIcon = new ImageIcon(CARD_IMAGE);
        card.setIcon(cardIcon);
        card.setBounds(area.getX(), area.getY(), area.getW(), area.getH());
        card.setOpaque(true);
        labelCards.add(card);
    }

    public void setCard(Area area) {
      setupCardLabel(area);
    }

    // SET UP Player dice in trailers at start of game
//    public void initDice(int playerCount) {
//        boolean flag = false;
//        if(playerCount>=7){
//            flag = true;
//        }
//        paneDeadwood = getLayeredPane();
//        label_p1 = new JLabel();
//        ImageIcon icon1 = new ImageIcon(String.format(P1DICE, 1));
//            if(flag){
//                icon1 = new ImageIcon("images/dice/b2.png");
//            }
//        label_p1.setIcon(icon1);
//        label_p2 = new JLabel();
//        ImageIcon icon2 = new ImageIcon("images/dice/c1.png");
//        if(flag){
//            icon2 = new ImageIcon("images/dice/c2.png");
//        }
//        label_p2.setIcon(icon2);
//        label_p1.setBounds(1030, 400, 46, 46);
//        label_p2.setBounds(1030, 350, 46, 46);
//        paneDeadwood.add(label_p1, new Integer(3));
//        paneDeadwood.add(label_p2, new Integer(3));
//        playerDice.add(label_p1);
//        playerDice.add(label_p2);
//        if (playerCount >= 3) {
//            label_p3 = new JLabel();
//            ImageIcon icon3 = new ImageIcon("images/dice/g1.png");
//            if(flag){
//                icon3 = new ImageIcon("images/dice/g2.png");
//            }
//            label_p3.setIcon(icon3);
//            label_p3.setBounds(1030, 300, 46, 46);
//            paneDeadwood.add(label_p3, new Integer(3));
//            playerDice.add(label_p3);
//        }
//        if (playerCount >= 4) {
//            label_p4 = new JLabel();
//            ImageIcon icon4 = new ImageIcon("images/dice/o1.png");
//            if(flag){
//                icon4 = new ImageIcon("images/dice/o2.png");
//            }
//            label_p4.setIcon(icon4);
//            label_p4.setBounds(1030, 250, 46, 46);
//            paneDeadwood.add(label_p4, new Integer(3));
//            playerDice.add(label_p4);
//        }
//        if (playerCount >= 5) {
//            label_p5 = new JLabel();
//            ImageIcon icon5 = new ImageIcon("images/dice/p1.png");
//            if(flag){
//                icon5 = new ImageIcon("images/dice/p2.png");
//            }
//            label_p5.setIcon(icon5);
//            label_p5.setBounds(1120, 250, 46, 46);
//            paneDeadwood.add(label_p5, new Integer(3));
//            playerDice.add(label_p5);
//        }
//        if (playerCount >= 6) {
//            label_p6 = new JLabel();
//            ImageIcon icon6 = new ImageIcon("images/dice/r1.png");
//            if(flag){
//                icon6 = new ImageIcon("images/dice/r2.png");
//            }
//            label_p6.setIcon(icon6);
//            label_p6.setBounds(1120, 300, 46, 46);
//            paneDeadwood.add(label_p6, new Integer(3));
//            playerDice.add(label_p6);
//        }
//        if (playerCount >= 7) {
//            label_p7 = new JLabel();
//            ImageIcon icon7 = new ImageIcon("images/dice/v2.png");
//            label_p7.setIcon(icon7);
//            label_p7.setBounds(1120, 350, 46, 46);
//            paneDeadwood.add(label_p7, new Integer(3));
//            playerDice.add(label_p7);
//        }
//        if (playerCount == 8) {
//            label_p8 = new JLabel();
//            ImageIcon icon8 = new ImageIcon("images/dice/y2.png");
//            label_p8.setIcon(icon8);
//            label_p8.setBounds(1120, 400, 46, 46);
//            paneDeadwood.add(label_p8, new Integer(3));
//            playerDice.add(label_p8);
//        }
//    }


    private void setupMenuLabel() {
        labelMenu = new JLabel(MENU_LABEL_TEXT);
        labelMenu.setBounds(iconGameBoard.getIconWidth() + 40, 0, 100, 20);
    }
    
    public void initializeDiceIcon(String image, int rank, int player, int x, int y) {
    	ImageIcon icon = new ImageIcon(String.format(image, rank));
    	if(player == 1) {
        	label_p1 = new JLabel();
        	label_p1.setIcon(icon);
        	label_p1.setBounds(x, y, 46, 46);
        	paneDeadwood.add(label_p1, new Integer(3));
        	playerDice.add(label_p1);
    	}
    	else if(player == 2) {
    		label_p2 = new JLabel();
        	label_p2.setIcon(icon);
        	label_p2.setBounds(x, y, 46, 46);
        	paneDeadwood.add(label_p2, new Integer(3));
        	playerDice.add(label_p2);
    	}
    	else if(player == 3) {
    		label_p3 = new JLabel();
        	label_p3.setIcon(icon);
        	label_p3.setBounds(x, y, 46, 46);
        	paneDeadwood.add(label_p3, new Integer(3));
        	playerDice.add(label_p3);
    	}
    	else if(player == 4) { 
    		label_p4 = new JLabel();
        	label_p4.setIcon(icon);
        	label_p4.setBounds(x, y, 46, 46);
        	paneDeadwood.add(label_p4, new Integer(3));
        	playerDice.add(label_p4);
    	}
    	else if(player == 5) {
    		label_p5 = new JLabel();
        	label_p5.setIcon(icon);
        	label_p5.setBounds(x, y, 46, 46);
        	paneDeadwood.add(label_p5, new Integer(3));
        	playerDice.add(label_p5);
    	}
    	else if(player == 6) {
    		label_p6 = new JLabel();
        	label_p6.setIcon(icon);
        	label_p6.setBounds(x, y, 46, 46);
        	paneDeadwood.add(label_p6, new Integer(3));
        	playerDice.add(label_p6);
    	}
    	else if(player == 7) {
    		label_p7 = new JLabel();
        	label_p7.setIcon(icon);
        	label_p7.setBounds(x, y, 46, 46);
        	paneDeadwood.add(label_p7, new Integer(3));
        	playerDice.add(label_p7);
    	}
    	else {
    		label_p8 = new JLabel();
        	label_p8.setIcon(icon);
        	label_p8.setBounds(x, y, 46, 46);
        	paneDeadwood.add(label_p8, new Integer(3));
        	playerDice.add(label_p8);
    	}    	
    }

    private void initializeButtons() {
        setupActButton();
        setupRehearseButton();
        setupMoveButton();
        setupTakeButton();
        setupPromoteButton();
        setupEndButton();
    }

    private void setupActivePanel() {
    	panelActive = new JPanel();
    	panelActive.setBounds(iconGameBoard.getIconWidth() + 10, 250, 180, 180);
    	panelActive.setBackground(Color.RED);
    	panelActive.setBorder(BorderFactory.createLineBorder(Color.black));
    	ImageIcon icon = new ImageIcon("images/dice/o4.png");
    	labelActive = new JLabel(icon);
    	panelActive.add(labelActive);
    }

    public void setActive() {
 
    }

    private void setupActButton() {
        buttonAct = new JButton(ACT_BUTTON_TEXT);
        buttonAct.setBackground(Color.white);
        buttonAct.setBounds(iconGameBoard.getIconWidth() + 10, 30, 100, 20);
        buttonAct.addMouseListener(new ActButtonMouseListener());
    }

    private void setupEndButton() {
        buttonEnd = new JButton(END_BUTTON_TEXT);
        buttonEnd.setBackground(Color.white);
        buttonEnd.setBounds(iconGameBoard.getIconWidth() + 10, 180, 100, 20);
        buttonEnd.addMouseListener(new EndButtonMouseListener());
    }

    private void setupPromoteButton() {
        buttonPromote = new JButton(PROMOTE_BUTTON_TEXT);
        buttonPromote.setBackground(Color.white);
        buttonPromote.setBounds(iconGameBoard.getIconWidth() + 10, 150, 100, 20);
        buttonPromote.addMouseListener(new PromoteButtonMouseListener());
    }

    private void setupTakeButton() {
      buttonTake = new JButton(TAKE_BUTTON_TEXT);
      buttonTake.setBackground(Color.white);
      buttonTake.setBounds(iconGameBoard.getIconWidth() + 10, 120, 100, 20);
      buttonTake.addMouseListener(new TakeButtonMouseListener());
    }

    private void setupRehearseButton() {
        buttonRehearse = new JButton(REHEARSE_BUTTON_TEXT);
        buttonRehearse.setBackground(Color.white);
        buttonRehearse.setBounds(iconGameBoard.getIconWidth() + 10, 60, 100, 20);
        buttonRehearse.addMouseListener(new RehearseButtonMouseListener());
    }

    private void setupMoveButton() {
        buttonMove = new JButton(MOVE_BUTTON_TEXT);
        buttonMove.setBackground(Color.white);
        buttonMove.setBounds(iconGameBoard.getIconWidth() + 10, 90, 100, 20);
        buttonMove.addMouseListener(new MoveButtonMouseListener());
    }

    private void setupShotCounter(ArrayList<Area> takeLocations){
      // for(Area a : takeLocations){
        
      // }
    }

    public void initializeDeadwoodPane() {
        paneDeadwood = getLayeredPane();
        paneDeadwood.add(labelGameBoard, new Integer(1)); // Add the board to the lowest layer
        for(int i = 0; i < labelCards.size(); i++) {
          paneDeadwood.add(labelCards.get(i), new Integer(2));
        } // Add the card to the lower layer
        paneDeadwood.add(labelMenu, new Integer(3)); // add menu
        paneDeadwood.add(buttonAct, new Integer(3));
        paneDeadwood.add(buttonRehearse, new Integer(3));
        paneDeadwood.add(buttonMove, new Integer(3));
        paneDeadwood.add(buttonTake, new Integer(3));
        paneDeadwood.add(buttonPromote, new Integer(3));
        paneDeadwood.add(buttonEnd, new Integer(3));
        paneDeadwood.add(panelActive, new Integer(4));
    }

    public void updateCard(Area area, String image) {
    	JLabel card = null;
    	for(int i = 0; i < labelCards.size(); i++) {
    		JLabel temp = labelCards.get(i);
    		if(temp.getX() == area.getX() && temp.getY() == area.getY()) {
    			card = temp;
    			break;
    		}
    	}
    	if(card != null) {
    		try {
    			ImageIcon icon = new ImageIcon(image);
    			card.setIcon(icon);
    			getLayeredPane().setLayer(card, 2);
    		}
    		catch(Exception e) {
    			getLayeredPane().setLayer(card, 0);
    		}
    	}
    }

    public void updateMenu() {
      buttonRehearse.validate();
      buttonAct.validate();
      buttonMove.validate();
      buttonTake.validate();
      buttonPromote.validate();
      labelMenu.validate();
    }

    public void nextDice() {
        if (this.turn++ == playerDice.size()) {
            this.turn = 0;
            return;
        }
        else{

          this.turn++;
        }
    }

    public void updateActiveDice(int rank){
      ImageIcon temp = new ImageIcon();
      switch(turn){
        case 1: temp = new ImageIcon(String.format(P1DICE, rank));
                break;
        case 2: temp = new ImageIcon(String.format(P2DICE, rank));
        break;
        case 3: temp = new ImageIcon(String.format(P3DICE, rank));
        break;
        case 4: temp = new ImageIcon(String.format(P4DICE, rank));
        break;
        case 5: temp = new ImageIcon(String.format(P5DICE, rank));
        break;
        case 6: temp = new ImageIcon(String.format(P6DICE, rank));
        break;
        case 7: temp = new ImageIcon(String.format(P7DICE, rank));
        break;
        case 8: temp = new ImageIcon(String.format(P8DICE, rank));
        break;
      }
      playerDice.get(turn).setIcon(temp);
    }


    public void updatePlayerPiece(String image, int x, int y, int player) {
      JLabel active = getActive(player);
      active.setBounds(x, y, 45, 45);
      active.setIcon(new ImageIcon(image));
      active.revalidate();
    }
    
    private JLabel getActive(int player) {
    	  if(player == 1) {
    	        return label_p1;
    	      }
    	      else if(player == 2) {
    	    	  return label_p2;
    	      }
    	      else if(player == 3) {
    	    	  return label_p3;
    	      }
    	      else if(player == 4) {
    	    	  return label_p4;
    	      }
    	      else if(player == 5) {
    	    	  return label_p5;
    	      }
    	      else if(player == 6) {
    	    	  return label_p6;
    	      }
    	      else if(player == 7) {
    	        return label_p7;
    	      }
    	      else {
    	    	  return label_p8;
    	      }
    }

    public void moveActiveDice(Room r){
      JLabel currDice = playerDice.get(turn); // identify current player
      currDice.setBounds(r.getLocation().getX(), r.getLocation().getY(), 46, 46); // set bounds via Area via Room
    }

    public List<JTextField> makeTextPanel(int playerNum) {
        JPanel panel = new JPanel();
        List<JTextField> list = new ArrayList<JTextField>();
        panel.setLayout(new SpringLayout());
        panel.setPreferredSize(new Dimension(300, 300));
        for (int i = 0; i < playerNum; i++) {
            // JLabel label = new JLabel(PLAYERMSG, i+1);
            JTextField textBox = new JTextField(20);
            // label.setLabelFor(textBox);
            // panel.add(label);
            panel.add(textBox);
            list.add(textBox);
        }
        this.getContentPane().add(panel);
        this.setVisible(true);
        return list;
    }

    public List<JLabel> getDiceList(){
        return playerDice;
    }

    public JButton getActButton() {
      return buttonAct;
    }
    public JButton getRehearseButton() {
      return buttonRehearse;
    }
    public JButton getMoveButton() {
      return buttonMove;
    }
    public JButton getTakeButton() {
      return buttonTake;
    }
    public JButton getPromoteButton() {
      return buttonPromote;
    }
    public JButton getEndButton() {
      return buttonEnd;
    }


    public String getRoomInput(String[] adjacent) {
      try {
      String name = (String)JOptionPane.showInputDialog(
                          this,
                          "Adjacent Rooms:\n",
                          "Select Room to Move to:",
                          JOptionPane.QUESTION_MESSAGE,
                          null,
                          adjacent,
                          adjacent[0]);
      return name;
    }
    catch(Exception e) {
      return null;
    }
    }

    public String getTakeInput(String[] roles) {
      try {
      String name = (String)JOptionPane.showInputDialog(
                          this,
                          "Valid Roles:\n",
                          "Select Role to Take:",
                          JOptionPane.QUESTION_MESSAGE,
                          null,
                          roles,
                          roles[0]);
      return name;
    }
    catch(Exception e) {
      return null;
    }
    }

    public List<String> getNameInput(int playerNum) {
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < playerNum; i++) {
            names.add(getPlayerName(i + 1));
        }
        return names;
    }

    // Adds players to the game of Deadwood.
    private String getPlayerName(int playerNum) {
        String name = (String) JOptionPane.showInputDialog(this, "Enter Name for Player " + playerNum + ":\n",
                "Player " + playerNum + " Name", JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (name != null && name.length() > 0) {
            return name;
        }
        return retryGetPlayerName(playerNum);
    }

    public void errorMessagePopup(String message) {
      JOptionPane.showMessageDialog(this, message);
    }

    private String retryGetPlayerName(int playerNum) {
        String name = (String) JOptionPane.showInputDialog(this,
                "Enter Name for Player " + playerNum + ":\n" + "Error: Please Enter Name!\n",
                "Player " + playerNum + " Name", JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (name != null && name.length() > 0) {
            return name;
        }
        return retryGetPlayerName(playerNum);
    }


    public String getDesiredCurrency() {
      try {
      String[] options = {"Dollars", "Credits"};
      String name = (String)JOptionPane.showInputDialog(
                          this,
                          "Dollars or Credits:\n",
                          "Select Currency Option:",
                          JOptionPane.QUESTION_MESSAGE,
                          null,
                          options,
                          options[0]);
      return name;
    }
    catch(Exception e) {
      return null;
    }
    }


    public String getPromoteRankDollars(int dollars, int rank) {
      try {
        String[] options = makeOptions(dollars, rank, 0);
        return(getPromoteRank(options));
      }
    catch(Exception e) {
      return null;
    }
    }

    public String getPromoteRankCredits(int credits, int rank) {
      try {
        String[] options = makeOptions(credits, rank, 1);
        return(getPromoteRank(options));
      }
    catch(Exception e) {
      return null;
    }
    }

    private String getPromoteRank(String[] options) {
      if(options.length == 0) {
        errorMessagePopup("You can't afford a rank up!");
        return null;
      }
      String desiredRank = (String)JOptionPane.showInputDialog(
                        this,
                        "Affordable Options:\n",
                        "Choose a Rank to Promote To:",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
    return desiredRank;
    }

    private String[] makeOptions(int wealth, int rank, int credOrDoll) {
      List<String> options = new ArrayList<String>();
      int[][] cost = Controller.getUpgradeCost();
      for(int i = rank; i < 6; i++) {
        if(cost[0][credOrDoll] <= wealth) {
          options.add(Integer.toString(i));
        }
      }
      return arrayListToArray(options);
    }

    private String[] arrayListToArray(List<String> options) {
      int size = options.size();
      String[] array = new String[size];
      for(int i = 0; i < size; i++) {
        array[i] = options.get(i);
      }
      return array;
    }

    public void updateActivePanel(String image, int dollars, int credits) {
      ImageIcon icon = new ImageIcon(image);
      labelActive.setIcon(icon);
      labelActive.revalidate();
    }

}
