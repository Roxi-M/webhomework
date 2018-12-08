import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Out {
    public void Outt(String x, List str) throws IOException {
        File file=new File(x);
        if(!file.exists()){
            file.createNewFile();
        }else System.out.println("已存在");
        BufferedOutputStream bf=new BufferedOutputStream(new FileOutputStream(x));
  //      bf.write());
        bf.close();
    }
}
