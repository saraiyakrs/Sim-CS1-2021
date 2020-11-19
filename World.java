package sim;

import java.awt.Color;
import java.awt.Graphics;

public class World {
    private final int SIZE = 100;
    private final Color bg = Color.GRAY;
    //declare array of Blobs
    private Blob[] blobs = new Blob[1000];
    
    public World() {
        for (int i = 0; i < blobs.length; i++) {
            blobs[i] = new Blob();
        }
        
    }
    
    public void draw(Graphics g) {
       int[] areas = calcAreas(blobs); 
       //for (int area : areas) System.out.println(area);
        //For each loop, enhanced for loop
        for (Blob blob : blobs) {
            if (blob.getHp() <= 0) continue;
            blob.move();
            blob.collideWorldBounds(this);
            for (Blob b2 : blobs) {
                if (blob == b2) continue;
                if (b2.getHp() <=0) continue;
                blob.collide(b2);
            }
            blob.draw(g);
        }
    }
    
    private int[] calcAreas(Blob[]_blobs) {
       int[] areas = new int[_blobs.length];
       for(int i = 0; i < _blobs.length; i++) {
           Blob blob = _blobs[i];
               int area = (int)(Math.PI*Math.pow(blob.getSIZE()/2,2));
           //put area in array
           
           areas[i]= area;
       }
       return areas;
    }

    public int getSIZE() {
        return SIZE;
    }
    
}
