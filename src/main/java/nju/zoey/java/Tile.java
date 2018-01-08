package nju.zoey.java;

import java.net.URL;

public class Tile extends Thing2D {

    public Tile(int x, int y) {
        super(x, y);

        this.setImage();
    }
    public void setImage(){
        URL loc = this.getClass().getClassLoader().getResource("tile.jpg");
        setimg(loc);
    }
}