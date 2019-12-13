package com.scaffolding.sophia.admin.biz.dao.dept;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scaffolding.sophia.admin.api.entity.bo.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.dao.dept
 * @ClassName: DeptMapper
 * @Date: 2019/11/5 11:00
 * @Description:
 * @Version: 1.0
 */
@Repository
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 查询该用户下的部门
     *
     * @param deptId 用户部门或公司id
     * @return List<Dept>
     */
    List<Dept> selectByDeptId(@Param("deptId") Long deptId);

    /**
     * 公司分页列表
     *
     * @param page  分页
     * @param param 条件
     * @return List<Dept>
     */
    List<Dept> findCompanyList(Page<Dept> page,@Param("param") Map<String, Object> param);

    /**
     * 添加公司
     *
     * @param dept
     * @return int
     */
    int insertSelective(Dept dept);
}
