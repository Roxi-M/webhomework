package team.redrock.skills;

import team.redrock.base.DamageSkill;
import team.redrock.base.buff.Dizzy;

public class CrowFlying extends DamageSkill implements Dizzy {

    private static final String NAME = "乌鸦坐飞机";
    private static final int DAMAGE = 200;
    private static final int DIZZY_TIME;

    static {
        DIZZY_TIME = 100000;
    }

    public CrowFlying() {
        super(NAME, DAMAGE);
    }

    @Override
    public int getTime() {
        return DIZZY_TIME;
    }

}