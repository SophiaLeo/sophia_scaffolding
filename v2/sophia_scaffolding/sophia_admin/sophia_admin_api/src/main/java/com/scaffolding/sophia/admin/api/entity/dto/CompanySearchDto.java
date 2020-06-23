package com.scaffolding.sophia.admin.api.entity.dto;

import com.scaffolding.sophia.common.base.dto.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.dto
 * @ClassName: CompanySearchDto
 * @Description:
 * @Version: 1.0
 */
@Data
@ApiModel(value = "CompanySearchDto", description = "公司搜索")
public class CompanySearchDto extends PageDto {


    @ApiModelProperty("公司全名或简名")
    private String name;

    @ApiModelProperty("地址")
    private String address;

}
