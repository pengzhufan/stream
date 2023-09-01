package testAnnotate.invoke;

import testAnnotate.annotate.Init;
import testAnnotate.entity.User;

import java.lang.reflect.Method;

public class UserFactory {

        public static User create() {
            User user = new User();


            // 获取User类中所有的方法（getDeclaredMethods也行）
            Method[] methods = User.class.getMethods();


            try
            {
                for (Method method : methods)
                {
                    // 如果一个注解指定注解类型是存在于此元素上此方法返回true，否则返回false
                    //参数 -- 对应于注解类型的Class对象
                    if (method.isAnnotationPresent(Init.class))
                    {
                        //此方法返回该元素的注解在此元素的指定注释类型（如果存在），否则返回null
                        Init init = method.getAnnotation(Init.class);
                        // 如果Method代表了一个方法 那么调用它的invoke就相当于执行了它代表的这个方法,在这里就是给set方法赋值
                        method.invoke(user, init.value());
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }


            return user;
}
}

