package com.amano.moeconn.exception;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BizException extends RuntimeException{
    private Integer errorCode;
    private String errorMsg;

    @Override
    public String getMessage() {
        return this.errorMsg;
    }
}
