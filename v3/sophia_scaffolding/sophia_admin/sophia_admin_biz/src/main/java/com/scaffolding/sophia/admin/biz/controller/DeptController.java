package com.scaffolding.sophia.admin.biz.controller;

import com.scaffolding.sophia.admin.api.entity.bo.Dept;
import com.scaffolding.sophia.admin.biz.service.dept.DeptService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.controller
 * @ClassName: DeptController
 * @Date: 2019/11/5 10:53
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/dept")
@Api(tags = "部门管理")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @GetMapping("")
    @ApiOperation("查询部门树形结构")
    public ApiResponse getDeptTree() {
        return success(deptService.queryDeptTree());
    }

    @PostMapping("/add")
    @ApiOperation("新增部门")
    public ApiResponse addDept(@RequestBody Dept dept){
        if (deptService.addOrUpdateDept(dept)){
            return success("保存成功");
        }else {
            return fail("保存失败");
        }
    }

    @PutMapping("/update")
    @ApiOperation("修改部门")
    public ApiResponse updateDept(@RequestBody Dept dept){
        if (deptService.addOrUpdateDept(dept)){
            return success("修改成功");
        }else {
            return fail("修改失败");
        }
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除部门")
    public ApiResponse deleteDept(@PathVariable Long id){
        if (deptService.deleteDept(id)){
            return success("删除成功");
        }else {
            return fail("删除失败");
        }
    }


    @DeleteMapping("/batchDel")
    @ApiOperation("批量删除部门")
    public ApiResponse deleteDept(@RequestParam List<Long> ids){
        if (deptService.deleteBatchDept(ids)){
            return success("批量删除成功");
        }else {
            return fail("批量删除失败");
        }
    }


}
