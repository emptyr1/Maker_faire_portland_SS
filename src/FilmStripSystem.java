import java.awt.print.Printable;
import java.util.*;
import processing.app.syntax.InputHandler.insert_break;
import processing.core.*;
import processing.video.Movie;


public class FilmStripSystem  { //collection of Films

	public int MOVIES_IN_EACH_STRIP = 8;

	Film[] films = new Film[MOVIES_IN_EACH_STRIP];
	PApplet parent;
	Movie myMovie;

	PVector origin;
	PVector originMoved;

	public int currentVideoX = 0;
	public int currentVideoY = 480;

	PVector points[];
	PVector targetPoints[];

	PVector minMax = new PVector();
	int x;

	public int currentStrip;
	public ArrayList<PVector> videoPos = new ArrayList<PVector>();
	public ArrayList<PVector> videoPosCloned = new ArrayList<PVector>();


	public static String[] movies = new String[8];
	String filmnames;
	public boolean isSelected;

	public FilmStripSystem(PApplet _parent, int _x, int _y, String[] _names, boolean _isSelected) {

		parent = _parent;
		//movies = _names;
		parent.arrayCopy(_names, movies);
		parent.println("movies");
		x = _x;
		//currentStrip = _currentStrip;
		originMoved = new PVector(x,  _y + 1080);
		isSelected = _isSelected;
		parent.println(isSelected);
		videoPos.clear();
		for(int i = 0; i < films.length; i++) {   // 8 films
			parent.print("initialize film number: ");
			parent.println(i);

			origin = new PVector( x, 50 + 500*i ); // 
			
			videoPos.add(origin);

			films[i] = new Film( this.parent, movies[i], origin  );

		}	

		//for(int l = 0; l < videoPos.size(); l++) {
		//parent.println(Collections.max(videoPos));
		//int maxx = videoPos.indexOf(max(videoPos.toArray()));
		//}
		videoPosCloned = new ArrayList<PVector>(videoPos);

	}

	public void run(int _currentStrip) {

		for(int i = 0; i < films.length; i++) { // 8 movies

			if(films[i].onScreen()) {
				films[i].loopMovie();
				//parent.println(" this particular video is on screen");
				films[i].display( i );  //(0, 300), (0, 1440)
			} else {
				films[i].pauseMovie();
				//films[i].jump(0);
			}

		}
	}
	public boolean AmISelected() {
		if(isSelected) {
			return true;
		} else {
			return false;
		}
	}


	public void moveUp() {
		parent.println(videoPos);

		for(int j = 0 ; j < films.length; j++ ) {	
			//if(videoPos.get(films.length-1).y > 300  ) {
			//videoPos.get(j).y -= 500;
			float startpoint = videoPos.get(j).y;
			float targetpoint = videoPos.get(j).y - 500; 
			
			float delta = parent.lerp(startpoint, targetpoint, 0.4f);
			PVector sampleTarget = new PVector(x, delta);
			

			films[j].setTargetPosition(videoPosCloned.get(j), sampleTarget, true ); //stores targetPos in targetLocation vector
			//parent.println(videoPos.get(0));
			films[j].display(1);
			parent.println(videoPosCloned.get(j));
			videoPosCloned.get(j).y -= 500;
		}
		

	}

	public void moveDown() {

		parent.println("previous key pressed");
		for(int j = 0; j < films.length; j++) {	

			//if(videoPos.get(films.length-1).y < -8340 {	
			
			videoPos.get(j).y += 1080;
			//films[j].setTargetPosition(videoPos.get(j), true);



		}

	}
	
	public void randomize() {
		
	}


}  // end class



