package team.redrock.base.buff;

import team.redrock.base.Hero;

/**
 * @Description 这是buff接口
 * @Author 余歌
 * @Date 2018/10/31
 **/
public interface Buff {

    /**
     * 使用技能时控制台的描述
     * @return 描述文字
     */
    String getDescription();

    /**
     * buff的持续时间
     */
    int getTime();

    /**
     * 技能中buff的蓝耗
     * @return buff中不包括伤害的蓝耗
     */
    int getConsume();

    /**
     * 如果是debuff 会加到对方身上
     * 反之buff加到自己身上
     */
    boolean isDebuff();

    /**
     * 每回合的buff效果结算
     * @param hero 要结算的英雄
     */
    void action(Hero hero);
}

