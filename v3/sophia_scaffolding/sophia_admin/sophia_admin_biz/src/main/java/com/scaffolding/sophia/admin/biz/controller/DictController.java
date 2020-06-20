package com.scaffolding.sophia.admin.biz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scaffolding.sophia.admin.api.entity.dto.DictDto;
import com.scaffolding.sophia.admin.api.entity.dto.DictSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.DictVo;
import com.scaffolding.sophia.admin.biz.service.DictService;
import com.scaffolding.sophia.common.base.support.ApiResponse;
import com.scaffolding.sophia.common.base.support.BaseController;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.controller
 * @ClassName: DictController
 * @Date: 2019/11/5 10:54
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/dict")
@Api(tags = "字典管理")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;



    @GetMapping("/web/list")
    @ApiOperation(value = "查询数据字典管理列表分页-后端管理数据字典管理", notes = "查询数据字典管理列表分页-后端管理数据字典管理")
    public ApiResponse getDictPage(@ModelAttribute DictSearchDto param){
        // Map<String, Object> param = getParams();
        return success(dictService.queryDictList(param));
    }


    @GetMapping("/web/details/list")
    @ApiOperation(value = "根据字典类型获取数据字典-后端管理数据字典管理", notes = "根据字典类型获取数据字典-后端管理数据字典管理")
    public ApiResponse getDictListByType(@RequestParam String type){
        return success(dictService.queryDictListByType(type));
    }


    @GetMapping(value = "/web/info/{id}")
    @ApiOperation(value = "获取字典-后端管理数据字典管理", notes = "获取字典-后端管理数据字典管理")
    public ApiResponse getDict(@ApiParam(value = "字典id",required = true)@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return fail("id不能为空");
        }
        DictVo dict = dictService.queryDictById(id);
        if (null == dict){
            return fail("未查找到该字典");
        }else{
            return success(dict);
        }
    }

    @PostMapping(value = "/web/save")
    @ApiOperation(value = "新增数据字典管理-后端管理数据字典管理", notes = "新增数据字典管理-后端管理数据字典管理")
    public ApiResponse saveDict(@RequestBody DictDto dictDto){
        if (dictService.saveOrUpdateDict(dictDto)){
            return success("保存成功");
        }else {
            return fail("保存失败");
        }
    }

    @PutMapping(value = "/web/update")
    @ApiOperation(value = "修改数据字典管理-后端管理数据字典管理", notes = "修改数据字典管理-后端管理数据字典管理")
    public ApiResponse updateDict(@RequestBody DictDto dictDto){
        if (dictService.saveOrUpdateDict(dictDto)){
            return success("修改成功");
        }else {
            return fail("修改失败");
        }
    }

    @DeleteMapping(value = "/web/del/{id}")
    @ApiOperation(value = "删除数据字典管理-后端管理数据字典管理", notes = "删除数据字典管理-后端管理数据字典管理")
    public ApiResponse deleteDict(@ApiParam(value = "字典id",required = true)@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return fail("id不能为空");
        }
        if (dictService.deleteDict(id)){
            return success("删除成功");
        }else {
            return fail("删除失败");
        }
    }


    @DeleteMapping(value = "/web/delBatch")
    @ApiOperation(value = "批量删除数据字典管理-后端管理数据字典管理", notes = "批量删除数据字典管理-后端管理数据字典管理")
    public ApiResponse deleteBatchDict(@RequestBody String ids){
        if (StringUtils.isBlank(ids)) {
            return fail("参数不能为空");
        }
        List<String> idList = JSON.parseArray(((JSONArray) JSON.parseObject(ids).get("ids")).toJSONString(), String.class);
        if (dictService.deleteBatchDict(idList)){
            return success("批量删除成功");
        }else {
            return fail("批量删除失败");
        }
    }

}
