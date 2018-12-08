package team.redrock.base;

/**
 * @Description 治疗类技能
 * @Author 余歌
 * @Date 2018/10/31
 **/
public class CureSkill extends Skill {
    private int cure;

    protected CureSkill(String name,int cure){
        super(name,cure/2);
        this.cure=  cure;
    }

    @Override
    public final int cast(Hero hero) {
        return cure;
    }
}
