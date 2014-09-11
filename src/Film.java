
import java.util.ArrayList;

import processing.core.*;
import processing.video.Movie;


public class Film {  //individual film
	
	PApplet pparent;
	PVector location = new PVector(); 
	PVector targetLocation = new PVector();
	PVector velocity;
	String filename;
	public static int VIDEO_WIDTH = 640;
	public static int VIDEO_HEIGHT = 480;
	
	public Movie myMovie;


	public Film(PApplet _parent, String _filename, PVector _Vpos) {
		pparent = _parent;
		pparent.println("loading movie " +  _filename);
		filename = _filename;
		myMovie = new Movie(this.pparent, filename);
		myMovie.loop();
		myMovie.jump(0);
		myMovie.pause();
		location = _Vpos.get();
		targetLocation = location;
		//pparent.println(location.x + " " + location.y);
	}


	public void loopMovie(){
		myMovie.loop();
	}

	public void pauseMovie(){
		myMovie.pause();
	}

	public void display(int whichVideo, ArrayList<PVector> v) {
			float tweenAmt = 0.3f;
			location.y = (1.f- tweenAmt) * location.y + tweenAmt * targetLocation.y;
			
			//pparent.println("Now I'm on screen");
			myMovie.play();
			pparent.image(myMovie, v.get(0).x, v.get(0).y, VIDEO_WIDTH, VIDEO_HEIGHT);
			//pparent.println(location.x + " " + location.y);
		

	}
	

	public boolean onScreen() {
		float halfHeight = pparent.height * 0.5f;
		float halfMovieHeight = myMovie.height * 0.5f;
		//if ((location.y >= halfHeight -halfMovieHeight) && (location.y < halfHeight +halfMovieHeight)){
		//	return true;
		//}

		if( (location.y > 0) && (location.y < 1000) ) {
			//parent.println("Video is on-screen");
			return true;
		}
		else {

			return false;
		}
	}
	
	//called on user interaction events. called by FilmStripSystem with correct position for this slide
	public void setTargetPosition(PVector targetPos, boolean jumpToPos){
		//jumpToPos is a boolean which indicates whether or not the slide should tween to the position
		// the slide should not tween if it is being moved to the opposite side of the visible area
		
		if (jumpToPos){
			//jump immediately to the target position
			location = targetPos;
		} //otherwise, tween to the target position.	
		targetLocation = targetPos;
	}

}
