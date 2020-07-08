package cn.sh.softline.system.shiro.realm;

import cn.sh.softline.shiro.realm.IhrmRealm;
import cn.sh.softline.system.entity.ProfileResult;
import cn.sh.softline.system.entity.User;
import cn.sh.softline.system.service.PermissionService;
import cn.sh.softline.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRealm extends IhrmRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //2.根据手机号查询用户
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile",mobile);
        User user = userService.getOne(queryWrapper);
        //3.判断用户是否存在，判断用户密码是否正确
        if(user != null && user.getPassword().equals(password)){
            //4.构造安全数据并返回
            ProfileResult profileResult = null;
            if("user".equals(user.getLevel())){
                profileResult = new ProfileResult(user);
            }else{
                if("coAdmin".equals(user.getLevel())){
                }
                queryWrapper = null;
                queryWrapper.eq("enVisible",1);
                List list = permissionService.list(queryWrapper);
                profileResult = new ProfileResult(user,list);
            }
        }


        return null;
    }
}
