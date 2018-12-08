package team.redrock.base;

public class MagicSkill extends Skill {
    private int damage;
    public MagicSkill(String name,int damage){
        super(name,damage*2);
        this.damage=damage;
    }


    @Override
    public int cast(Hero hero) {
        hero.reduceHp(damage);
        return 0;
    }

}
