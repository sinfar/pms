package jxf.pms.data;

import jxf.pms.data.LoginUserDTO;

public class UserThreadLocal {
    /**
     * 关于参数的说明
     * 	如果需要传递多值则使用Map集合封装
     */
    private static ThreadLocal<LoginUserDTO> userThread =
            new ThreadLocal<LoginUserDTO>();

    public static void set(LoginUserDTO user){
        userThread.set(user);
    }

    public static LoginUserDTO get(){
        return userThread.get();
    }

    //防止内存泄漏
    public static void remove(){
        userThread.remove();
    }
}
