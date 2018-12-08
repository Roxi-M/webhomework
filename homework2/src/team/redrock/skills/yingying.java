package team.redrock.skills;

import team.redrock.base.DamageSkill;
import team.redrock.base.Hero;
import team.redrock.base.Skill;
import team.redrock.base.buff.Buff;
import team.redrock.base.buff.Dizzy;

public  class yingying extends DamageSkill implements  Buff{
    public yingying(){
        super("仓仓子炮弹",0);
    }

    @Override
    public String getDescription() {
        return "并且眩晕了"+getTime()+"回合！";
    }

    @Override
    public int getTime() {
        return 10000000;
    }

    @Override
    public boolean isDebuff() {
        return true;
    }

    @Override
    public void action(Hero hero) {
             hero.setCanDamage(false);
             hero.setCanCast(false);
    }
}
