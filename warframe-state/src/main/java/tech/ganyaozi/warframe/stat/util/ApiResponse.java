package tech.ganyaozi.warframe.stat.util;

import lombok.Builder;
import lombok.Data;

/**
 * @author Derek
 */
@SuppressWarnings("unused")
@Data
@Builder
public class ApiResponse {

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAIL_UNKNOWN = 1;
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAIL = "fail";

    String error;
    Integer code;
    Object data;

    public ApiResponse() {
    }

    public ApiResponse(String error, Integer code, Object data) {
        this.error = error;
        this.code = code;
        this.data = data;
    }

    public static ApiResponse newSuccess(String msg, Object data) {
        return new ApiResponseBuilder().error(msg).data(data).code(CODE_SUCCESS).build();
    }

    public static ApiResponse newSuccess(Object data) {
        return newSuccess(MSG_SUCCESS, data);
    }

    public static ApiResponse newFail(String msg, Object data) {
        return new ApiResponseBuilder().error(msg).data(data).code(CODE_FAIL_UNKNOWN).build();
    }

    public static ApiResponse newFail(Object data) {
        return newFail(MSG_FAIL, data);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "error='" + error + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
