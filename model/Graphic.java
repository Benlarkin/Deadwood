package model;
import java.awt.*;
public class Graphic extends Globals {

   protected Image background;
   protected Area location;

   // Returns the Graphic's Image.
   public Image getBackground() {
      return background;
   }

   // Returns the Graphic's location.
   public Area getLocation() {
      return location;
   }
}
