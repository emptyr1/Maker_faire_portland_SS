import processing.core.*;
import processing.video.*;
import processing.serial.*;

import java.util.*;

public class MainApp extends PApplet {

	public static int WINDOW_WIDTH = 1920;
	public static int WINDOW_HEIGHT = 1080;
	public int NUMBER_STRIPS = 3;
	public int CURRENT_STRIP = 0;



	public static int m = 640;
	public static int n = 300;

	///////////////////////////////////////////////////////////////

	FilmStripSystem[] filmStripSystems = new FilmStripSystem[NUMBER_STRIPS];

	///////////////////////////////////////////////////////////////

	//ArrayList<String[]> strips = new ArrayList<String[]>();
	//for (int i = 0; i < filmStripSystems.length; i++){
	//	filmStripSystems = new FilmStripSystem(this, );
	//	}

	public String[] strip1 = {"coney.mp4", "kaleidescope.mp4", "kaleidescope.mp4", "stars.mp4", "stars.mp4", "kaleidescope.mp4", "coney.mp4", "kaleidescope.mp4"};
	public String[] strip2 = {"kaleidescope.mp4", "kaleidescope.mp4", "noisia.mp4", "stars.mp4", "stars.mp4", "kaleidescope.mp4", "coney.mp4", "noisia.mp4"};
	public String[] strip3 = {"noisia.mp4", "kaleidescope.mp4", "noisia.mp4", "stars.mp4", "stars.mp4", "kaleidescope.mp4", "coney.mp4", "noisia.mp4"};

	ArrayList<String[]> allStrips = new ArrayList<String[]>();

	////////////////////////////////////////////////////////////////

	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT);
		allStrips.add(strip1);
		allStrips.add(strip2);
		allStrips.add(strip3);

		for(int i = 0; i < filmStripSystems.length; i++) {

			println("initializing filmstrip system");
			filmStripSystems[i] = new FilmStripSystem(this, i * m, n, allStrips.get(i), CURRENT_STRIP);

		}

	}

	public void draw() {
		background(25);
		for(int i = 0; i < filmStripSystems.length; i++) {
			filmStripSystems[i].run(CURRENT_STRIP);
		}
		//println("framerate" + frameRate);

	}

	public void keyPressed() {
		println("key pressed-- do something");

		if(key == 'n') {  //next film strip

			if(CURRENT_STRIP <= 2) {
				CURRENT_STRIP ++;
				if(CURRENT_STRIP > 2) {
					CURRENT_STRIP = 0;
				}
			}
		} else {
			CURRENT_STRIP = 0;
		}



	}
	public void movieEvent(Movie m) {
		m.read();
	}


}

