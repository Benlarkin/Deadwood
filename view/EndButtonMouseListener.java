package view;
import java.awt.event.MouseEvent;

import controller.Controller;

public class EndButtonMouseListener extends BaseDeadwoodMouseListener {


    public void mouseClicked(MouseEvent e) {
        Controller.endCurrentTurn();
    }
}
