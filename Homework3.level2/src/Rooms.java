import java.io.Serializable;

public class Rooms implements Serializable {
    public int clas;
    public String name;
    public int num;
    public int age;
    public String sex;
    public Rooms(){

    }
    public Rooms(int clas,String name,int num,int age,String sex){
        this.clas=clas;
        this.name=name;
        this.num=num;
        this.age=age;
        this.sex=sex;
    }
    public int getClas() {
        return clas;
    }
    public String getName(){
        return name;
    }

    public int getNum() {
        return num;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}
