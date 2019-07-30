package controller;
public class Area {
   private int x;
   private int y;
   private int h;
   private int w;

   // Keeps track of an object's location and 2-dimensional size for use in a GUI.
   public Area(int x, int y, int h, int w) {
      this.x = x;
      this.y = y;
      this.h = h;
      this.w = w;
   }

   // Returns the x coordinate.
   public int getX() {
      return x;
   }

   // Returns the y coordinate.
   public int getY() {
      return y;
   }

   // Returns the height.
   public int getH() {
      return h;
   }

   // Returns the width.
   public int getW() {
      return w;
   }
}
