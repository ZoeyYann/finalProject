package nju.zoey.java;

public class XIaolouluo extends Player {
    public XIaolouluo(int x,int y,Field field){
        super(x,y,field);
        type=CreatureType.Yeye;
        setImage();
    }
    public void setImage() {
        setimg(this.getClass().getClassLoader().getResource("Loluo.jpeg"));
    }
}
