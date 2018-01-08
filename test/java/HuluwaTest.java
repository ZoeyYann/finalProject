import nju.zoey.java.*;
import org.junit.Test;


public class HuluwaTest {

    Ground ground = new Ground(10,10);
    Field field =new Field(10,10,ground);

    @Test
    public void testPlayerMove(){
        Grandpa p1= new Grandpa(0,0,field);
        int _x=3,_y=6;
        p1.move(_x,_y);
        assert (p1.x()==3 && p1.y()==6);
    }

    @Test
    public void testBattleBegin(){
        Grandpa p1= new Grandpa(0,0,field);
        XIaolouluo p2= new XIaolouluo(0,5,field);
        assert (field.confict(p1,p2)==true);
    }
    //测试战斗双方有效性
    @Test(expected = IllegalArgumentException.class)
    public void testBattleRole(){
        XIaolouluo p1= new XIaolouluo(0,0,field);
        XIaolouluo p2= new XIaolouluo(0,5,field);
        field.battleBetween(p1,p2);
    }
    //测试战斗结果
    @Test
    public void testBattleResult(){
        Grandpa p1= new Grandpa(0,0,field);
        XIaolouluo p2= new XIaolouluo(0,5,field);
        field.battleBetween(p1,p2);
        assert (p1.isAlive() ^ p2.isAlive());
    }



}
