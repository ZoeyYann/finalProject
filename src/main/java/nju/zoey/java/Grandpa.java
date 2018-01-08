package nju.zoey.java;

public class Grandpa extends Player{
    public Grandpa(int x,int y,Field field){
        super(x,y,field);
        type=CreatureType.Yeye;
        setImage();
    }
    public void setImage() {
        setimg(this.getClass().getClassLoader().getResource("Yeye.jpg"));
    }
}
