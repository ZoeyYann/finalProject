# 葫芦娃大战妖怪 __游戏说明__
## 游戏规则简介
#### 用户操作
- 运行游戏，进入游戏界面，界面下方有操作指引。
- 按下<b>Space键，战斗开始。</b>
- 按下<b>L键，可以选择文件夹重温过去的精彩战斗。</b>
#### 游戏过程
- 正义阵营：
   葫芦娃和爷爷站在左方，摆出长蛇阵。
   爷爷由于攻击力较弱站在葫芦娃后方受到保护，但也具有战斗能力。
- 邪恶阵营：
   小喽啰和蝎子精站在右方，同样摆出长蛇阵。
   蝎子精站在蝎子精和小啰啰后方受到保护，同样具有战斗能力。
- 游戏开始：
       
      双方向敌对阵营前进，每前进一次，如果和敌方距离达到阈值，发起战斗。
      战斗结果由随机概率决定，死者变灰，留在原地。
      胜者继续寻找下一个攻击目标
      

## 实现原理
#### 并发
- 每个player为一个线程，在场上行走
- 与战斗有关方法设置为synchronized，保证不会发生重复战斗
#### 文件 I/O
- 记录战斗时：利用Robot对象拷贝屏幕到一个BufferedImage对象
- 回放战斗时：利用播放线程的sleep方法<b>按顺序渲染图片，制造视频效果</b>
#### 范型
- 使用了不同的范型来存储不同阵营，以及不同对象
#### 测试
主要对Field中的方法进行测试，因为这关乎了战斗的正确性：
* 战斗双方是否有效
* 战斗开始的条件
* 战斗进行的方式
* 角色移动的正确性

***
## 类结构图
![image](http://chuantu.biz/t6/200/1515391174x-1404792931.jpg)

***
## 写在最后
- 考完笔试觉得老师出的试卷非常好，做每一道题目真的在思考，比如范型的擦除，线程安全。
- 有些题目没有回答出来，是自己复习的不够认真，但还是不后悔选这门课的～
- 大作业实现的过程是对知识的实践，尤其是测试，自动构建，上课听的一脸懵逼。
- 谢谢曹老师，余老师一学期的教导！
***