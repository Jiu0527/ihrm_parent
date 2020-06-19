package cn.sh.softline.system.controller;


import cn.sh.softline.common.entity.Result;
import cn.sh.softline.system.entity.Role;
import cn.sh.softline.system.service.RoleService;
import cn.sh.softline.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rz045
 * @since 2020-06-09
 */
@Api(value = "角色管理",tags = "角色管理")
@CrossOrigin
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "角色新增",notes = "角色新增")
    @PostMapping("add")
    public Result add(@RequestBody Role role){
        boolean save = roleService.save(role);
        if(save){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "角色删除",notes = "角色删除")
    public Result delete(@PathVariable("id") String id){
        boolean remove = roleService.removeById(id);
        if(remove){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "角色修改",notes = "角色修改")
    public Result update(@RequestBody Role role){
        boolean update = roleService.update(role, null);
        if(update){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @GetMapping("selectAll")
    @ApiOperation(value = "查询所有角色",notes = "查询所有角色")
    public Result findAll(){
        List<Role> list = roleService.list();
        return Result.SUCCESS(list);
    }

    @GetMapping("select/{id}")
    @ApiOperation(value = "查询单个角色",notes = "查询单个角色")
    public Result queryById(@PathVariable("id") String id){
        Role role = roleService.getById(id);
        return Result.SUCCESS(role);
    }

    @GetMapping("select")
    @ApiOperation(value = "条件分页查询角色",notes = "条件分页查询角色")
    public Result queryInfo(@RequestParam("pageNo") long pageNo, @RequestParam("size") long size, @RequestBody Role role){
        Page page = new Page(pageNo,size);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(role.getCompanyId() != null){
            wrapper.eq("companyId",role.getCompanyId());
        }
        Page list = roleService.page(page, wrapper);
        return Result.SUCCESS(list);
    }
}
