package team.redrock.base.buff;

import team.redrock.base.Hero;
import team.redrock.base.buff.Buff;

/**
 * @Description 眩晕的buff
 * @Author 余歌
 * @Date 2018/10/31
 **/
public interface Dizzy extends Buff {

    /**
     * 眩晕的蓝耗和时间长度有关
     * 眩晕时间没一回合的蓝耗
     */
    int aRoundConsume = 70;

    @Override
    default String getDescription(){
        return "并且眩晕了"+getTime()+"回合！";
    }

    @Override
    default void action(Hero hero) {
        hero.setCanDamage(false);
        hero.setCanCast(false);
    }

    @Override
    default boolean isDebuff() {
        return true;
    }

    @Override
    default int getConsume(){
        return getTime()*aRoundConsume;
    }
}
