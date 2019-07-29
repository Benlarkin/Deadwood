package View;

import java.awt.event.MouseEvent;
import Observer.Observer;


public class PromoteButtonMouseListener extends BaseDeadwoodMouseListener {


    public void mouseClicked(MouseEvent e) {
      	Observer.promoteClicked();
    }
}
