package dynamicProxy;

public class UserServiceImpl implements UserService{
    @Override
    public void getName(String name) {
        System.out.println("姓名");
        System.out.println(name);
    }

    @Override
    public void getAge(int age) {
        System.out.println("年龄");
        System.out.println(age);
    }
}
