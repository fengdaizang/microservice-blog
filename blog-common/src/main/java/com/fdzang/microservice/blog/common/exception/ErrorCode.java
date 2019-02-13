package com.fdzang.microservice.blog.common.exception;

public class ErrorCode {
    /** 操作成功 */
    public static final long SUCCESS = 0;
    
    /** 内部系统错误 */
    public static final long SYSTEM_ERROR = 1004001000;
    /** 配置系统失效 */
    public static final long ALTAIR_CONFIG_FAIL = 1004001001;
    /** token失效 */
    public static final long TOKEN_EXPIRE = 1004001002;
    /** 登录失败 */
    public static final long LOGIN_ERROR = 1004001003;
    /** 非法访问 */
    public static final long ILLEGAL_ACCESS = 1004001004;

    /** 重复数据 */
    public static final long DUPLICATE_DATA_ERROR = 1004001005;

    /** 数据重复错误 */
    public static final long DATA_DUPLICATE_ERROR = 1003001001;
    /** 数据为空错误 */
    public static final long DATA_NULL_ERROR = 1003001002;
    /** 应用状态错误 */
    public static final long APPLICATION_STATUS_ERROR = 1003001003;
    /** 密码错误 */
    public static final long PASSWORD_ERROR = 1003001004;
    /** uri错误 */
    public static final long URI_ERROR = 1003001005;

    /** 参数错误 */
    public static final long PARAM_ERROR = 1003001006;

    /** 新老密码一致错误 */
    public static final long PASSWORD_SAME_ERROR=1003001007;

    /** 用户不存在错误 */
    public static final long USER_NO_EXIST_ERROR=1003001008;

    /** 手机号不匹配错误 */
    public static final long PHONE_NO_MATCH_ERROR=1003001009;

    /** 插入数据失败 */
    public static final long INSERT_FALSE=1003001009;
}
