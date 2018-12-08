package team.redrock.base;

/**
 * @Description 战斗的舞台
 * @Author 余歌
 * @Date 2018/11/1
 **/
public class Stage {

    //每个技能释放的几率
    private static final int SKILL_P = 20;

    /**
     * 开始战斗
     *
     * @param left  一个英雄
     * @param right 另一个英雄
     * @return 如果是true 则left赢 否则right赢
     */
    public boolean battle(Hero left, Hero right) {
        //正数left赢 负数right赢
        int order = 1;

        //把敏捷高的放在heros[0] 低的放在heros[1]
        //然后算好每个英雄的概率什么的
        Hero[] heros = new Hero[2];

        if (left.getAgi() < right.getAgi()) {
            order = -1;
            heros[0] = right;
            heros[1] = left;
        } else {
            heros[0] = left;
            heros[1] = right;
        }

        int[] skillP = new int[2];
        int[] skillNum = new int[2];
        for (int i = 0; i < 2; i++) {
            skillNum[i] = heros[i].getSkillNum();
            skillP[i] = skillNum[i] * SKILL_P;
        }

        //开始战斗 一方血量为0或以下结束
        while (heros[0].getHp() > 0 && heros[1].getHp() > 0) {
            //如果觉得太慢 把下面这一段删掉
        /*    try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //到这里的部分↑
            System.out.println(heros[0].getName() + " hp:" + heros[0].getHp() + " mp:" + heros[0].getMp());
            System.out.println(heros[1].getName() + " hp:" + heros[1].getHp() + " mp:" + heros[1].getMp());

            for (int i = 0; i < 2; i++) {

                //回合前的buff结算
                heros[i].turn();

                //如果能放技能
                if (heros[i].isCanCast() && heros[i].isCanDamage()) {
                    int random = random(100);

                    if (random <= skillP[i]) {
                        Skill skill = heros[i].getSkill(random(skillNum[i]));
                        heros[i].cast(skill, heros[(i + 1) % 2]);
                    } else {
                        heros[i].attach(heros[(i + 1) % 2]);
                    }
                } else {
                    if (heros[i].isCanCast()) {
                        Skill skill = heros[i].getSkill(random(skillNum[i]));
                        heros[i].cast(skill, heros[(i + 1) % 2]);
                    } else if (heros[i].isCanDamage()) {
                        heros[i].attach(heros[(i + 1) % 2]);
                    } else {
                        System.out.println("【" + heros[i].getName() + "】什么都做不了");
                    }
                }

            }
        }

        if (heros[1].getHp() > 0) {
            order = order * (-1);
        }

        left.init();
        right.init();
        return order > 0;
    }

    public static int random(int limit) {
        return (int) (Math.random() * limit);
    }
}
