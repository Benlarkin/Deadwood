package view;

import java.awt.event.MouseEvent;
import controller.*;

public class ActButtonMouseListener extends BaseDeadwoodMouseListener {

    public void mouseClicked(MouseEvent e) {
        Controller.actClicked();
    }
}
