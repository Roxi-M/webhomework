package team.redrock.heros;

import team.redrock.base.DamageSkill;
import team.redrock.base.Hero;
import team.redrock.base.Skill;
import team.redrock.skills.Blind;
import team.redrock.skills.Poi;

/**
 * @Description
 * @Author 余歌
 * @Date 2018/11/1
 **/
public class SakuraMatou extends Hero {
    private final static String NAME = "间桐樱";//你的名字
    private final static String STUID = "123456";//学号
    private final static int STR = 100;//力量
    private final static int INT = 100;//智力
    private final static int AGI = 100;//敏捷

    public SakuraMatou() {
        //在构造方法里第一行写上调用父类的构造方法
        //第一个参数是你的名字，第二个是学号，后面依次是力量、敏捷、智力。
        super(NAME, STUID, STR, AGI, INT);
        //自己搞两个技能
     /*   Skill shadowRaze = new DamageSkill("嘤嘤嘤", 50);
       super.addSkill(shadowRaze);*/
     Skill blinding=new Blind(INT);
     super.addSkill(blinding);
    }
}
