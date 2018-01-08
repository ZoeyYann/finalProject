package nju.zoey.java;

import java.awt.*;
import java.util.ArrayList;

public class PicturePlayer implements Runnable{
    private ArrayList<Image> images = new ArrayList();
    Field field;

    PicturePlayer(ArrayList<Image> ims,Field f){
        this.images=ims;
        field=f;
    }
    @Override
    public void run(){
        for (int i = 0; i < images.size(); i++) {
            try{
                //更新播放图片
                field.setPlayingImage(images.get(i));
                System.out.println(i+"th Image playing");
                field.repaint();
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println("图片播放被打断！");
            }

        }
        System.out.println("战斗播放结束！");
    }
}
