package com.scaffolding.sophia.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffolding.sophia.admin.api.entity.bo.Role;
import com.scaffolding.sophia.admin.api.entity.bo.RolePermission;
import com.scaffolding.sophia.admin.api.entity.bo.UserRole;
import com.scaffolding.sophia.admin.api.entity.dto.RoleDto;
import com.scaffolding.sophia.admin.api.entity.dto.RoleSearchDto;
import com.scaffolding.sophia.admin.api.entity.vo.RoleVo;
import com.scaffolding.sophia.admin.biz.mapper.RoleMapper;
import com.scaffolding.sophia.admin.biz.mapper.RolePermissionMapper;
import com.scaffolding.sophia.admin.biz.mapper.UserRoleMapper;
import com.scaffolding.sophia.admin.biz.service.RoleService;
import com.scaffolding.sophia.common.base.constants.BizConstants;
import com.scaffolding.sophia.common.base.exception.CommonException;
import com.scaffolding.sophia.common.security.util.UserUtils;
import com.scaffolding.sophia.common.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.admin.biz.service.role.impl
 * @ClassName: RoleServiceImpl
 * @Date: 2019/11/5 09:28
 * @Description:
 * @Version: 1.0
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Role getRoleByUserId(String userId) {
        return baseMapper.getRoleByUserId(userId);
    }

    @Override
    public IPage<RoleVo> queryRoleList(RoleSearchDto param) {
        Integer currentPage = param.getCurrentPage() == null ? 1 : param.getCurrentPage();
        Integer pageSize = param.getPageSize() == null ? 10 : param.getPageSize();
        Page<RoleVo> page = new Page<>(currentPage, pageSize);
        return page.setRecords(baseMapper.selectRoleList(page, param));
    }

    @Override
    public RoleVo queryRoleById(String id) {
        return new RoleVo().buildVo(baseMapper.selectById(id));
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public Map<String, Object> saveOrUpdateRole(RoleDto roleDto) {
        Map<String, Object> map = new HashMap<>(16);
        if (StringUtils.isBlank(roleDto.getUserId())) {
            roleDto.setUserId(UserUtils.getLoginUser().getId());
        }
        Role role = new Role();
        role.buildBo(roleDto);
        if (StringUtils.isBlank(roleDto.getId())) {
            Role code = baseMapper.selectOne(new QueryWrapper<Role>().eq("ROLE_CODE", role.getRoleCode()));
            if (null != code) {
                map.put("msg", "该角色编码已存在");
                map.put("flag", false);
                return map;
            }
            role.setId(UuidUtils.getUuid());
            role.setCreateTime(LocalDateTime.now());
            role.setCreateUser(roleDto.getUserId());
            role.setStatus(BizConstants.YES);
            int i = baseMapper.insert(role);
            if (i > 0) {
                map.put("msg", "保存成功");
                map.put("flag", true);
            } else {
                map.put("msg", "保存失败");
                map.put("flag", false);
            }
            return map;
        } else {
            Role code = baseMapper.selectOne(new QueryWrapper<Role>().eq("ROLE_CODE", role.getRoleCode()).ne("ID", role.getId()));
            if (null != code) {
                map.put("msg", "该角色编码已存在");
                map.put("flag", false);
                return map;
            }
            role.setUpdateTime(LocalDateTime.now());
            role.setUpdateUser(roleDto.getUserId());
            int i = baseMapper.updateById(role);
            if (i > 0) {
                map.put("msg", "修改成功");
                map.put("flag", true);
            } else {
                map.put("msg", "修改失败");
                map.put("flag", false);
            }
            return map;
        }
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public Map<String, Object> deleteRole(String id) {
        Map<String, Object> map = new HashMap<>(16);
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("ROLE_ID", id).ne("USER_ID", UserUtils.getLoginUser().getId()));
        if (userRoles.size() > 0) {
            map.put("msg", "该角色已有其他用户关联");
            map.put("flag", false);
            return map;
        }
        Role role = new Role();
        role.setId(id);
        role.setStatus(BizConstants.NO);
        role.setUpdateTime(LocalDateTime.now());
        role.setUpdateUser(UserUtils.getLoginUser().getId());
        int i = baseMapper.updateById(role);
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("ROLE_ID", id));
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("ROLE_ID", id));
        if (i > 0) {
            map.put("msg", "删除成功");
            map.put("flag", true);
        } else {
            map.put("msg", "删除失败");
            map.put("flag", false);
        }
        return map;
    }

    @Transactional(rollbackFor = CommonException.class)
    @Override
    public Map<String, Object> deleteBatchRole(List<String> ids) {
        Map<String, Object> map = new HashMap<>(16);
        ids.parallelStream().forEach(
                x -> {
                    List<UserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("ROLE_ID", x).ne("USER_ID", UserUtils.getLoginUser().getId()));
                    if (userRoleList.size() > 0) {
                        map.put("msg", "选取的其中角色已有其他用户关联");
                        map.put("flag", false);
                    }
                }
        );
        if (map.size() > 0) {
            return map;
        }
        int i = baseMapper.updateBatchByIds(ids);
        rolePermissionMapper.deleteBatchByRoleIds(ids);
        userRoleMapper.deleteBatchByRoleIds(ids);
        if (i > 0) {
            map.put("msg", "批量删除成功");
            map.put("flag", true);
        } else {
            map.put("msg", "批量删除失败");
            map.put("flag", false);
        }
        return map;
    }

    @Override
    public List<RoleVo> queryRole(String userId) {
        if (StringUtils.isBlank(userId)) {
            userId = UserUtils.getLoginUser().getId();
        }
        Role role = baseMapper.getRoleByUserId(userId);
        return baseMapper.selectRole(role.getRoleCode());
        // return baseMapper.selectRole(null);
    }
}
