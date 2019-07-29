package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.util.List;
import java.util.*;
import Observer.Observer;

public class DeadwoodFrame extends JFrame {
    private JLabel labelGameBoard;
    private JLabel labelCard;
    private JLabel labelPlayer;
    private JLabel labelMenu;
    private JLabel label_p1;
    private JLabel label_p2;
    private JLabel label_p3;
    private JLabel label_p4;
    private JLabel label_p5;
    private JLabel label_p6;
    private JLabel label_p7;
    private JLabel label_p8;

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

    public DeadwoodFrame() {
        super(DEADWOOD_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeLabels();
        initializeButtons();
        initializeDeadwoodPane();
    }

    private void initializeLabels() {
        setupGameBoardLabel();
        setupCardLabel();
        setupMenuLabel();
    }

    private void setupGameBoardLabel() {
        labelGameBoard = new JLabel();
        iconGameBoard = new ImageIcon(GAME_BOARD_IMAGE);
        labelGameBoard.setIcon(iconGameBoard);
        labelGameBoard.setBounds(0, 0, iconGameBoard.getIconWidth(), iconGameBoard.getIconHeight());
        setSize(iconGameBoard.getIconWidth() + 200, iconGameBoard.getIconHeight()); // Set the size of the GUI
    }

    private void setupCardLabel() {
        labelCard = new JLabel();
        ImageIcon cardIcon = new ImageIcon(CARD_IMAGE);
        labelCard.setIcon(cardIcon);
        labelCard.setBounds(20, 65, cardIcon.getIconWidth() + 2, cardIcon.getIconHeight());
        labelCard.setOpaque(true);
    }

    // SET UP Player dice in trailers at start of game
    public void initDice(int playerCount) {
        paneDeadwood = getLayeredPane();
        label_p1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("images/dice/b1.png");
        label_p1.setIcon(icon1);
        label_p2 = new JLabel();
        ImageIcon icon2 = new ImageIcon("images/dice/c1.png");
        label_p2.setIcon(icon2);
        label_p1.setBounds(1030, 400, 46, 46);
        label_p2.setBounds(1030, 350, 46, 46);
        paneDeadwood.add(label_p1, new Integer(3));
        paneDeadwood.add(label_p2, new Integer(3));
        if (playerCount >= 3) {
            label_p3 = new JLabel();
            ImageIcon icon3 = new ImageIcon("images/dice/g1.png");
            label_p3.setIcon(icon3);
            label_p3.setBounds(1030, 300, 46, 46);
            paneDeadwood.add(label_p3, new Integer(3));
        }
        if (playerCount >= 4) {
            label_p4 = new JLabel();
            ImageIcon icon4 = new ImageIcon("images/dice/o1.png");
            label_p4.setIcon(icon4);
            label_p4.setBounds(1030, 250, 46, 46);
            paneDeadwood.add(label_p4, new Integer(3));
        }
        if (playerCount >= 5) {
            label_p5 = new JLabel();
            ImageIcon icon4 = new ImageIcon("images/dice/p1.png");
            label_p5.setIcon(icon4);
            label_p5.setBounds(1120, 250, 46, 46);
            paneDeadwood.add(label_p5, new Integer(3));
        }
        if (playerCount >= 6) {
            label_p6 = new JLabel();
            ImageIcon icon4 = new ImageIcon("images/dice/r1.png");
            label_p6.setIcon(icon4);
            label_p6.setBounds(1120, 300, 46, 46);
            paneDeadwood.add(label_p6, new Integer(3));
        }
        if (playerCount >= 7) {
            label_p7 = new JLabel();
            ImageIcon icon4 = new ImageIcon("images/dice/v1.png");
            label_p7.setIcon(icon4);
            label_p7.setBounds(1120, 350, 46, 46);
            paneDeadwood.add(label_p7, new Integer(3));
        }
        if (playerCount == 8) {
            label_p8 = new JLabel();
            ImageIcon icon4 = new ImageIcon("images/dice/y1.png");
            label_p8.setIcon(icon4);
            label_p8.setBounds(1120, 400, 46, 46);
            paneDeadwood.add(label_p8, new Integer(3));
        }
    }

    private void setupMenuLabel() {
        labelMenu = new JLabel(MENU_LABEL_TEXT);
        labelMenu.setBounds(iconGameBoard.getIconWidth() + 40, 0, 100, 20);
    }

    private void initializeButtons() {
        setupActButton();
        setupRehearseButton();
        setupMoveButton();
        setupTakeButton();
        setupPromoteButton();
        setupEndButton();
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

    private void initializeDeadwoodPane() {
        paneDeadwood = getLayeredPane();
        paneDeadwood.add(labelGameBoard, new Integer(0)); // Add the board to the lowest layer
        paneDeadwood.add(labelCard, new Integer(1)); // Add the card to the lower layer
        paneDeadwood.add(labelMenu, new Integer(2)); // add menu
        paneDeadwood.add(buttonAct, new Integer(2));
        paneDeadwood.add(buttonRehearse, new Integer(2));
        paneDeadwood.add(buttonMove, new Integer(2));
        paneDeadwood.add(buttonTake, new Integer(2));
        paneDeadwood.add(buttonPromote, new Integer(2));
        paneDeadwood.add(buttonEnd, new Integer(2));
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
      int[][] cost = Observer.getUpgradeCost();
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

}
