package com.scaffolding.sophia.admin.biz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: LHL
 * @ProjectName: scaffolding-sophia
 * @Package: com.scaffolding.sophia.admin.biz.config
 * @ClassName: FileConfig
 * @Description:
 * @Version: 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileConfig {

    /**
     * 绝对路径
     */
    private String fileActualPath;
    /**
     * 相对路径
     */
    private String fileVirtualPath;
}
