package team.redrock.skills;

import team.redrock.base.Hero;
import team.redrock.base.MagicSkill;
import team.redrock.base.buff.Blinding;

public class Blind extends MagicSkill implements Blinding {
    static String name="戳眼睛";
    public Blind(int INT){
        super(name,INT);
    }

    @Override
    public int getTime() {
        return 1;
    }

    @Override
    public void action(Hero hero) {
       hero.setCanBlind(true);
    }
}
