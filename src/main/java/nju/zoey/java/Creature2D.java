package nju.zoey.java;

import java.awt.*;

enum CreatureType{ Huluwa,Shejing,Yeye,Xiezijing,Loluo}
public  abstract class Creature2D extends Thing2D{
    protected CreatureType type; //生物种类有5种

    public Creature2D(int x, int y) {
        super(x,y);
    }

    public CreatureType getType(){ return this.type; }

    public Image getImage() {
        return this.image;
    }


}
