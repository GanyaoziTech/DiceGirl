package tech.ganyaozi.warframe.stat.util;

import lombok.Builder;
import lombok.Data;

/**
 * @author Derek
 */
@Data
@Builder
public class ApiResponse {

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAIL_UNKNOWN = 1;
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAIL = "fail";
    String msg;
    Integer code;
    Object data;

    public static ApiResponse newSuccess(Object data) {
        return new ApiResponseBuilder().msg(MSG_SUCCESS).data(data).code(CODE_SUCCESS).build();
    }

    public static ApiResponse newFail(Object data) {
        return new ApiResponseBuilder().msg(MSG_FAIL).data(data).code(CODE_FAIL_UNKNOWN).build();
    }

}
