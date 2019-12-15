package invokeLearning;

import java.lang.reflect.Method;

public class invokeTest {
    public static void main(String[] args) throws Exception{
        Class<?> clz = Class.forName("invokeLearning.UserManager");
        Object o = clz.newInstance();
        Method m = clz.getMethod("addUser", String.class, String.class);
        m.invoke(o, "001", "张三");

    }

    /*
    static class UserManager {
        public void addUser(String id, String userName){
            System.out.println("add a user");
        }
    }*/
}
