package com.scaffolding.sophia.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.bo.ApiLogger;
import com.scaffolding.sophia.admin.api.entity.vo.ApiLoggerVo;
import com.scaffolding.sophia.admin.biz.mapper.ApiLoggerMapper;
import com.scaffolding.sophia.admin.biz.service.ApiLoggerService;
import com.scaffolding.sophia.common.base.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.impl
 * @ClassName: ApiLoggerServiceImpl
 * @Date: 2019/12/23 14:37
 * @Description:
 * @Version: 1.0
 */
@Service
public class ApiLoggerServiceImpl extends ServiceImpl<ApiLoggerMapper, ApiLogger> implements ApiLoggerService {

    @Override
    public IPage<ApiLoggerVo> queryApiLoggerList(Map<String, Object> param) {
        Integer currentPage = param.get("currentPage") == null ? 1 : Integer.parseInt(String.valueOf(param.get("currentPage")));
        Integer pageSize = param.get("pageSize") == null ? 10 : Integer.parseInt(String.valueOf(param.get("pageSize")));
        Page<ApiLoggerVo> result = new Page<>(currentPage, pageSize);
        Page<ApiLogger> page = new Page<>(currentPage, pageSize);
        QueryWrapper<ApiLogger> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank((String)param.get("userName")), "USER_NAME", (String)param.get("userName"));
        queryWrapper.like(StringUtils.isNotBlank((String)param.get("method")), "METHOD", (String)param.get("method"));
        queryWrapper.like(StringUtils.isNotBlank((String)param.get("methodName")), "METHOD_NAME", (String)param.get("methodName"));
        queryWrapper.ge(null != (LocalDateTime)param.get("startTime"), "CREATE_TIME", (String)param.get("startTime"));
        queryWrapper.le(null != (LocalDateTime)param.get("endTime"), "CREATE_TIME", (String)param.get("endTime"));
        queryWrapper.orderByDesc("CREATE_TIME");
        IPage<ApiLogger> iPage = baseMapper.selectPage(page, queryWrapper);
        BeanUtils.copyProperties(iPage, result);
        return result.setRecords(new ApiLoggerVo().buildVoList(iPage.getRecords()));
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean deleteApiLogger(String id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean deleteBatchApiLogger(List<String> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public boolean saveLog(ApiLogger apiLogger) {
        return baseMapper.insert(apiLogger) > 0;
    }
}
