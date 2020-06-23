package com.scaffolding.sophia.admin.api.entity.vo;

import com.scaffolding.sophia.admin.api.entity.bo.Permission;
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
 * @ClassName: PermissionVo
 * @Date: 2019/12/20 13:52
 * @Description:
 * @Version: 1.0
 */
@Data
public class PermissionVo implements Serializable {

    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 路径
     */
    private String urlPath;

    /**
     * 父级权限
     */
    private String pid;

    /**
     * 菜单类型 （0菜单 1按钮）
     */
    private Integer type;

    /**
     * 图标
     */
    private String iconPath;

    /**
     * 排序
     */
    private Integer sort;


    private String pidName;



    /**
     * bo转vo
     * @param permission
     * @return
     */
    public PermissionVo buildVo(Permission permission){
        BeanUtils.copyProperties(permission,this);
        return this;
    }

    /**
     * bo转vo
     * @param list
     * @return
     */
    public List<PermissionVo> buildVoList(List<Permission> list){
        List<PermissionVo> voList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return voList;
        }
        list.forEach(item ->{
            PermissionVo vo = new PermissionVo();
            BeanUtils.copyProperties(item,vo);
            voList.add(vo);
        });
        return voList;
    }
}
