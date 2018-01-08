package nju.zoey.java;

enum COLOR {
    red,orange,yellow,green,cyan,blue,purple
}
enum RANK {
    one,two,three,four,five,six,seven
}
public class Huluwa extends Player {
    private COLOR COLOR;
    public COLOR getColor() {
        return COLOR;
    }

    Huluwa(int x,int y,Field field,COLOR COLOR) {
        super(x,y,field);
        this.COLOR = COLOR;
        type=CreatureType.Huluwa;
        setImage();
    }
    @Override
    public void setImage(){
        switch (COLOR){
            case red:
                setimg(this.getClass().getClassLoader().getResource("Hong.jpg"));break;
            case orange:
                setimg(this.getClass().getClassLoader().getResource("Cheng.jpg"));break;
            case yellow:
                setimg(this.getClass().getClassLoader().getResource("huang.jpg"));break;
            case green:
                setimg(this.getClass().getClassLoader().getResource("lv.jpg"));break;
            case cyan:
                setimg(this.getClass().getClassLoader().getResource("Qing.jpg"));break;
            case blue:
                setimg(this.getClass().getClassLoader().getResource("Lan.jpg"));break;
            case purple:
                setimg(this.getClass().getClassLoader().getResource("Zi.jpg"));
        }
    }

}


