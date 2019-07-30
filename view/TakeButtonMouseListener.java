package view;
import java.awt.event.MouseEvent;

import controller.Controller;

public class TakeButtonMouseListener extends BaseDeadwoodMouseListener {

    public void mouseReleased(MouseEvent e) {
        Controller.takeButtonPressed();
    }
}
