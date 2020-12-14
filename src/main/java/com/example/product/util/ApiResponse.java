package com.example.product.util;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T payload;

    public static<T> ApiResponse<T> ofSuccess(int code, String message, T payload) {
        return new ApiResponse<>(true, code, message, payload);
    }

    public static <T> ApiResponse<T> ofFailure(int code, String message) {
        return new ApiResponse<>(false, code, message, null);
    }
}
