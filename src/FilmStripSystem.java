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
	ArrayList<PVector> videoPos = new ArrayList<PVector>();


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
		for(int i = 0; i < films.length; i++) {   // 8 films
			parent.print("initialize film number: ");
			parent.println(i);

			origin = new PVector( x, 300 + parent.height*i ); // 

			videoPos.add(origin);

			films[i] = new Film( this.parent, movies[i], origin  );

		}	

		//for(int l = 0; l < videoPos.size(); l++) {
		//parent.println(Collections.max(videoPos));
		//int maxx = videoPos.indexOf(max(videoPos.toArray()));
		//}

		


	}

	public void run(int _currentStrip) {

		for(int i = 0; i < films.length; i++) { // 8 movies

			if(films[i].onScreen()) {
				//parent.println(" this particular video is on screen");
				films[i].display(i, videoPos );  //(0, 300), (0, 1440)
			} else {
				films[i].pauseMovie();
			}

		}
	}
	
	public void setSelected() {
		
	}
	
	public void getSelected() {
		
	}

	public boolean AmISelected() {
		if(isSelected) {
			return true;
		} else {
			return false;
		}
	}


	//setTargetPosition
	public void reassign(ArrayList<PVector> _videoPos) {

		for(int k = 0; k < films.length-1; k++) {
			//points[k] = videoPos.get(k);
			//targetPoints[k] = videoPos.get(k+1);

			//films[k].
		}

	}


	public void moveUp() {

		for(int j = 0; j < films.length; j++) {	

			if(videoPos.get(films.length-1).y > 299  ) {
				videoPos.get(j).y -= 1080;

				//float point = videoPos.get(j).y;
				//float targetpoint = videoPos.get(j).y - MainApp.WINDOW_HEIGHT;
				//float deltaPoint =parent.lerp(point, targetpoint, 0.3f);

				films[j].setTargetPosition(videoPos.get(j), true);

				//parent.println(videoPos.get(0));


			} else {


			}

		}

	}

	public void moveDown() {

		parent.println("previous key pressed");
		for(int j = 0; j < films.length; j++) {	

			//if(videoPos.get(films.length-1).y < -8340 ) {
			videoPos.get(j).y += 1080;
			films[j].setTargetPosition(videoPos.get(j), true);

			//}


		}

	}

	public void StripkeyPressed() {
		if(parent.key == 'n') {



		}



	}


}
