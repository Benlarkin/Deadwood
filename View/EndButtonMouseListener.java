package View;
import Observer.Observer;
import java.awt.event.MouseEvent;

public class EndButtonMouseListener extends BaseDeadwoodMouseListener {


    public void mouseClicked(MouseEvent e) {
        Observer.endCurrentTurn();
    }
}
