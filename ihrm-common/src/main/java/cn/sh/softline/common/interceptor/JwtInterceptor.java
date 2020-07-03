package cn.sh.softline.common.interceptor;

import cn.sh.softline.common.entity.ResultCode;
import cn.sh.softline.common.utils.JwtUtils;
import cn.sh.softline.exception.CommonException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置拦截器可以简化获取token的代码
 * 可以检验是否登录，可以获取用户权限
 * true代表放行，false代表拦截
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            throw new CommonException(ResultCode.UNAUTHENTICATED);
        }
        String token = authorization.replace("Bear", "").trim();
        Claims claims = jwtUtils.parseJWT(token);
        if (claims == null) {
            throw new CommonException(ResultCode.UNAUTHENTICATED);
        }else{
            String apis = (String)claims.get("apis");
            HandlerMethod hm = (HandlerMethod) handler;
            RequestMapping methodAnnotation = hm.getMethodAnnotation(RequestMapping.class);
            String name = methodAnnotation.name();
            if(apis.contains(name)){
                request.setAttribute("user_claims",claims);
                return true;
            }else{
                throw new CommonException(ResultCode.UNAUTHORISE);
            }
        }
    }
}
