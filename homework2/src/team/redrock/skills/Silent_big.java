package team.redrock.skills;

import team.redrock.base.DamageSkill;
import team.redrock.base.buff.Silent;

public class Silent_big extends DamageSkill implements Silent {
    static String name="消声膜波";
    static int damage=300;
    public Silent_big(){
        super(name,damage);
    }
    int times=(int)(Math.random()*3);
    @Override
    public int getTime() {
        return times;
    }

}
