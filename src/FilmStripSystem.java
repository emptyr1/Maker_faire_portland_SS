import java.awt.print.Printable;
import java.util.ArrayList;

import processing.core.*;
import processing.video.Movie;


public class FilmStripSystem  { //collection of Films

	public int MOVIES_IN_EACH_STRIP = 8;

	Film[] films = new Film[MOVIES_IN_EACH_STRIP];
	PApplet parent;
	Movie myMovie;
	PVector origin;

	public int currentVideoX = 0;
	public int currentVideoY = 480;

	PVector points[];
	PVector targetPoints[];

	public int currentStrip;
	public boolean isSelected = false;
	ArrayList<PVector> videoPos = new ArrayList<PVector>();
	PVector originMoved;

	public static String[] movies = new String[8];
	String filmnames;

	public FilmStripSystem(PApplet _parent, int _x, int _y, String[] _names, int _currentStrip) {

		parent = _parent;
		//movies = _names;
		parent.arrayCopy(_names, movies);
		parent.println("movies");
		currentStrip = _currentStrip;
		originMoved = new PVector(_x,  _y + 1080);

		for(int i = 0; i < films.length; i++) {   // 8 films
			parent.print("initialize film number: ");
			parent.println(i);

			origin = new PVector( _x, (parent.width*i) + 300 );
			
			videoPos.add(origin);

			films[i] = new Film( this.parent, movies[i], origin  );

		}	
		//parent.println(videoPos.get(1));

		for(int k = 0; k < films.length-1; k++) {
			//points[k] = videoPos.get(k);
			//targetPoints[k] = videoPos.get(k+1);
		}

	}

	public void run(int _currentStrip) {

		//parent.println(_currentStrip);
		
		if(parent.mousePressed) {
			films[0].setTargetPosition(originMoved, true);
		}

		for(int i = 0; i < films.length; i++) { // 8 movies

			if(films[i].onScreen()) {
				//parent.println(" this particular video is off screen");
				films[i].display(i, videoPos );  //(0, 480), (0, 1440)
			} else {
				films[i].pauseMovie();
			}

		}
	}


	//setTargetPosition
	public void reassign(ArrayList<PVector> _videoPos) {

	}


}
