package com.scaffolding.sophia.common.base.exception;

import com.scaffolding.sophia.common.base.enums.SophiaHttpStatus;

/**
 * @author: LHL
 * @ProjectName: sophia_scaffolding
 * @Package: com.scaffolding.sophia.common.base.exception
 * @ClassName: CommonException
 * @Description: 自定义公共运行时异常
 * @Version: 1.0
 */
public class CommonException extends RuntimeException {

    public Integer code;

    public String msg;

    public CommonException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonException(String msg) {
        super(msg);
        this.code = SophiaHttpStatus.COMMON_FAIL.getCode();
        this.msg = msg;
    }

    public CommonException() {
        super(SophiaHttpStatus.COMMON_FAIL.getMessage());
        this.code = SophiaHttpStatus.COMMON_FAIL.getCode();
        this.msg = SophiaHttpStatus.COMMON_FAIL.getMessage();
    }
    public CommonException(SophiaHttpStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.msg = status.getMessage();
    }

    public CommonException(Throwable cause) {
        super(cause);
    }


}
