package com.scaffolding.sophia.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.mapper
 * @ClassName: ApiLoggerMapper
 * @Date: 2019/12/23 14:36
 * @Description:
 * @Version: 1.0
 */
@Mapper
public interface ApiLoggerMapper extends BaseMapper<ApiLogger> {
}
