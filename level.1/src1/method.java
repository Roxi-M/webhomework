import java.util.Arrays;
public class method {
  public boolean method1(String str){
      int a=str.length();
      boolean flag=true;
      for(int i=0;i<str.length();i++){
          int int3=str.charAt(i);
          if(48<=int3&&int3<=57);
              else flag=false;
      }
    return flag;
  }
  public boolean method2(String str){
      boolean flag=true;
      for(int i=0;i<str.length();i++){
          int int3=str.charAt(i);
          if(65<=int3&&int3<=90);
          else flag=false;
      }
      return flag;
  }
  public boolean method3(String str){
        boolean flag=true;
        for(int i=0;i<str.length();i++){
            int int3=str.charAt(i);
            if(97<=int3&&int3<=122);
            else flag=false;
        }
        return flag;
    }
  public String method4(String str){
      if(method3(str)){
         str= str.toUpperCase();
         return str;
      }
      String s="false";
      return s;
  }
  public void method5(String str){
     if( method1(str)){
        char c[]=str.toCharArray();
        int num[]=new int[str.length()];
        for(int i=0;i<str.length();i++){
          Integer a=new Integer(c[i]);
          int b=a-48;
          num[i]=b;
        }
        Arrays.sort(num);
        for(int ch:num)
     System.out.print(ch+" ");
        System.out.println();
     }
  }
  public void method6(String str){
      boolean flag=true;
      for(int i=0;i<str.length();i++){
          if(str.charAt(i)>=65&&str.charAt(i)<=122);
              else flag=false;
      }
      if(flag){
          char c[]=str.toCharArray();
          for(char ch:c){
              System.out.print(ch+" ");
          }
          System.out.println();
      }
      else System.out.println("not fit");
  }
}
