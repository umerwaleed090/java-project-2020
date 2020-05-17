package example;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PApplet;

public class SpinVisual extends Visual{

	 Rotater[] rotaters;

   
    public static void main(String[] args) {
		String[] processingArgs = { "ColorsGame" };
		SpinVisual mySPin = new SpinVisual();
		PApplet.runSketch(processingArgs, mySPin);
	}

	public void settings() {
		size(750, 750);
		
	}
	
	public void setup() {
        rotaters = new Rotater[25];
		
		  // Rotaters are made randomly
		for(int i = 0 ; i < rotaters.length ; i ++)
		{
			  Color c = new Color(random(0, 255), random(0, 255), random(0, 255));
				
		    rotaters[i] = new Rotater(random(width), random(height), random(-0.1f,0.1f), random(48),c);
		  }
        
	}
	
	void render() {
		
		
	}

	public void draw() {
		 background(255);
		 
	  // All Rotaters spin and are displayed
	  for (int i = 0; i < rotaters.length; i++ ) {
			
	    rotaters[i].spin();
	    rotaters[i].display();
	  }
	}
	
	class Rotater {
		  float x, y;   // x,y location
		  float theta;  // angle of rotation
		  float speed;  // speed of rotation
		  float w;      // size of rectangle
		  Color c;

		  Rotater( float tempX, float tempY, float tempSpeed, float tempW, Color c) {
		    x = tempX;
		    y = tempY;
		    // Angle is always initialized to 0
		    theta = 0; 
		    speed = tempSpeed;
		    w = tempW;
		    this.c= c;
		  }

		  // Increment angle
		  void spin() {
		    theta += speed;
		  }

		  // Display rectangle
		  void display() {
			rectMode(CENTER);
			colorMode(PApplet.RGB);
		    stroke(0);
		    fill(c.r, c.g, c.b);
		    // pushMatrix() and popMatrix() are called inside the class' display() method. 
		    // This way, every Rotater object is rendered with its own independent translation and rotation!
		    pushMatrix(); 
		    translate(x, y);
		    rotate(theta);
		    rect(0, 0, w, w);
		    popMatrix();
		  }
		}

}


