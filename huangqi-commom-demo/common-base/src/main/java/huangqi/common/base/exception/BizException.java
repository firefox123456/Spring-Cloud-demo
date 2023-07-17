package huangqi.common.base.exception;

import huangqi.common.base.result.IResultCode;
import lombok.Getter;

/**
 * 全局异常类
 *
 * @author "黄骐"
 * @date 2023/07/16 18:58
 **/
@Getter
public class BizException extends RuntimeException {

    public IResultCode resultCode;

    public BizException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.resultCode = errorCode;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }
}
