public class Student {
    private String name;
    private String stuid;
    private String clas;
    private String party;

    public String getName() {
        return name;
    }

    public String getClas() {
        return clas;
    }

    public String getParty() {
        return party;
    }

    public String getStuid() {
        return stuid;
    }
    public Student(){

    }
    public Student(String name,String stuid,String clas,String party){
        this.name=name;
        this.stuid=stuid;
        this.clas=clas;
        this.party=party;
    }
}
