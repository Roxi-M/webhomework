import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String s="acbdw,1269547,AASIDX,AIUydjs,12sjaA,3819247,ausSHSzio,IUFISsi";
      String str[]=s.split(",");
        method p = new method();
        int i=0;
       for(String ch:str){
            System.out.println(p.method1(str[i]));
            System.out.println(p.method2(str[i]));
            System.out.println(p.method3(str[i]));
            System.out.println(p.method4(str[i]));
           p.method5(str[i]);
          p.method6(str[i]);
             i++;
             System.out.println("-----------------------");
        }
    }
}
