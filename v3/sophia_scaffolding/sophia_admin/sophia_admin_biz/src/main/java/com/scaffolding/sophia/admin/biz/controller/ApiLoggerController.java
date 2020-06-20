package com.scaffolding.sophia.admin.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import com.scaffolding.sophia.admin.api.entity.dto.ApiLoggerSearchDto;
import com.scaffolding.sophia.admin.biz.service.ApiLoggerService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
import com.scaffolding.sophia.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LHL
 */
@RestController
@RequestMapping("/log")
@Api(tags = "日志管理")
public class ApiLoggerController extends BaseController {

    @Autowired
    private ApiLoggerService apiLoggerService;

    @GetMapping("/web/list")
    @ApiOperation(value = "查询日志管理列表分页-后端管理日志管理", notes = "查询日志管理列表分页-后端管理日志管理")
    public ApiResponse getApiLoggerPage(@ModelAttribute ApiLoggerSearchDto params){
        // Map<String, Object> params = getParams();
        return success(apiLoggerService.queryApiLoggerList(params));
    }

    @SysLog("删除日志")
    @DeleteMapping(value = "/web/del/{id}")
    @ApiOperation(value = "删除日志管理-后端管理日志管理", notes = "删除日志管理-后端管理日志管理")
    public ApiResponse deleteApiLogger(@ApiParam(value = "日志id", required = true) @PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return fail("id不能为空");
        }
        if (apiLoggerService.deleteApiLogger(id)) {
            return success("删除成功");
        } else {
            return fail("删除失败");
        }
    }


    @DeleteMapping(value = "/web/delBatch")
    @ApiOperation(value = "批量删除日志管理-后端管理日志管理", notes = "批量删除日志管理-后端管理日志管理")
    public ApiResponse deleteBatchApiLogger(@RequestBody String ids) {
        if (StringUtils.isBlank(ids)) {
            return fail("参数不能为空");
        }
        List<String> idList = JSON.parseArray(((JSONArray) JSON.parseObject(ids).get("ids")).toJSONString(), String.class);
        if (apiLoggerService.deleteBatchApiLogger(idList)) {
            return success("批量删除成功");
        } else {
            return fail("批量删除失败");
        }
    }

    /**
     * 插入日志
     *
     * @param apiLogger 日志实体
     * @return ApiResponse
     */
    @PostMapping("/info/add")
    public ApiResponse saveLog(@Validated @RequestBody ApiLogger apiLogger) {
        return handle(apiLoggerService.saveLog(apiLogger));
    }


}
