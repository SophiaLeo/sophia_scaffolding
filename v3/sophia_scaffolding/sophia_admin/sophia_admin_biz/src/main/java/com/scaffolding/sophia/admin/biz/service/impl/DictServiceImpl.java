package com.scaffolding.sophia.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.bo.Dict;
import com.scaffolding.sophia.admin.api.entity.dto.DictDto;
import com.scaffolding.sophia.admin.api.entity.dto.DictSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.DictVo;
import com.scaffolding.sophia.admin.biz.mapper.DictMapper;
import com.scaffolding.sophia.admin.biz.service.DictService;
import com.scaffolding.sophia.common.base.constants.BizConstants;
import com.scaffolding.sophia.common.base.exception.CommonException;
import com.scaffolding.sophia.common.security.util.UserUtils;
import com.scaffolding.sophia.common.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.dict.impl
 * @ClassName: DictServiceImpl
 * @Date: 2019/11/5 10:56
 * @Description:
 * @Version: 1.0
 */
@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {


    @Override
    public IPage<DictVo> queryDictList(DictSearchDto param) {
        Integer currentPage = param.getCurrentPage() == null ? 1 : param.getCurrentPage();
        Integer pageSize = param.getPageSize() == null ? 10 : param.getPageSize();
        Page<DictVo> page = new Page<>(currentPage, pageSize);
        return page.setRecords(baseMapper.selectDictList(page, param));
    }

    @Override
    public DictVo queryDictById(String id) {
        DictVo dictVo = new DictVo().buildVo(baseMapper.selectById(id));
        List<DictVo> childrenDict = baseMapper.selectByPid(id);
        dictVo.setChildren(childrenDict);
        return dictVo;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean deleteDict(String id) {
        return baseMapper.updateByIdOrPid(id) > 0;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean deleteBatchDict(List<String> ids) {
        return baseMapper.updateBatchByIds(ids) > 0;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public boolean saveOrUpdateDict(DictDto dictDto) {
        if (StringUtils.isBlank(dictDto.getUserId())) {
            dictDto.setUserId(UserUtils.getLoginUser().getId());
        }
        Dict dict = new Dict();
        dict.buildBo(dictDto);
        if (StringUtils.isBlank(dictDto.getId())) {
            String uuid = UuidUtils.getUuid();
            dict.setId(uuid);
            dict.setPid(BizConstants.NONE_NODE);
            dict.setStatus(BizConstants.YES);
            dict.setCreateTime(LocalDateTime.now());
            dict.setCreateUser(dictDto.getUserId());
            List<Dict> dictList = new ArrayList<>();
            if (dictDto.getChildren().size() > 0) {
                dictList = dictDto.getChildren().stream().parallel().map(x -> {
                    Dict d = new Dict();
                    d.setId(UuidUtils.getUuid());
                    d.setName(x.getName());
                    d.setValue(x.getValue());
                    d.setPid(uuid);
                    d.setStatus(BizConstants.YES);
                    d.setCreateTime(LocalDateTime.now());
                    d.setCreateUser(dictDto.getUserId());
                    return d;
                }).collect(Collectors.toList());
            }
            dictList.add(dict);
            return baseMapper.insertBatch(dictList) > 0;
        } else {
            AtomicBoolean flag = new AtomicBoolean(false);
            dict.setUpdateTime(LocalDateTime.now());
            dict.setUpdateUser(dictDto.getUserId());
            int b = baseMapper.updateById(dict);
            if (b > 0) {
                flag.set(true);
            } else {
                return false;
            }
            if (dictDto.getChildren().size() > 0) {
                dictDto.getChildren().forEach(x -> {
                    Dict d = new Dict();
                    d.setId(x.getId());
                    d.setName(x.getName());
                    d.setValue(x.getValue());
                    d.setPid(dict.getId());
                    d.setUpdateTime(LocalDateTime.now());
                    d.setUpdateUser(dictDto.getUserId());
                    int i = baseMapper.updateById(d);
                    if (i > 0) {
                        flag.set(true);
                    } else {
                        flag.set(false);
                    }
                });
            }
            return flag.get();
        }
    }

    @Override
    public List<DictVo> queryDictListByType(String type) {
        return baseMapper.selectDictListByType(type);
    }
}
