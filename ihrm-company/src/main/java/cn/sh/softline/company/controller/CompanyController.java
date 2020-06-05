package cn.sh.softline.company.controller;


import cn.sh.softline.common.entity.Result;
import cn.sh.softline.company.entity.Company;
import cn.sh.softline.company.service.CompanyService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rz045
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/company")
@Api(value = "企业服务",tags = "企业服务")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "新增企业",httpMethod = "POST")
    @PostMapping("add")
    public Result add(@RequestBody Company company){
        company.setState(1);
        company.setCreateTime(new Date());
        boolean save = companyService.save(company);
        if(save){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }
    @ApiOperation(value = "删除企业",httpMethod = "DELETE")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id){
        boolean delete = companyService.removeById(id);
        if(delete){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @ApiOperation(value = "修改企业信息",httpMethod = "PUT")
    @PutMapping("update")
    public Result update(@RequestBody Company company){
        boolean update = companyService.updateById(company);
        if(update){
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

    @GetMapping("selectAll")
    @ApiOperation(value = "查询所有信息",httpMethod = "GET")
    public Result queryAll(){
        List<Company> list = companyService.list();
        return Result.SUCCESS(list);
    }

    @GetMapping("select/{id}")
    @ApiOperation(value="查询单个信息",httpMethod = "GET")
    public Result queryById(@PathVariable("id") String id){
        Company company = companyService.getById(id);
        return Result.SUCCESS(company);
    }

    @GetMapping("select")
    public Result queryInfoByPage(@RequestParam("pageNo") long pageNo,@RequestParam("size") long size){
        Page<Company> page = companyService.page(new Page<Company>(pageNo,size),null);
        return Result.SUCCESS(page);
    }

}
