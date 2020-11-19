package sim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blob {
    //Fields
    private int x, y, vx, vy, hp, size;
    private final static int SIZE = 10, MAX_SPEED = 20;
    private final static Color COLOR = Color.RED;
    
    //Constructor
    //overloaded
    public Blob(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = (int) (Math.random()*2*MAX_SPEED) - MAX_SPEED;
        this.vy = (int) (Math.random()*2*MAX_SPEED) - MAX_SPEED;
    }
    
    //no-args constructor
    public Blob() {
        this.hp = 100;
        this.size = (int) (Math.random()*size) +2;
        this.x = (int) (Math.random()*600);
        this.y = (int) (Math.random()*600);        
        this.vx = (int) (Math.random()*2*MAX_SPEED) - MAX_SPEED;
        this.vy = (int) (Math.random()*2*MAX_SPEED) - MAX_SPEED;
    }
    
    //Methods
    public void move() {
        if (vx ==0) vx =1;
        if (vy ==0) vy =1;
        x += vx;
        y += vy;
    }
    public double getArea() {
        return Math.PI * Math.pow(size/2, 2);
    }
    
    public void draw(Graphics g) {
        if (hp < 0) hp = 0;
        else if (hp > 100) hp = 100;
        Color color = new Color(150,hp*2,0);
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
    
    public void collideWorldBounds(World w) {
        if (x > w.getSIZE() || x < 0) {
            vx *= -1;
        }
        if (y > w.getSIZE() || y < 0) {
            vy *= -1;
        }
    }
    
    public void collide(Blob other) {
        if (circleVsCircle(this,other)) {
            if (this.hp > other.hp) {
                this.hp++;
                if (this.size < 25) this.size++;
                other.hp--;
            }
            else if (this.hp < other.hp) {
                other.hp++;
                if (other.size < 25) other.size++;
                this.hp--;
                this.size--;
            }
            else {
                this.hp--;
                other.hp--;
            }
            int tempvx =(int)((this.vx * this.getArea()) / other.getArea());
            int tempvy =(int)((this.vy * this.getArea()) / other.getArea());
            this.vx = (int)((other.vx * this.getArea()) / other.getArea());
            this.vy = (int)((other.vy * this.getArea()) / other.getArea());
            other.vx = tempvx;
            other.vy = tempvy;
        }
    }
    
    private boolean circleVsCircle(Blob b1, Blob b2) {
        if (dist(b1,b2) < Blob.SIZE)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    private double dist(Blob b1, Blob b2) {
        return Math.sqrt(Math.pow(b2.x-b1.x,2)+Math.pow(b2.y-b1.y,2));
    }
    //Getters & setters

    public int getHp() {
        return hp;
    }
    
    public int getSIZE() {
        return SIZE;
    }
    
    
    
    
}