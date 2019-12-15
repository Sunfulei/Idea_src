package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    public UserService target;

    public MyInvocationHandler(){};

    public MyInvocationHandler(UserService userService){
        this.target = userService;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if(method.getName().equals("getName")){
            System.out.println("before get name");
            return method.invoke(target, objects);
        }else{
            return method.invoke(target, objects);
        }
    }
}
