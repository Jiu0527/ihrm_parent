package cn.sh.softline.company.controller;


import cn.sh.softline.common.entity.Result;
import cn.sh.softline.company.entity.Department;
import cn.sh.softline.company.service.DepartmentService;
import cn.sh.softline.exception.CommonException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rz045
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/company/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("add")
    @ApiOperation(value = "部门新增")
    public Result add(@RequestBody Department department){
        boolean save = departmentService.save(department);
        if(save){
            return Result.SUCCESS();
        }else {
            throw new CommonException();
        }
    }
    @ApiOperation(value = "根据id删除部门")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") String id){
        boolean del = departmentService.removeById(id);
        if(del){
            return Result.SUCCESS();
        }else {
            throw new CommonException();
        }
    }
    @PutMapping("update")
    @ApiOperation(value = "更改部门信息")
    public Result update(@RequestBody Department department){
        boolean update = departmentService.updateById(department);
        if(update){
            return Result.SUCCESS();
        }else {
            throw new CommonException();
        }
    }

    @GetMapping("select/{id}")
    @ApiOperation(value = "根据id查询部门信息 ")
    public Result queryById(@PathVariable("id") String id){
        if(id == null){
            throw new CommonException();
        }
        Department department = departmentService.getById(id);
        return Result.SUCCESS(department);
    }

    @GetMapping("selectAll")
    @ApiOperation(value ="查询所有部门")
    public Result findAll(){
        List<Department> list = departmentService.list();
        return Result.SUCCESS(list);
    }

    @GetMapping("select")
    @ApiOperation(value ="分页查询")
    public Result getListByPage(@RequestParam("pageNo") long pageNo,@RequestParam("size") long size){
        Page<Department> page = departmentService.page(new Page<>(pageNo, size), null);
        return Result.SUCCESS(page);
    }
}
