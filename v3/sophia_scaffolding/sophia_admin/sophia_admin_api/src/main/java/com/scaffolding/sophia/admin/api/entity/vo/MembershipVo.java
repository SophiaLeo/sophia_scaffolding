package com.scaffolding.sophia.admin.api.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.scaffolding.sophia.admin.api.entity.bo.Membership;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.api.entity.vo
 * @ClassName: MembershipVo
 * @Date: 2019/12/20 13:55
 * @Description:
 * @Version: 1.0
 */
@Data
public class MembershipVo implements Serializable {

    /**
     * 登录用户id
     */
    private String userId;

    /**
     * id
     * */
    private String id;

    /**
     * 用户头像
     */
    private String imgPath;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 1:男 2:女
     */
    private Integer sex;

    /**
     * 生日
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 住址
     */
    private String address;

    /**
     *   角色名称
     */
    private String roleName;

    /**
     * bo转vo
     * @param membership
     * @return
     */
    public MembershipVo buildVo(Membership membership){
        BeanUtils.copyProperties(membership,this);
        return this;
    }

    /**
     * bo转vo
     * @param list
     * @return
     */
    public List<MembershipVo> buildVoList(List<Membership> list){
        List<MembershipVo> voList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return voList;
        }
        list.forEach(item ->{
            MembershipVo vo = new MembershipVo();
            BeanUtils.copyProperties(item,vo);
            voList.add(vo);
        });
        return voList;
    }
}
