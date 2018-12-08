package team.redrock.base.buff;

import team.redrock.base.Hero;

public interface Silent extends Buff{
    @Override
    default String getDescription(){
      return "沉默对方"+getTime()+"回合";
     }
     int consume=20;
     @Override
     default int getConsume(){
        return getTime()*consume;
     }
     @Override
    default boolean isDebuff(){
         return true;
     }

    @Override
    default void action(Hero hero) {
        hero.setCanCast(false);
    }
}
