package com.scaffolding.sophia.common.base.constants;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.base.constants
 * @ClassName: GlobalsConstants
 * @Description: 系统常量
 * @Version: 1.0
 */
public class GlobalsConstants {

    /**
     * jwt对称加密
     * */
    public static final String OAUTH_SIGNING_KEY = "sophia_oauth_key";

    public static final String OAUTH_AUTH_FORM_URI = "/authentication/form";

    public static final String OAUTH_AUTH_REQUIRE_URI = "/authentication/require";

    /**
     * 登录页面
     */
    public static final String LOGIN_PAGE_URI = "/index.html";


    /**
     * Redis Cache
     */
    public static final String REDIS_USER_CACHE = "RedisUserCache";

    /**
     * Redis Cache
     */
    public static final String REDIS_CLIENT_CACHE = "RedisClientCache";

    /**
     * 缓存中user的key
     */
    public static final String USER_KEY_PREFIX = "SophiaUser_";

    /**
     * oauth 客户端信息
     */
    public static final String CLIENT_DETAILS_KEY =  "SophiaClient_";

    /**
     * Redis默认过期时长，单位：秒  5分钟
     */
    public static final long DEFAULT_EXPIRE = 60 * 5;

    /**
     * Redis 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    /**
     * Redis set 前缀
     */
    public static final String KEY_SET_PREFIX = "_set:";

    /**
     * Redis list 前缀
     */
    public static final String KEY_LIST_PREFIX = "_list:";

    /**
     * ip
     */
    public static final String UNKNOWN = "unknown";

    /**
     * druid配置
     */
    public static final String DB_PREFIX = "spring.datasource";

    /**
     *  sophia security配置
     */
    public static final String SOPHIA_OAUTH_PREFIX = "sophia.security";
    /**
     * oauth security配置
     */
    public static final String OAUTH_SECURITY_PREFIX = "security.oauth2.client";

    /**
     * security  过滤url 配置
     */
    public static final String FILTER_IGNORE = "ignore";

    /**
     * security  过滤url 配置
     */
    public static final String SOPHIA_RESOURCE_IDS = "sophia.resource";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    public static final Integer FAIL = 1;

    /**
     * 前缀
     */
    public static final String PROJECT_PREFIX = "sophia_";

    /**
     * oauth 相关前缀
     */
    public static final String  OAUTH_PREFIX = "oauth:";

    public static final String CURRENT = "current";

    public static final String SIZE = "size";

}
