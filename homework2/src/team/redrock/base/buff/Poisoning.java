package team.redrock.base.buff;

import team.redrock.base.Hero;
import team.redrock.base.Skill;

public interface Poisoning extends Buff {
    int acomumse = 30;

    @Override
    default String getDescription() {
        if(getTime()==0){
            return ",对方中毒失败";
        }
        else
        return "并且中毒了" + getTime()+"回合";
    }
    @Override
    default int getConsume(){
        return acomumse*getTime();
    }
    @Override
    default boolean isDebuff(){
       return true;
    }
}