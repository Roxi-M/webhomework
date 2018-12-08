package team.redrock.base;

import team.redrock.base.buff.Buff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 英雄的基类 你应该继承这个类
 * @Author 余歌
 * @Date 2018/10/31
 **/
public abstract class Hero {

    //力量 血量等于力量*19
    private int strength;

    //智力 蓝量等于智力*13
    private final int intelligence;

    //敏捷 普攻伤害等于敏捷 敏捷高的一方先行动
    private final int agility;

    //你的名字
    private final String name;

    //你的学号
    private final String stuid;

    //你身上的buff的集合
    private Map<Buff, Integer> buff;

    //能否放技能(沉默)
    private boolean canCast = true;

    //能否普攻
    private boolean canDamage = true;

    //你的血量
    private int hp;

    //你的蓝量
    private int mp;

    //你的普攻伤害
    private int damage;

    //你的技能 最多两个
    private List<Skill> skills;

    private boolean canBlind=false;

    //下面九个方法是封装
    boolean canInjuryfree=false;
    public void setCanInjuryfree(boolean canInjuryfree){this.canInjuryfree=canInjuryfree;}
    public boolean isInjuryFree(){return canInjuryfree;}
    public boolean isCanCast() {
        return canCast;
    }

    public void setCanCast(boolean canCast) {
        this.canCast = canCast;
    }

    public boolean isCanDamage() {
        return canDamage;
    }

    public boolean iscanBlind(){return canBlind;}

    public void setCanBlind(boolean canBlind){
        this.canBlind=canBlind;
    }
    public void setCanDamage(boolean canDamage) {
        this.canDamage = canDamage;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getAgi() {
        return agility;
    }

    public int getSkillNum() {
        return skills.size();
    }

    public String getName() {
        return this.name;
    }

    /**
     * 构造方法 你的人物里应该调用父类的这个构造方法
     *
     * @param name         你的名字
     * @param stuid        你的学号
     * @param strength     你的力量
     * @param agility      你的敏捷
     * @param intelligence 你的智力
     */
    protected Hero(String name, String stuid, int strength, int agility, int intelligence) {

        if (strength + intelligence + agility > 500) {
            throw new RuntimeException("三维超过了500！");
        }

        this.stuid = stuid;
        this.name = name;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.hp = strength * 19;
        this.mp = intelligence * 13;
        this.damage = agility;
        skills = new ArrayList<>();
        buff = new HashMap<>();
    }

    //加buff
    public void addBuff(Buff b) {
        buff.put(b, b.getTime());
    }

    //加技能
    protected void addSkill(Skill skill) {
        if (skills.size() < 2) {
            skills.add(skill);
        } else {
            throw new RuntimeException("技能不能超过两个！");
        }
    }

    //得到一个技能
    public Skill getSkill(int num) {
        if (num >= skills.size()) {
            throw new RuntimeException("拿错啦");
        }
        return skills.get(num);
    }

    //每打完一轮之后 所有英雄回满血蓝
    public void init() {
        this.hp = strength * 19;
        this.mp = intelligence * 13;
        this.buff.clear();
    }

    /**
     * 释放技能
     *
     * @param skill 要释放的技能
     * @param hero  你的对手
     */
    public final void cast(Skill skill, Hero hero) {
        //蓝耗够的情况下
        if (this.mp >= skill.getConsume()) {
            this.mp -= skill.getConsume();

            System.out.print("【" + this.name + "】对【" + hero.getName() + "】使用了【" + skill.getName() + "】造成了" + skill.getConsume() * 2 + "点伤害");
            //如果技能还有buff
            if (skill instanceof Buff) {
                Buff buff = ((Buff) skill);
                System.out.print(buff.getDescription());
                hero.reduceMp(buff.getConsume());
                if (buff.isDebuff()) {
                    hero.addBuff(buff);
                } else {
                    this.addBuff(buff);
                }
            }
            System.out.println();

            this.hp += skill.cast(hero);

        } else {
            //不够就只能普攻了
            if (this.isCanDamage()) {
                this.attach(hero);
            }else{
                System.out.println("【"+this.name+"】什么都做不了");
            }
        }
    }

    //掉血
     public void reduceHp(int num) {
        this.hp -= num;
    }

    //掉蓝
    protected void reduceMp(int num) {
        this.mp -= num;
    }
    //普攻
    public void attach(Hero hero) {
        if (this.iscanBlind()) {
            System.out.println("【"+this.getName()+"】的攻击落空");
        } else {
            if (this.isInjuryFree()) {
                System.out.println("【" + hero.getName() + "】"+"免疫！！ 哈哈哈 打不了我吧");
            } else {
                hero.reduceHp(this.damage);
                System.out.println("【" + this.getName() + "】攻击了【" + hero.getName() + "】造成了" + this.damage + "点伤害！");
            }
        }
    }
    //每回合开始的时候结算buff
    public void turn() {
        this.canCast = true;
        this.canDamage = true;
        this.canBlind=false;
        this.canInjuryfree=false;
        for (Map.Entry<Buff, Integer> entry : buff.entrySet()) {
            if (entry.getValue() == 0) {
                buff.remove(entry.getKey());
                continue;
            }
            entry.getKey().action(this);
            entry.setValue(entry.getValue() - 1);
        }
    }
}
