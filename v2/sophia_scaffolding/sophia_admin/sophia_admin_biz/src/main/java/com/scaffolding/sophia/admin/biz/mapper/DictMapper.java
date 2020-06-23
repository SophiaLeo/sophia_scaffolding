package com.scaffolding.sophia.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scaffolding.sophia.admin.api.entity.bo.Dict;
import com.scaffolding.sophia.admin.api.entity.dto.DictSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.DictVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.dao.dict
 * @ClassName: DictMapper
 * @Date: 2019/11/5 11:02
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 根据字典类型获取字典
     *
     * @param type 字典类型
     * @return List<DictVo>
     */
    List<DictVo> selectDictListByType(String type);

    /**
     * 获取字典
     *
     * @param id 字典id
     * @return List<DictVo>
     */
    List<DictVo> selectByPid(String id);

    /**
     * 分页条件查询字典列表
     *
     * @param param 分页条件
     * @return List<DictVo>
     */
    List<DictVo> selectDictList(@Param("page") Page<DictVo> page, @Param("param") DictSearchDto param);

    /**
     * 逻辑删除
     *
     * @param id 字典id
     * @return int
     */
    int updateByIdOrPid(String id);

    /**
     * 逻辑批量删除
     *
     * @param list 字典id
     * @return int
     */
    int updateBatchByIds(@Param("list") List<String> list);

    /**
     * 批量添加
     *
     * @param list 字典信息
     * @return int
     */
    int insertBatch(@Param("list") List<Dict> list);

}
