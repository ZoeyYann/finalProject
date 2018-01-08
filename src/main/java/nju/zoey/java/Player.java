package nju.zoey.java;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

abstract public class Player extends Creature2D implements Runnable {
    private Field field;
    private boolean isAlive = true;

    public Player(int x, int y, Field field) {
        super(x, y);
        this.field = field;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void died() {
        isAlive = false;
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        if (nx < field.getBoardWidth() && nx >= 0)
            this.setX(nx);
        if (ny < field.getBoardHeight() && ny >=0)
            this.setY(ny);
    }

    public void run() {
        try {
            //如果有一方成员全部死亡 战斗结束
            while (field.anyGoodAlive() && field.anyBadAlive()) {

                Random rand = new Random();

                //给field上锁
                synchronized (field) {
                    //如果该线程的生物体死亡，该线程结束
                    if (!this.isAlive()) return;

                    //控制行进方向，使角色朝敌人方向前进
                    if (this instanceof Huluwa || this instanceof Grandpa) {
                        this.move(rand.nextInt(150), 0);
                        for (Player bad : field.bads()) {
                            if (bad.isAlive() && field.confict(this, bad))
                                field.battleBetween(this, bad);
                        }
                    } else
                        this.move(-rand.nextInt(80), 0);
                }

                //延迟一段时间
                Thread.sleep(1500);
                //刷新页面
                this.field.repaint();

                //再次给field上锁，避免同时处理截屏的序列号
                synchronized (field) {
                    //录屏
                    try {
                        BufferedImage screenshot = (new Robot()).createScreenCapture(field.getFrame().getBounds());
                        field.serialNum++;

                        //创建当前battle目录
                        File f;
                        int battleId=2;
                        String imgDir="./Battle"+battleId; //"."代表当前目录 "/hw2"
                        f=new File(imgDir);
                        f.mkdir();

                        String name = imgDir+"/HlwScreenshot" + field.serialNum + ".jpg";
                        f= new File(name);
                        System.out.println("Save File " + name);
                        //将screenshot对象写入图像文件
                        ImageIO.write(screenshot, "jpg", f);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        } catch (InterruptedException e) {
            System.out.println("Player Over");
        }
    }
}