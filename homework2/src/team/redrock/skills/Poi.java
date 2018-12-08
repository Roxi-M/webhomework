package team.redrock.skills;

import team.redrock.base.DamageSkill;
import team.redrock.base.Hero;
import team.redrock.base.Skill;
import team.redrock.base.buff.Buff;
import team.redrock.base.buff.Poisoning;

public class Poi extends DamageSkill implements Poisoning {
    private static final String name="一只毒箭";
    private static final int consume=0;
    public Poi() {
        super(name, consume);
    }
    private final int damge=100;
    private  int times=3;
    @Override
    public int getTime() {
        return times;
    }
    @Override
    public void action(Hero hero) {
       hero.reduceHp(this.damge);
       System.out.println(hero.getName()+"中毒受到了"+damge+"点伤害");
    }
}
