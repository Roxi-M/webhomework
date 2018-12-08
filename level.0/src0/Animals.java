public class Animals {
    private String species;
    private String color;
    private int age;
    private String appearance;
    private String sex;
    public Animals(){

    }
    public Animals(String species,String color,int age,String appearance,String sex){
          this.sex=sex;
          this.appearance=appearance;
          this.age=age;
          this.color=color;
          this.species=species;
    }
    public void setSpecies(String species){
        this.species=species;
    }
    public String getSpecies(){
        return species;
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getColor(){
        return color;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getAge(){
        return age;
    }
    public void setAppearance(String appearance){
        this.appearance=appearance;
    }
    public String getAppearance(){
        return appearance;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public String getSex(){
        return sex;
    }
    public void ppp(){
        System.out.println("动物种类："+species);
        System.out.println("动物颜色:"+color);
        System.out.println("动物年龄："+age);
        System.out.println("动物相貌:"+appearance);
        System.out.println("动物性别："+sex);
    }
}
