package example;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PApplet;
import processing.core.PFont;

public class ColorsGame extends MyVisual {

	public static void main(String[] args) {
		String[] processingArgs = { "ColorsGame" };
		ColorsGame mySketch = new ColorsGame();
		PApplet.runSketch(processingArgs, mySketch);
	}

	public void settings() {
		size(1024, 500, P3D);
	}
	

	boolean isGameStarted = false;
	boolean isGamePaused = false;
	int round = 1;
	List<Circle> circles = new ArrayList<Circle>();
	List<Circle> whiteCircles = new ArrayList<Circle>();
	int colorCirclesSize = 15;
	int whiteCirclesSize = 3;
	int whiteCirclesSpeed = 1;
	PFont f = null;
	
	public void setup() {
		
		startMinim();
        
        // Call loadAudio to load an audio file to process 
        loadAudio("heroplanet.mp3");        
        
        // Call this instead to read audio from the microphone
        startListening(); 
        
		f = createFont("Arial", 16, true);
		
		wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
        rwc = new RedWhiteCircleVisual(this);
        zsv = new ZoomSquareVisual(this);
        sv = new SpinVisual();
	}
	
	public void keyPressed()
    {
		System.out.println("Key - "+key);
        if (key == ' ')
        {
        	if(isGameStarted) {
        		isGamePaused = !isGamePaused;
				
				  if(isGamePaused) as.stop(); 
				  if(!isGamePaused) as.trigger();
				 
        	}else {
        		as.stop();
                as.trigger();
	        	isGameStarted = true;
	        	generateColorCircles();
	    		generateWhiteCircles();
        	}
        }
    }

	/**
	 * generate Color circles with constant speed
	 */
	private void generateColorCircles() {
		clear();
		circles.clear();
		for (int i = 0; i < colorCirclesSize; i++) {
			Color c = new Color(random(0, 255), random(0, 255), random(0, 255));
			circles.add(new Circle(this, "", random(width), random(height), random(-5, 5), random(-5, 5), c));
		}
	}

	/**
	 * generate white circles with increasing speed based on rounds.
	 */
	private void generateWhiteCircles() {
		for (int i = 1; i <= whiteCirclesSize; i++) {
			Color c = new Color(255, 255, 255);
			whiteCircles.add(new Circle(this, ""+i, random(width), random(height), random(-5, 5), random(-5, 5), c));
		}
	}

	public void mousePressed() {
		if(isGameStarted && !isGamePaused) {
			System.out.println("Clicked on " + mouseX + "," + mouseY);
			
			Iterator<Circle> itr = whiteCircles.iterator();
			if (itr.hasNext()) {
				Circle circle = itr.next();
				float d = PApplet.dist(mouseX, mouseY, circle.x, circle.y);
				System.out.println("d - " + d);
				if (d < 50) {
					System.out.println("True");
					itr.remove();
				}
				
			}
			
			if(whiteCircles.size() == 0) {
				round = round+1;
				//whiteCirclesSpeed += 1;
				generateWhiteCircles();
				generateColorCircles();
			}
		}
	}
	
	

	public void draw() {
		background(250);
        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        //calculateAverageAmplitude();        
       
		if(isGamePaused) {
			String roundTxt = "Paused";
			fill(0);
			textFont(f, 50);
			text(roundTxt, width/2, height/2);
			textAlign(CENTER, CENTER);
		}
		
		if(!isGameStarted) {
			String roundTxt = "Press 'space bar' to start the game.";
			fill(0);
			textFont(f, 30);
			text(roundTxt, width/2, height/2);
			textAlign(CENTER, CENTER);
		}else {
			String roundTxt = "Round : " + str(round);
			fill(0);
			textFont(f, 20);
			text(roundTxt, 800, 50);
			textAlign(CENTER, CENTER);
			
			/*
			 * String speedTxt = "Speed : x" + str(whiteCirclesSpeed); fill(0); textFont(f,
			 * 20); text(speedTxt, 800, 70); textAlign(CENTER, CENTER);
			 */
			
			
	       // rwc.render();
	        //sv.render();
		}
		wf.render();
        abv.render();
		//Color circles
        
        	//pick the 3rd band
        	float spd = lerpedBands[2];
            for (Circle c : circles) {
    			if(!isGamePaused)c.move(spd, spd);
    			c.display(40);
    		}
    		
    		//White circles
    		for (Circle c : whiteCircles) {
    			if(!isGamePaused)c.move(spd,spd);
    			c.display(50);
    		}
		
		
	}

}

class Circle {
	private PApplet sketch;
	String no;
	float x;
	float y;
	float xSpeed;
	float ySpeed;
	Color c;
	

	Circle(PApplet sketch, String no, float x, float y, float xSpeed, float ySpeed, Color c) {
		this.sketch = sketch;
		this.no = no;
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.c = c;

	}

	void move(float xS, float yS) {
		x += (xSpeed * xS);
		if (x < 0 || x > sketch.width) {
			xSpeed *= -1;   
		}

		y += (ySpeed * yS);
		if (y < 0 || y > sketch.height) {
			ySpeed *= -1;
		}
		System.out.println("X - "+ xSpeed);
		System.out.println("Y - "+ ySpeed);
	}

	void display(int size) {
		sketch.fill(0);
		sketch.ellipse(x, y, size+5, size+5);
		
		sketch.fill(c.r, c.g, c.b);
		sketch.ellipse(x, y, size, size);
		
		sketch.fill(0);
		PFont f = sketch.createFont("Arial", 16, true);
		sketch.textFont(f, 20);
		sketch.text(no,x,y);
		sketch.textAlign(sketch.CENTER, sketch.CENTER);
	}

	public boolean clicked() {
		float d = PApplet.dist(sketch.mouseX, sketch.mouseY, x, y);
		System.out.println("d - " + d);
		if (d < 50) {
			System.out.println("True");
			return true;
		}
		return false;
	}
	
}


