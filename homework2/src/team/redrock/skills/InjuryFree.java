package team.redrock.skills;

import team.redrock.base.CureSkill;
import team.redrock.base.Hero;
import team.redrock.base.Skill;
import team.redrock.base.buff.Buff;

public class InjuryFree extends CureSkill implements Buff {

    public InjuryFree(String name, int cure) {
        super(name, cure);
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getTime() {
        return 2;
    }

    @Override
    public int getConsume() {
        return 0;
    }

    @Override
    public boolean isDebuff() {
        return true ;
    }

    @Override
    public void action(Hero hero) {
        hero.setCanInjuryfree(true);
    }
}
