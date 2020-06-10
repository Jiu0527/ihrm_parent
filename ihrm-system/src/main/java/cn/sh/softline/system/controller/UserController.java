package cn.sh.softline.system.controller;


import cn.sh.softline.common.entity.Result;
import cn.sh.softline.exception.CommonException;
import cn.sh.softline.system.entity.User;
import cn.sh.softline.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rz045
 * @since 2020-06-09
 */
@CrossOrigin
@RestController
@RequestMapping("/system")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    @ApiOperation(value = "用户新增")
    public Result add(@RequestBody User user){
        boolean save = userService.save(user);
        if(save){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") String id){
        boolean res = userService.removeById(id);
        if(res){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }


}
