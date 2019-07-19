import java.util.List;

public class Trailers extends Room {
   
   // Creates a new Room with limited functionality for the players to start on.
   public Trailers(List<String> adjacent) {
      super.name = TRAILER;
      super.adjacent = adjacent;
   }
}
