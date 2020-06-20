package com.scaffolding.sophia.admin.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scaffolding.sophia.admin.api.entity.dto.CompanySearchDto;
import com.scaffolding.sophia.admin.api.entity.dto.DeptDto;
import com.scaffolding.sophia.admin.api.entity.vo.DeptVo;
import com.scaffolding.sophia.admin.biz.service.DeptService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import com.scaffolding.sophia.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @GetMapping("/web")
    @ApiOperation("查询公司列表")
    public ApiResponse companyList(@ModelAttribute CompanySearchDto params) {
        // Map<String, Object> params = getParams();
        return success(deptService.queryCompanyList(params));
    }
    
    @GetMapping("/web/select/list")
    @ApiOperation(value = "查询公司下拉框-后端管理公司管理", notes = "查询公司管理-后端管理公司管理")
    public ApiResponse getCompanySelectList(){
        return success(deptService.queryCompanySelectList());
    }
    
    @GetMapping(value = "/web/info/{id}")
    @ApiOperation(value = "获取公司")
    public ApiResponse getCompany(@ApiParam(value = "公司id",required = true)@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return fail("id不能为空");
        }
        DeptVo deptVo = deptService.queryDeptById(id);
        if (null == deptVo){
            return fail("未查找到该公司");
        }else{
            return success(deptVo);
        }
    }


    @SysLog("新增公司")
    @PostMapping("/web/add")
    @ApiOperation("新增公司")
    public ApiResponse addCompany(@RequestBody DeptDto deptDto){
        if (deptService.addOrUpdateCompany(deptDto)){
            return success("保存成功");
        }else {
            return fail("保存失败");
        }
    }

    @SysLog("修改公司")
    @PutMapping("/web/update")
    @ApiOperation("修改公司")
    public ApiResponse updateCompany(@RequestBody DeptDto deptDto){
        if (deptService.addOrUpdateCompany(deptDto)){
            return success("修改成功");
        }else {
            return fail("修改失败");
        }
    }

    @SysLog("删除公司")
    @DeleteMapping("/web/del/{id}")
    @ApiOperation("删除公司")
    public ApiResponse deleteCompany(@PathVariable String id){
        if (deptService.deleteDept(id)){
            return success("删除成功");
        }else {
            return fail("删除失败");
        }
    }


    @SysLog("批量删除公司")
    @DeleteMapping("/web/batchDel")
    @ApiOperation("批量删除公司")
    public ApiResponse deleteBatchCompany(@RequestBody String ids){
        if (StringUtils.isBlank(ids)) {
            return fail("参数不能为空");
        }
        List<String> idList = JSON.parseArray(((JSONArray) JSON.parseObject(ids).get("ids")).toJSONString(), String.class);

        if (deptService.deleteBatchDept(idList)){
            return success("批量删除成功");
        }else {
            return fail("批量删除失败");
        }
    }


}
