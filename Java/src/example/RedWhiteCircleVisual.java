package example;

import ie.tudublin.Visual;
import processing.core.PApplet;

public class RedWhiteCircleVisual {

	MyVisual mv;

    public RedWhiteCircleVisual(MyVisual mv)
    {
        this.mv = mv; 
    }
    
    public void render()
    {
        mv.noStroke();
        for(int i = 0 ; i < mv.bands.length ; i ++)
        {
            drawTarget(mv.random(0, mv.width), mv.random(0, mv.height), mv.lerpedBands[i] * 1f);  
        }
    	 	
    	 
    }
    
    void drawTarget(float targetX, float targetY, float targetSize) {
    	
    		mv.colorMode(PApplet.RGB);
		  mv.fill(255, 0, 0);
		  mv.ellipse(targetX, targetY, targetSize, targetSize);
		  
	
		  mv.fill(255, 255, 255);
		  mv.ellipse(targetX, targetY, targetSize*.75f, targetSize*.75f);
	
		  mv.fill(255, 0, 0);
		  mv.ellipse(targetX, targetY, targetSize/2, targetSize/2);
	}
}
