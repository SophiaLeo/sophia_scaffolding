package com.scaffolding.sophia.admin.api.entity.dto;

import com.scaffolding.sophia.common.base.dto.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.dto
 * @ClassName: RoleSearchDto
 * @Description:
 * @Version: 1.0
 */
@Data
@ApiModel(value = "RoleSearchDto", description = "角色搜索")
public class RoleSearchDto extends PageDto {

    @ApiModelProperty("角色名")
    private String roleName;

}
