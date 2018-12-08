public class Main {
    public static void main(String []args){
        Animals cat=new Animals();
        cat.setAge(2);
        cat.setAppearance("loviness");
        cat.setSpecies("coffee cat");
        cat.setColor("orange");
        cat.setSex("female");
       cat.ppp();
       Animals dog=new Animals("big dog","blue",3,"凶狠","male");
       dog.ppp();
    }
}
