import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectOutputStream out=null;
        File file=new File("Roxi.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("已存在");
        try {
            List<Rooms> tms=new ArrayList();
            tms.add(new Rooms(231,"yaoyao",201821283,18,"man"));
            tms.add(new Rooms(231,"wenwen",202313,18,"man"));
            tms.add(new Rooms(231,"ruirui",123813,18,"man"));
            tms.add(new Rooms(231,"nanan",231413,18,"man"));
            tms.add(new Rooms(211,"yuyu",231314,18,"woman"));
            out=new ObjectOutputStream(new FileOutputStream("Roxi.txt"));
            out.writeObject(tms);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file1=new File("Roxi - 副本.txt");
        if(!file1.exists()){
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("已存在");

        ObjectInputStream oj=null;
       ObjectOutputStream bf=null;
     FileInputStream inq=null;
        try {
            try {
                oj=new ObjectInputStream(new FileInputStream("Roxi.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Rooms> rooms= null;  //反序列
            try {
                rooms = (List<Rooms>) oj.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                inq=new FileInputStream("Roxi.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int len;
            byte cost[]=new byte[1024];
            try {
                bf= new ObjectOutputStream(new FileOutputStream("Roxi - 副本.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bf.writeObject(rooms);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inq.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObjectInputStream inp=null;

        try{
            inp=new ObjectInputStream(new BufferedInputStream(new FileInputStream("Roxi - 副本.txt")));
            List<Rooms> tms=new ArrayList<>();
            tms= (List<Rooms>) inp.readObject();
            for(Rooms x:tms){
                System.out.println(x.getClas()+" "+x.getName()+" "+x.getNum()+" "+x.getAge()+" "+x.getSex());
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

