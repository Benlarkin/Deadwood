import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
public class tempdice extends JPanel{

    // JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JLabel l6;
    JLabel l7;
    JLabel l8;
    
    // ImageIcon icon1;
    ImageIcon icon2;
    ImageIcon icon3;
    ImageIcon icon4;
    ImageIcon icon5;
    ImageIcon icon6;
    ImageIcon icon7;
    ImageIcon icon8;
    
    // BufferedImage img1;
    BufferedImage img2;
    BufferedImage img3;
    BufferedImage img4;
    BufferedImage img5;
    BufferedImage img6;
    BufferedImage img7;
    BufferedImage img8;

    protected static final String D1_PATH = "./dice/b1.png";
    protected static final String D2_PATH = "./dice/c1.png";

    public static void main(String[] args){
        // initPlayerDice();
    }
    
    public static void initPlayerDice(int playerCount){
        // try{
            JLabel label1 = new JLabel(new ImageIcon("./dice/b1.png"));
            if(playerCount == 3){
                JLabel label3 = new JLabel(new ImageIcon("./dice/g1.png"));
            }
            if(playerCount == 4){
                JLabel label4 = new JLabel(new ImageIcon("./dice/o1.png"));
            }
            if(playerCount == 5){
                JLabel label5 = new JLabel(new ImageIcon("./dice/p1.png"));
            }
            if(playerCount == 6){
                JLabel label6 = new JLabel(new ImageIcon("./dice/r1.png"));
            }
            if(playerCount == 7){
                JLabel label7 = new JLabel(new ImageIcon("./dice/v1.png"));
            }
            if(playerCount == 8){
                JLabel label8 = new JLabel(new ImageIcon("./dice/y1.png"));
            }

            //add to frame
            // set location
            //set visible true
    }
}