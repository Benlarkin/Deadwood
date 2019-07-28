package View;
import Observer.Observer;
import java.awt.event.MouseEvent;

public class TakeButtonMouseListener extends BaseDeadwoodMouseListener {

    public void mouseReleased(MouseEvent e) {
        Observer.takeButtonPressed();
    }
}
