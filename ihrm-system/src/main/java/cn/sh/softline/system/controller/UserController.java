package cn.sh.softline.system.controller;


import cn.sh.softline.common.entity.Result;
import cn.sh.softline.common.entity.ResultCode;
import cn.sh.softline.common.utils.JwtUtils;
import cn.sh.softline.common.utils.PermissionConstants;
import cn.sh.softline.system.entity.Permission;
import cn.sh.softline.system.entity.ProfileResult;
import cn.sh.softline.system.entity.Role;
import cn.sh.softline.system.entity.User;
import cn.sh.softline.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rz045
 * @since 2020-06-09
 */
@CrossOrigin
@RestController
@Api(value = "用户管理", tags = "用户管理")
@RequestMapping("/system")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("add")
    @ApiOperation(value = "用户新增")
    public Result add(@RequestBody User user) {
        boolean save = userService.save(user);
        if (save) {
            return Result.SUCCESS();
        } else {
            return Result.FAIL();
        }
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "delete/{id}", name = "API-USER-DELETE")
    public Result delete(@PathVariable("id") String id) {
        boolean res = userService.removeById(id);
        if (res) {
            return Result.SUCCESS();
        } else {
            return Result.FAIL();
        }
    }

    @PutMapping("update")
    public Result update(@RequestBody User user) {
        boolean update = userService.update(user, null);
        if (update) {
            return Result.SUCCESS();
        } else {
            return Result.FAIL();
        }
    }

    @GetMapping("select/{id}")
    public Result queryById(@PathVariable("id") String id) {
        User user = userService.getById(id);
        return Result.SUCCESS(user);
    }

    @GetMapping("select")
    public Result queryInfo(@RequestParam("pageNo") long pageNo, @RequestParam("size") long size, @RequestBody User user) {
        Page page = new Page(pageNo, size);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (user.getId() != null) {
            wrapper.eq("id", user.getId());
        }
        if (user.getMobile() != null) {
            wrapper.eq("mobile", user.getMobile());
        }
        if (user.getDepartmentId() != null) {
            wrapper.eq("departmentId", user.getDepartmentId());
        }
        if (user.getFormOfEmployment() != null) {
            wrapper.eq("formOfEmployment", user.getFormOfEmployment());
        }
        Page list = userService.page(page, wrapper);
        return Result.SUCCESS(list);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");
        //JWT登录方式
        /*QueryWrapper<User> wrapper = new QueryWrapper();
        if (password != null) {
            wrapper.eq("mobile", mobile);
        }
        List<User> list = userService.list(wrapper);
        User user = list.get(0);
        if (user == null|| !user.getPassword().equals(password)) {
            return new Result(ResultCode.MOBILEANDPASSWORDERROR);
        }else{
            StringBuilder stringBuilder = new StringBuilder();
            for (Role role:user.getRoles()) {
                for (Permission permission:role.getPermissions()) {
                    if(permission.getType() == PermissionConstants.PERMISSION_API){
                        stringBuilder.append(permission.getCode()).append(",");
                    }
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("companyId",user.getCompanyId());
            map.put("companyName",user.getCompanyName());
            map.put("apis",stringBuilder.toString());
            String token = jwtUtils.createJWT(user.getId(), user.getUsername(), map);
            return new Result(ResultCode.SUCCESS,token);
        }*/

        try {
            //1.构造登录令牌 usernamePasswordToken
            //加密密码
            password = new Md5Hash(password, mobile, 3).toString();//1.密码 2.盐 3.加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile, password);
            //2.获取Subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入realm完成用户认证
            subject.login(upToken);
            //4.获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //5.构造返回结果
            return new Result(ResultCode.SUCCESS,sessionId);
        } catch (Exception e) {
            return new Result(ResultCode.MOBILEANDPASSWORDERROR);
        }


    }

    @PostMapping("/profile")
    public Result profile(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        String userid = claims.getId();
        User user = userService.getById(userid);
        return new Result(ResultCode.SUCCESS, new ProfileResult(user));
    }

}
