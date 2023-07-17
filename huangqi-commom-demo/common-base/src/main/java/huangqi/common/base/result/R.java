package huangqi.common.base.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import huangqi.common.base.entity.APage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 结果返回类
 *
 * @author "黄骐"
 * @date 2023/07/16 19:05
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable {
    private String code;

    private T data;

    private String msg;

    private Long total;

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        ResultCode rce = ResultCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            rce = ResultCode.SYSTEM_EXECUTION_ERROR;
        }
        return result(rce, data);
    }




    public static <T> R<T> failed() {
        return result(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMsg(), null);
    }

    public static <T> R<T> failed(String msg) {
        return result(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), msg, null);
    }

    public static <T> R<T> judge(boolean status) {
        if (status) {
            return ok();
        } else {
            return failed();
        }
    }

    public static <T> R<T> failed(IResultCode resultCode) {
        return result(resultCode.getCode(), resultCode.getMsg(), null);
    }

    public static <T> R<T> failed(IResultCode resultCode, String msg) {
        return result(resultCode.getCode(), msg, null);
    }

    private static <T> R<T> result(IResultCode resultCode, T data) {
        return result(resultCode.getCode(), resultCode.getMsg(), data);
    }

    private static <T> R<T> result(String code, String msg, T data) {
        R<T> result = new R<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }


    public static boolean isSuccess(R<?> result) {
        return result != null && ResultCode.SUCCESS.getCode().equals(result.getCode());
    }

    public static <T> R page(APage<T> page) {
        R<List<T>> result = new R<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData(page.getList());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setTotal(page.getTotal());
        return result;
    }
}
