import processing.core.*;
import processing.video.*;
import processing.serial.*;

import java.util.*;

public class MainApp extends PApplet {

	public static int WINDOW_WIDTH = 1920;
	public static int WINDOW_HEIGHT = 1080;
	public int NUMBER_STRIPS = 3;
	public int CURRENT_STRIP = 0;

	public boolean isitSelected = true;

	public static int m = 640;
	public static int MARGIN_TOP = 300;
	public int t = 0;

	///////////////////////////////////////////////////////////////

	FilmStripSystem[] filmStripSystems = new FilmStripSystem[NUMBER_STRIPS];

	///////////////////////////////////////////////////////////////

	//ArrayList<String[]> strips = new ArrayList<String[]>();


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
			if(i == 1)  {
				isitSelected = true;
				filmStripSystems[i] = new FilmStripSystem(this, i * m, MARGIN_TOP, allStrips.get(i), isitSelected); //m = i*640  n=300

			}
			else {
				isitSelected = false;
				filmStripSystems[i] = new FilmStripSystem(this, i * m, MARGIN_TOP, allStrips.get(i), isitSelected); //m = i*640  n=300

			}
			println(isitSelected);

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

		if(key == 'd') {
			println(" d key presseed");
			for (int m = 0; m < filmStripSystems.length; m++){
				if(filmStripSystems[m].AmISelected())
					//println(filmStripSystems[m].AmISelected());
					filmStripSystems[m].moveUp();
			}

		}

		////////////////////////////////////////////////////////////////////////////////

		if(key == 'u') {
			for (int m = 0; m < filmStripSystems.length; m++){
				if(filmStripSystems[m].AmISelected())
					filmStripSystems[m].moveDown();
			}

		}
		///////////////////////////////////////////////////////////////////////////////////	
		if(key == 'l') {

		}

		if(key == 'r') {

			for (int m = 0; m < filmStripSystems.length; m++){

				t = (t + 1) % filmStripSystems.length;

				if(filmStripSystems[m].AmISelected()) {
					filmStripSystems[m].isSelected = false;
					filmStripSystems[m+1].isSelected = true;
				}
				//filmStripSystems[m].moveDown();
			}

		}



	}


	public void movieEvent(Movie m) {
		m.read();
	}


	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "MainApp" });
	}

}



