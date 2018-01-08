package nju.zoey.java;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

abstract public class Thing2D {

    private final int SPACE = 20;

    protected int x;
    protected int y;
    protected Image image;

    public Thing2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }
    abstract public void setImage();
    public void setimg(URL loc){
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.image=image;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}


