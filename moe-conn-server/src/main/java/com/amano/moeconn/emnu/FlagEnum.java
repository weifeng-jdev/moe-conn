package com.amano.moeconn.emnu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlagEnum {
   OPEN(1),CLOSE(0);
   private final Integer flag;
}
