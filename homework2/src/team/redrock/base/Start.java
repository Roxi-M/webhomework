package team.redrock.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @Description
 * @Author 余歌
 * @Date 2018/10/31
 **/
public class Start {

    //这一轮参战的英雄
    private List<Hero> list;

    //赢家的排名
    private Stack<Hero> rank = new Stack<>();

    //舞台
    private Stage stage;

    //英雄的包名
    private static String packageName = "team.redrock.heros";

    public Start() {
        load();
        stage = new Stage();
    }

    //类加载 现在不用搞懂
    private void load() {
        list = new LinkedList<>();
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String protocol = resource.getProtocol();
                if (protocol.equalsIgnoreCase("file")) {
                    String packagePath = resource.getPath();
                    loadClass(packageName, packagePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //类加载 现在不用搞懂
    private void loadClass(String packageName, String packagePath) {
        File[] files = new File(packagePath).listFiles(file -> file.isDirectory() || file.getName().endsWith(".class"));
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (file.isDirectory()) {
                    String subPackageName = packageName + "." + fileName;
                    String subPackagePath = packagePath + "/" + fileName;
                    loadClass(subPackageName, subPackagePath);
                } else {
                    fileName = packageName + "." + fileName.substring(0, fileName.lastIndexOf("."));
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(fileName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (clazz != null) {
                        if (!clazz.equals(Hero.class) && Hero.class.isAssignableFrom(clazz)) {
                            Hero hero = null;
                            try {
                                hero = (Hero) clazz.newInstance();
                                list.add(hero);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }
    }

    //开始战斗
    public void begin() {
        //打乱这一轮的英雄的这个集合
        Collections.shuffle(list);
        //胜利者
        List<Hero> winners = new LinkedList<>();

        //如果这一轮的参战英雄数为奇数 第一个人轮空
        if (list.size() % 2 != 0) {
            winners.add(list.get(0));
            list.remove(0);
        }

        //开始两个一组开始战斗 赢家放到winners里面
        while (list.size() != 0) {
            Hero hero1 = list.get(0);
            Hero hero2 = list.get(1);
            list.remove(0);
            list.remove(0);

            boolean winner = stage.battle(hero1, hero2);
            if (winner) {
                winners.add(hero1);
                rank.add(hero2);
            } else {
                winners.add(hero2);
                rank.add(hero1);
            }
        }

        //赢家进入下一轮
        list = winners;

        //如果下一轮只有一个人 那么他是最后胜者
        if (list.size() == 1) {
            rank.add(list.get(0));
            list.remove(0);
        } else {
            //还有很多人的话 就继续战斗
            begin();
        }
    }

    public static void main(String[] args) {
        Start start = new Start();
        start.begin();
        for (int i = 1; !start.rank.empty(); i++) {
            System.out.println("第" + i + "名：" + start.rank.pop().getName());
        }
    }
}
