package com.amano.moeconn.emnu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum FlagEnum {
   OPEN(1),CLOSE(0);
   private Integer flag;
}
