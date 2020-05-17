package example;

import ie.tudublin.Visual;
import processing.core.PApplet;

public class ZoomSquareVisual {

	MyVisual mv;
	float z= 0f;
	
    public ZoomSquareVisual(MyVisual mv)
    {
        this.mv = mv; 
    }
    
    public void render()
    {
    	mv.stroke(0);
    	//for(int i = 0 ; i < 10 ; i ++)
        {
    		drawTarget();
        }
    	 
    }
    
    void drawTarget() {
    	
    	  mv.colorMode(PApplet.RGB);
    	  mv.fill(mv.random(0,255),mv.random(0,255),mv.random(0,255));

    	  // Translate to a point before displaying a shape there
    	  mv.translate(mv.width/2, mv.height/2, z);
    	  mv.rectMode(mv.CENTER);
    	  mv.rect(0, 0, 50, 50); 

    	  // Increment z (i.e. move the shape toward the viewer)
    	  z += 200;

    	  // Restart rectangle
    	  if (z > 2000) {
    	    z = 0;
    	  }
	}
}
