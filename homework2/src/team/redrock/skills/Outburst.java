package team.redrock.skills;

import team.redrock.base.DamageSkill;
import team.redrock.base.Hero;

public class Outburst extends DamageSkill {
    static String name="暴击";
    public Outburst(int damage){
        super(name,damage*3);
    }
}
