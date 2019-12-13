package com.scaffolding.sophia.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用熔断器
 */
@RestController
@RequestMapping("common")
public class FallbackController {

    Logger logger = LoggerFactory.getLogger(FallbackController.class);

    @RequestMapping("fallback")
    public Map fallback() {
        Map map = new HashMap<>();
        map.put("code", 504);
        map.put("msg", "请求超时，请稍后再试");
        logger.debug("msg : {}",map.get("msg"));
        return map;
    }
}
