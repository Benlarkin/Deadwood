package model;
import java.awt.*;
public class Graphic extends Globals {

   protected String background;
   protected Area location;

   // Returns the Graphic's Image.
   public String getBackground() {
      return background;
   }

   // Returns the Graphic's location.
   public Area getLocation() {
      return location;
   }
   
   public void setBackground(String s){
      this.background = s;
   }
}
