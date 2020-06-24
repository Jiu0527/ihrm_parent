package cn.sh.softline.system.controller;


import cn.sh.softline.common.entity.Result;
import cn.sh.softline.common.entity.ResultCode;
import cn.sh.softline.common.utils.JwtUtils;
import cn.sh.softline.exception.CommonException;
import cn.sh.softline.system.entity.User;
import cn.sh.softline.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSetMetaData;
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
    @DeleteMapping("delete/{id}")
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
        QueryWrapper<User> wrapper = new QueryWrapper();
        if (password != null) {
            wrapper.eq("mobile", mobile);
        }
        List<User> list = userService.list(wrapper);
        User user = list.get(0);
        if (user == null|| !user.getPassword().equals(password)) {
            return new Result(ResultCode.MOBILEANDPASSWORDERROR);
        }else{
            Map<String,Object> map = new HashMap<>();
            map.put("companyId",user.getCompanyId());
            map.put("companyName",user.getCompanyName());
            String token = jwtUtils.createJWT(user.getId(), user.getUsername(), map);
            return new Result(ResultCode.SUCCESS,token);
        }

    }
}
