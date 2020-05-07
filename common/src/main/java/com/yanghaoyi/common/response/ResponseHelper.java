package com.yanghaoyi.common.response;

/**
 * Created by 浩艺 on 2020/4/27.
 */
public class ResponseHelper {
    protected static final int SUCCESS = 0;
    protected static final int NO_PERMISSION = 2;
    protected static final int NOT_FOUND = 5;

    private ResponseHelper() {
    }

    public static <T> Response<T> createSuccessResponse() {
        return createResponse(0, (String)null);
    }

    public static <T> Response<T> createSuccessResponse(T payload) {
        Response<T> response = createResponse(0, (String)null);
        response.setPayload(payload);
        return response;
    }

    public static <T> Response<T> createResponse(int code, String description) {
        Response<T> response = new Response();
        response.setCode(code);
        if(description != null) {
            response.setDescription(description);
        }

        return response;
    }

    public static <T> Response<T> createNotFoundResponse(T payload) {
        Response<T> response = createResponse(5, "Data not found!");
        response.setPayload(payload);
        return response;
    }

    public static <T> Response<T> createNotFoundResponse() {
        return createResponse(5, "Data not found!");
    }

    public static <T> Response<T> createNoPermissionResponse() {
        return createResponse(2, "No Permission!");
    }
}
