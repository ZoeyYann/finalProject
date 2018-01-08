package nju.zoey.java;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Field extends JPanel {

    private Ground g;
    Ground getFrame(){ return g;}
    public int serialNum = 0; //录屏时的截屏序列号

    private final int DDL = 100; //引发battle的阈值
    public final int OFFSET = 30;

    private final int TILE_SPACE = 20;
    private final int PLAYER_SPACE = 85; // Size of Playes

    private ArrayList tiles = new ArrayList();

    //Players
    private ArrayList<Player> theGood = new ArrayList();
    private ArrayList<Player> theBad = new ArrayList();
    public ArrayList<Player> goods() {
        return this.theGood;
    }
    public ArrayList<Player> bads() {
        return this.theBad;
    }

    //判断每个阵营中是否还有活着的player
    synchronized boolean anyGoodAlive() {
        for (Player g : theGood) if (g.isAlive()) return true;
        return false;
    }

    synchronized boolean anyBadAlive() {
        for (Player b : theBad) if (b.isAlive()) return true;
        return false;
    }

    private Image ImgPlayed;
    void setPlayingImage(Image im){ ImgPlayed=im; }

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    public int getBoardWidth() {
        return this.w;
    }
    public int getBoardHeight() {
        return this.h;
    }

    public Field(int M, int N,Ground g) {
        this.g=g;
        addKeyListener(new TAdapter(this));
        setFocusable(true);
        initBackground(M, N);
        initPlayers();
    }

    public final void initBackground(int M, int N) {
        // M * N grid
        int x = 0;
        int y = 0;
        this.h = (M + 1) * PLAYER_SPACE;
        this.w = (N + 1) * PLAYER_SPACE;

        Tile a;
        while (y < h) {
            x = 0;
            while (x < w) {
                a = new Tile(x, y); //逐行新建砖块
                tiles.add(a); //会copy，所以可以用同一个引用a来朝ArrayList 传值
                x += TILE_SPACE;
            }
            y += TILE_SPACE;
        }

    }

    // 创建player实体
    void initPlayers() {

        theGood.add(new Huluwa(0 + OFFSET + PLAYER_SPACE, 0 + OFFSET, this, COLOR.red));
        theGood.add(new Huluwa(0 + OFFSET + PLAYER_SPACE, 0 + OFFSET + PLAYER_SPACE, this, COLOR.orange));
        theGood.add(new Huluwa(0 + OFFSET + PLAYER_SPACE, 0 + OFFSET + PLAYER_SPACE * 2, this, COLOR.yellow));
        theGood.add(new Huluwa(0 + OFFSET + PLAYER_SPACE, 0 + OFFSET + PLAYER_SPACE * 3, this, COLOR.green));
        theGood.add(new Huluwa(0 + OFFSET + PLAYER_SPACE, 0 + OFFSET + PLAYER_SPACE * 4, this, COLOR.cyan));
        theGood.add(new Huluwa(0 + OFFSET + PLAYER_SPACE, 0 + OFFSET + PLAYER_SPACE * 5, this, COLOR.blue));
        theGood.add(new Huluwa(0 + OFFSET + PLAYER_SPACE, 0 + OFFSET + PLAYER_SPACE * 6, this, COLOR.purple));
        theGood.add(new Grandpa(0 + OFFSET, 0 + OFFSET + PLAYER_SPACE * 3, this));

        int nLouo = 6;
        theBad.add(new Shejing(w - OFFSET * 2, 0 + OFFSET + PLAYER_SPACE * 3, this));
        theBad.add(new Xiezijing(w - OFFSET * 2 - PLAYER_SPACE, 0 + OFFSET + PLAYER_SPACE * 3, this));
        for (int i = 0; i <= nLouo; i++)
            if (i != 3)
                theBad.add(new XIaolouluo(w - OFFSET * 2 - PLAYER_SPACE, 0 + OFFSET + PLAYER_SPACE * i, this));

    }

    // 绘制世界中的事物
    public void paintWorld(Graphics g) {

        g.setColor(new Color(222, 250, 168));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList<Thing2D> world = new ArrayList<>();
        world.addAll(tiles);
        world.addAll(theGood);
        world.addAll(theBad);

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = world.get(i);//这里用泛型避免了强制类型转换

            if (item instanceof Player && !((Player) item).isAlive()) {

                //给死亡生物体上覆盖灰色
                Image img = item.getImage();
                int w = img.getWidth(this);
                int h = img.getHeight(this);

                g.setColor(new Color(43, 5, 19, 128));
                g.drawImage(img, item.x(), item.y(), this);
                g.fillRect(item.x(), item.y(), w, h);
                g.drawString("Died", item.x(), item.y());

            } else
                g.drawImage(item.getImage(), item.x(), item.y(), this);
        }
        //如果是回放模式 播放当前帧的图片
        g.drawImage(ImgPlayed,0,0, this);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);//一些基本组件的绘制
        paintWorld(g);
    }


    class TAdapter extends KeyAdapter {
        private  Field field;
        public TAdapter(Field f){
            field=f;
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                System.out.println("Game Start!");
                ExecutorService exec = Executors.newCachedThreadPool();
                for (Player good : theGood)
                    exec.execute(good);
                for (Player bad : theBad)
                    exec.execute(bad);

            }
            if (key == KeyEvent.VK_L) {
                //战斗回放
                JFileChooser fc = new JFileChooser(".");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.setDialogTitle("选择回放文件夹(Battle开头)");
                int flag = 1;
                String path = "";

                try {
                    flag = fc.showOpenDialog(null);
                } catch (HeadlessException head) {
                    System.out.println("打开回放文件错误！");
                }

                tiles.clear();
                theBad.clear();
                theGood.clear();

                if (flag == JFileChooser.APPROVE_OPTION) {
                    //获得该文件
                    File f = fc.getSelectedFile();
                    path = f.getPath();

                }

                //读取该目录下的图片

                File dir = new File(path);
                String[] filesName = dir.list();
                ArrayList<Image> ims=new ArrayList<>();

                for (int i = 1; i <= filesName.length; i++) {
                    try{

                        File temp = new File(path + "/HlwScreenshot" + i + ".jpg");
                        ims.add(ImageIO.read(temp));  //将读取的图片放入集合中
                    } catch (IOException e2){
                        System.out.println(e2);
                    }
                }

                //开启播放图片线程
                new Thread(new PicturePlayer(ims,this.field)).start();

            }
        }
    }

    public boolean confict(Player p1, Player enemy) {
        return (Math.abs(p1.x - enemy.x) <= DDL && Math.abs(p1.y - enemy.y) <= DDL);
    }

    public void battleBetween(Player p1, Player enemy) {
        Random rand = new Random();
        int goodLiveRate = rand.nextInt(100);
        if (p1 instanceof Huluwa || p1 instanceof Grandpa) {
            if (goodLiveRate > 50)
                enemy.died();
            else
                p1.died();
        }
        else throw new IllegalArgumentException();
    }

}

