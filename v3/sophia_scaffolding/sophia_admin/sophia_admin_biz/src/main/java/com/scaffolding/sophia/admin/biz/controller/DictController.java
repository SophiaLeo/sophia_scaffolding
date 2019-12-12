package com.scaffolding.sophia.admin.biz.controller;

import com.scaffolding.sophia.admin.biz.service.dict.DictService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DictController {

    @Autowired
    private DictService dictService;


}
