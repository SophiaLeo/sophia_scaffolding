package com.scaffolding.sophia.admin.biz.mapper;

import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import com.scaffolding.sophia.admin.api.entity.bo.ApiLoggerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiLoggerMapper {
    long countByExample(ApiLoggerExample example);

    int deleteByExample(ApiLoggerExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApiLogger record);

    int insertSelective(ApiLogger record);

    List<ApiLogger> selectByExample(ApiLoggerExample example);

    ApiLogger selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApiLogger record, @Param("example") ApiLoggerExample example);

    int updateByExample(@Param("record") ApiLogger record, @Param("example") ApiLoggerExample example);

    int updateByPrimaryKeySelective(ApiLogger record);

    int updateByPrimaryKey(ApiLogger record);
}