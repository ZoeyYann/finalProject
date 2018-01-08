//public class CraneFormation extends Formation {
//    public CraneFormation(Creature member,Creature leader) {
//        super(7, 4);
//        String matter=member.getShape();
//        for (int i = 0; i < 3; i++) {
//            content[i][i] = matter;
//        }
//        for (int i = 4; i < 7; i++) {
//            content[i][2 - i % 4] = matter;
//        }
//        content[3][3]=leader.getShape();
//    }
//}