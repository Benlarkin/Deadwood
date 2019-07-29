package view;
import java.awt.event.MouseEvent;

import controller.Controller;

public class MoveButtonMouseListener extends BaseDeadwoodMouseListener {

    public void mouseReleased(MouseEvent e) {
        Controller.moveButtonPressed();
    }
}
