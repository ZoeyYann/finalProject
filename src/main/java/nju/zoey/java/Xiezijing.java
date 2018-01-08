package nju.zoey.java;

public class Xiezijing extends Player{
    public Xiezijing(int x,int y,Field field){
        super(x,y,field);
        type=CreatureType.Yeye;
        setImage();
    }
    public void setImage() {
        setimg(this.getClass().getClassLoader().getResource("Xiezijing.jpg"));
    }
}
