package nju.zoey.java;

import javax.swing.*;
import java.awt.*;


public final class Ground extends JFrame {
    private final int OFFSET = 30;

    public Ground(int M,int N) {
        InitUI(M,N);
    }

    public void InitUI(int M,int N) {

        JButton jb1 = new JButton("1.按空格键：开始并记录战斗");
        JButton jb2 = new JButton("2.按L键：读取过去战斗录像");
        JButton jb3= new JButton("3.关闭窗口:退出游戏");
        JPanel  buttonPanel= new JPanel();

        add(buttonPanel,BorderLayout.SOUTH);
        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.add(jb1);
        buttonPanel.add(jb2);
        buttonPanel.add(jb3);
        Field field = new Field(M,N,this);
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() +  OFFSET);
        setLocationRelativeTo(null);
        setTitle("葫芦娃大战妖精");

    }
}