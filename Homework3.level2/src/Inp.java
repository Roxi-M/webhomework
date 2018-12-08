import java.io.*;

public class Inp {
    public String inpp(String x) throws IOException {
        File file=new File(x);
        if(!file.exists()){
            file.createNewFile();
        }else System.out.println("已存在");
        BufferedInputStream inp=null;
        inp=new BufferedInputStream(new FileInputStream(x));
        int len;
        byte cost[]=new byte[1024];
        String s = null;
        while ((len=inp.read(cost,0,cost.length))!=-1){
            s=new String(cost,0,len);
        }
        inp.close();
        return  s;
    }
}
