package com.scaffolding.sophia.admin.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scaffolding.sophia.admin.api.entity.dto.DictDto;
import com.scaffolding.sophia.admin.api.entity.dto.DictSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.DictVo;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service
 * @ClassName: DictService
 * @Date: 2019/11/5 10:55
 * @Description:
 * @Version: 1.0
 */
public interface DictService {

    /**
     * 分页条件查询字典列表
     *
     * @param param 分页条件
     * @return IPage<DictVo>
     */
    IPage<DictVo> queryDictList(DictSearchDto param);

    /**
     * 获取字典
     *
     * @param id 字典id
     * @return DictVo
     */
    DictVo queryDictById(String id);

    /**
     * 逻辑删除字典
     *
     * @param id 字典id
     * @return boolean
     */
    boolean deleteDict(String id);

    /**
     * 逻辑批量删除字典
     *
     * @param ids 字典id
     * @return boolean
     */
    boolean deleteBatchDict(List<String> ids);

    /**
     * 保存或修改字典
     *
     * @param dictDto 字典信息
     * @return boolean
     */
    boolean saveOrUpdateDict(DictDto dictDto);

    /**
     * 根据字典类型获取字典
     *
     * @param type 字典类型
     * @return List<DictVo>
     */
    List<DictVo> queryDictListByType(String type);
}
