package com.scaffolding.sophia.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scaffolding.sophia.admin.api.entity.bo.Dept;
import com.scaffolding.sophia.admin.api.entity.dto.CompanySearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.DeptVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * @return List<DeptVo>
     */
    List<DeptVo> selectByDeptId(@Param("deptId") String deptId);

    /**
     * 公司分页列表
     *
     * @param page  分页
     * @param param 条件
     * @return List<DeptVo>
     */
    List<DeptVo> findCompanyList(Page<DeptVo> page, @Param("param") CompanySearchDto param);

}
