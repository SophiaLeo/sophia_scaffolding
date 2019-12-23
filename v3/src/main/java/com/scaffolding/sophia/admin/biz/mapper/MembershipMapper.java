package com.scaffolding.sophia.admin.biz.mapper;

import com.scaffolding.sophia.admin.api.entity.bo.Membership;
import com.scaffolding.sophia.admin.api.entity.bo.MembershipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MembershipMapper {
    long countByExample(MembershipExample example);

    int deleteByExample(MembershipExample example);

    int deleteByPrimaryKey(String id);

    int insert(Membership record);

    int insertSelective(Membership record);

    List<Membership> selectByExample(MembershipExample example);

    Membership selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Membership record, @Param("example") MembershipExample example);

    int updateByExample(@Param("record") Membership record, @Param("example") MembershipExample example);

    int updateByPrimaryKeySelective(Membership record);

    int updateByPrimaryKey(Membership record);
}