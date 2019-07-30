package view;

import java.awt.event.MouseEvent;

import controller.Controller;


public class PromoteButtonMouseListener extends BaseDeadwoodMouseListener {


    public void mouseClicked(MouseEvent e) {
      	Controller.promoteClicked();
    }
}
