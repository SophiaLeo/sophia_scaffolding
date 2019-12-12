package com.scaffolding.sophia.admin.biz.service.dict.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.dict.Dict;
import com.scaffolding.sophia.admin.biz.dao.dict.DictMapper;
import com.scaffolding.sophia.admin.biz.service.dict.DictService;
import org.springframework.stereotype.Service;

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


}
