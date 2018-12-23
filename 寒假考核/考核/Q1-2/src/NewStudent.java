import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.util.*;

public class NewStudent {
    public static void  main(String args[]) {
        Scanner scanner=new Scanner(System.in);
        String id=scanner.next();
        try {
            boolean flag = false;
            Student[] stus = new Student[18826];
            stus = FileUtil.parseStudents("students.txt");
            for (Student student : stus) {
                if (id.equals(student.getStuid())) {
                    System.out.println(student.getName());
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (flag) {
                System.out.println("该学生不存在");
            }
            int max=0;
            String name=null;
            for(Student student:stus){
                int count=0;
                for(Student student1:stus){
                    if(student.getName().equals(student1.getName())){
                        count++;
                    }
                }
                if(max<count){
                    max=count;
                    name=student.getName();
                }
            }
            System.out.println(name+" "+max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
