package jxf.pms.interceptor;

import com.alibaba.cola.dto.Response;
import com.alibaba.fastjson.JSON;
import jxf.pms.cmd.BaseCmd;
import jxf.pms.data.LoginUserDTO;
import jxf.pms.data.ErrorCode;
import jxf.pms.data.PermissionDTO;
import jxf.pms.data.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    static final ThreadLocal<Integer> localUserId = new ThreadLocal<>();

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
            LoginUserDTO user=(LoginUserDTO)request.getSession().getAttribute("user");
            if(user!=null){
                UserThreadLocal.set(user);
                return true;
            }

            if (request.getMethod().equalsIgnoreCase("get"))
                response.sendRedirect(request.getContextPath()+"/login");
            else {
                Response rst = Response.buildFailure(ErrorCode.b_user_none_login.getErrCode(), ErrorCode.b_user_none_login.getErrDesc());
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(rst));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView == null) return;

        // 权限
        List<PermissionDTO> permissions =(List<PermissionDTO>)request.getSession().getAttribute("permissions");
        modelAndView.addObject("permissions", permissions);

        // 一级菜单
        List<PermissionDTO> menus1 = permissions.stream().filter(t->t.getLevel() == 1).collect(Collectors.toList());
        for (PermissionDTO menu : menus1) {
            // 一级菜单路径为空，默认使用首个子菜单路径
            if (StringUtils.isEmpty(menu.getCode())) {
                Optional<PermissionDTO> firstChild = permissions.stream().filter(t-> menu.getId().equals(t.getParentId())).findFirst();
                if (firstChild.isPresent())
                    menu.setCode(firstChild.get().getCode());
            }
        }
        modelAndView.addObject("menus1", menus1);

        // 二级菜单
        String url = request.getRequestURI();
        PermissionDTO currMenu = permissions.stream().filter(t->url.equals(t.getCode())).findFirst().orElse(null);
        if (currMenu == null) {
            currMenu = permissions.stream().filter(t->t.getLevel() == 2).findFirst().orElseGet(null);
        }
        if (currMenu.getLevel() == 3) {
            PermissionDTO finalCurrMenu1 = currMenu;
            currMenu = permissions.stream().filter(t->t.getId().equals(finalCurrMenu1.getParentId())).findFirst().orElseGet(null);
        }
        PermissionDTO finalCurrMenu = currMenu;
        List<PermissionDTO> menus2 = permissions.stream().filter(t-> finalCurrMenu.getParentId() != null && finalCurrMenu.getParentId().equals(t.getParentId())).collect(Collectors.toList());
        modelAndView.addObject("menus2", menus2);
        modelAndView.addObject("currMenu", currMenu);

        // 当前用户
        LoginUserDTO user = (LoginUserDTO)request.getSession().getAttribute("user");
        modelAndView.addObject("user", user);
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }
}
