package nju.zoey.java;

public class Shejing extends Player {
    public Shejing(int x,int y,Field field){
        super(x,y,field);
        type=CreatureType.Yeye;
        setImage();
    }
    public void setImage() {
        setimg(this.getClass().getClassLoader().getResource("Shejing.jpg"));
    }
}
