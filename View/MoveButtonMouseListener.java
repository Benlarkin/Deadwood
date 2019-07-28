package View;
import Observer.Observer;
import java.awt.event.MouseEvent;

public class MoveButtonMouseListener extends BaseDeadwoodMouseListener {

    public void mouseReleased(MouseEvent e) {
        Observer.moveButtonPressed();
    }
}
