package view;
import java.awt.event.MouseEvent;
import controller.Controller;

public class RehearseButtonMouseListener extends BaseDeadwoodMouseListener {


    public void mouseClicked(MouseEvent e) {
        Controller.rehearseClicked();
    }
}
