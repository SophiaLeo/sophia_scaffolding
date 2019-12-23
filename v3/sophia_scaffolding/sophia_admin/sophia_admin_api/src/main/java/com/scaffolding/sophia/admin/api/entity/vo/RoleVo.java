package com.scaffolding.sophia.admin.api.entity.vo;

import com.scaffolding.sophia.admin.api.entity.bo.Role;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.vo
 * @ClassName: RoleVo
 * @Date: 2019/12/20 13:51
 * @Description:
 * @Version: 1.0
 */
@Data
public class RoleVo implements Serializable {

    /**
     * id
     * */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 角色类型 1:后台角色 0:前台角色
     */
    private Integer roleType;


    /**
     * bo转vo
     * @param role
     * @return
     */
    public RoleVo buildVo(Role role){
        BeanUtils.copyProperties(role,this);
        return this;
    }

    /**
     * bo转vo
     * @param list
     * @return
     */
    public List<RoleVo> buildVoList(List<Role> list){
        List<RoleVo> voList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return voList;
        }
        list.forEach(item ->{
            RoleVo vo = new RoleVo();
            BeanUtils.copyProperties(item,vo);
            voList.add(vo);
        });
        return voList;
    }
}
