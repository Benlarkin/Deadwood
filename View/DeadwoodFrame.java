package View;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.util.List;
import java.util.*;

public class DeadwoodFrame extends JFrame {
    private JLabel labelGameBoard;
    private JLabel labelCard;
    private JLabel labelPlayer;
    private JLabel labelMenu;

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
    private static final String CARD_IMAGE = "images/01.png";
    private static final String DICE_IMAGE = "images/r2.png";
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
        setupPlayerLabel();
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

    private void setupPlayerLabel() {
        labelPlayer = new JLabel();
        ImageIcon playerDiceIcon = new ImageIcon(DICE_IMAGE);
        labelPlayer.setIcon(playerDiceIcon);
        labelPlayer.setBounds(114, 227, playerDiceIcon.getIconWidth(), playerDiceIcon.getIconHeight());
        //labelPlayer.setBounds(114,227,46,46);
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
        paneDeadwood.add(labelPlayer, new Integer(3));
        paneDeadwood.add(labelMenu, new Integer(2));

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
    	for(int i = 0; i < playerNum; i++) {
    		// JLabel label = new JLabel(PLAYERMSG, i+1);
    		JTextField textBox = new JTextField(20);
    		//label.setLabelFor(textBox);
    		//panel.add(label);
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
      for(int i = 0; i < playerNum; i++) {
        names.add(getPlayerName(i+1));
      }
      return names;
    }

    // Adds players to the game of Deadwood.
    private String getPlayerName(int playerNum) {
        String name = (String)JOptionPane.showInputDialog(
                            this,
                            "Enter Name for Player " + playerNum + ":\n",
                            "Player " + playerNum + " Name",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            null);
        if(name != null && name.length() > 0) {
          return name;
        }
        return retryGetPlayerName(playerNum);
    }

    public void errorMessagePopup(String message) {
      JOptionPane.showMessageDialog(this, message);
    }

    private String retryGetPlayerName(int playerNum) {
      String name = (String)JOptionPane.showInputDialog(
                          this,
                          "Enter Name for Player " + playerNum + ":\n" +
                          "Error: Please Enter Name!\n",
                          "Player " + playerNum + " Name",
                          JOptionPane.PLAIN_MESSAGE,
                          null,
                          null,
                          null);
      if(name != null && name.length() > 0) {
        return name;
      }
      return retryGetPlayerName(playerNum);
    }
}
