
import java.util.ArrayList;

import processing.core.*;
import processing.video.Movie;


public class Film {  //individual film

	PApplet pparent;
	public PVector location = new PVector(); 
	public PVector targetLocation = new PVector();
	PVector velocity;
	String filename;
	public static int VIDEO_WIDTH = 640;
	public static int VIDEO_HEIGHT = 480;
	boolean isPlaying;

	public Movie myMovie;


	public Film(PApplet _parent, String _filename, PVector _Vpos) {
		pparent = _parent;
		pparent.println("loading movie " +  _filename);
		filename = _filename;
		myMovie = new Movie(this.pparent, filename);
		myMovie.loop();
		myMovie.jump(0);
		myMovie.pause();
		isPlaying = false;
		location = _Vpos.get();
		targetLocation = location.get();
		pparent.println("location ->" + location.x + " " + location.y);
	}


	public void loopMovie(){
		if (!isPlaying){
			pparent.println("film " + filename + "is onscreen!");

			isPlaying = true;
			myMovie.play();
		}
	}
	public void pauseMovie(){
		myMovie.pause();
		isPlaying = false;
		//myMovie.jump(0);
	}

	public void display(int whichVideo) {
		//pparent.println("location ->" + location.x + " " + location.y);

		float tweenAmt = 0.04f;
		//location.y = (1.f- tweenAmt) * location.y + tweenAmt * targetLocation.y;

		location.lerp(targetLocation.x, targetLocation.y, 0.0f, 0.3f);
		pparent.image(myMovie, location.x, location.y, VIDEO_WIDTH, VIDEO_HEIGHT);


	}


	public boolean onScreen() {
		/*	float halfHeight = MainApp.WINDOW_HEIGHT * 0.5f;
		float halfMovieHeight = VIDEO_HEIGHT * 0.5f;
		float minOnscreenY = halfHeight - halfMovieHeight;
		float maxOnscreenY = halfHeight + halfMovieHeight;
		float minVideoY = location.y;
		float maxVideoY = location.y + VIDEO_HEIGHT;

		if ( (maxVideoY >= minOnscreenY) && (minVideoY <= maxOnscreenY) ){
			return true;

		}else{
			return false;
		}

		 */

		if( (location.y > -300) && (location.y + 400 < MainApp.WINDOW_HEIGHT) ) {
			return true;
		}else{
			return false;
		}

	}

	//called on user interaction events. called by FilmStripSystem with correct position for this slide
	public void setTargetPosition(PVector _loc, PVector targetPos, boolean jumpToPos){
		location = _loc.get();
		targetLocation = targetPos.get();
		//pparent.println(targetLocation);
	}

}
