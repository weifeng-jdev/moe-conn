package com.amano.moeconn.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessLimitRejectException extends RuntimeException {
    private Integer errorCode = 500;
    private final String MSG = "操作太快啦！休息一会再试试吧！";

    @Override
    public String getMessage() {
        return this.MSG;
    }
}
