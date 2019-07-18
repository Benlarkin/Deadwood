import java.util.List;

public class Trailers extends Room {
	private final String TRAILER = "trailer";
	
	public Trailers(List<String> adjacent) {
		  super.name = TRAILER;
		  super.adjacent = adjacent;
	}
}
  