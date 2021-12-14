package com.simon.oct.common.core.util;

import com.simon.oct.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public static <T> R<T> ok() {
        return restResult(CommonConstants.SUCCESS, "success", null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(CommonConstants.SUCCESS, "success", data);
    }

    public static <T> R<T> ok(String msg, T data) {
        return restResult(CommonConstants.SUCCESS, msg, data);
    }

    public static <T> R<T> error() {
        return restResult(CommonConstants.FAIL, "", null);
    }

    public static <T> R<T> error(String msg) {
        return restResult(CommonConstants.FAIL, msg, null);
    }

    public static <T> R<T> error(T data) {
        return restResult(CommonConstants.FAIL, "", data);
    }

    public static <T> R<T> error(String msg, T data) {
        return restResult(CommonConstants.FAIL, msg, data);
    }

    private static <T> R<T> restResult(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);

        return r;
    }


}
