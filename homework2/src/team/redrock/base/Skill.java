package team.redrock.base;

import team.redrock.base.Hero;

import java.util.Map;

/**
 * @Description
 * @Author 余歌
 * @Date 2018/10/31
 **/
public abstract class Skill {

    private final String name;
    private final int consume;//蓝耗

    protected Skill(String name, int consume) {
        this.name = name;
        this.consume = consume;
    }

    public int getConsume() {
        return consume;
    }

    public String getName() {
        return this.name;
    }

    //返回值是对释放者的血量加减
    public abstract int cast(Hero hero);


}
