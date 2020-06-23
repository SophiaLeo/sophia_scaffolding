package com.scaffolding.sophia.common.base.constants;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.base.constants
 * @ClassName: BizConstants
 * @Description: 系统常量
 * @Version: 1.0
 */
public interface BizConstants {
    /**
     * 是否
     */
    Integer YES = 1;
    Integer NO = 0;
    /**
     * 用户状态
     */
    //用户状态：正常
    Integer USER_STATUS_NORMAL = 1;
    //用户状态：禁用
    Integer USER_STATUS_UNUSED = 0;
    //用户状态：锁定
    Integer USER_STATUS_LOCKED = 2;
    //用户状态：过期
    Integer USER_STATUS_EXPIRED = 3;

    /**
     * 部门,公司
     */
    Integer DEPT = 1;
    Integer COMP = 0;
    /**
     * 后台,前台
     */
    Integer HT = 1;
    Integer QT = 0;

    /**
     * 系统常量：是否
     */
    Integer SYS_YES = 0;

    Integer SYS_NO = 1;

    String NONE_NODE = "0";
}
