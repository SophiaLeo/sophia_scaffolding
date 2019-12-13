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
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.controller
 * @ClassName: CompanyController
 * @Date: 2019/11/22 13:50
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/company")
@Api(tags = "公司管理")
public class CompanyController extends BaseController {

    @Autowired
    private DeptService deptService;


    @GetMapping("")
    @ApiOperation("查询公司列表")
    public ApiResponse companyList() {
        Map<String, Object> params = getParams();
        return success(deptService.queryCompanyList(params));
    }

    @PostMapping("/add")
    @ApiOperation("新增公司")
    public ApiResponse addCompany(@RequestBody Dept dept){
        if (deptService.addOrUpdateCompany(dept)){
            return success("保存成功");
        }else {
            return fail("保存失败");
        }
    }

    @PutMapping("/update")
    @ApiOperation("修改公司")
    public ApiResponse updateCompany(@RequestBody Dept dept){
        if (deptService.addOrUpdateCompany(dept)){
            return success("修改成功");
        }else {
            return fail("修改失败");
        }
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除公司")
    public ApiResponse deleteCompany(@PathVariable Long id){
        if (deptService.deleteDept(id)){
            return success("删除成功");
        }else {
            return fail("删除失败");
        }
    }


    @DeleteMapping("/batchDel")
    @ApiOperation("批量删除公司")
    public ApiResponse deleteCompany(@RequestParam List<Long> ids){
        if (deptService.deleteBatchDept(ids)){
            return success("批量删除成功");
        }else {
            return fail("批量删除失败");
        }
    }


}
