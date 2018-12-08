package team.redrock.base.buff;
import team.redrock.base.Hero;
public interface Blinding extends Buff {
    @Override
    default String getDescription(){
        return "并且致盲了"+getTime()+"回合";
    }
    int consume=300;
    @Override
    default int getConsume(){
        return consume*getTime();
    }
    @Override
    default boolean isDebuff(){
        return true;
    }
}
