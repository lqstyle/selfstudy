package com.example.jol;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangqing
 * @since 2020/12/19 15:48
 */

@Data
public class BigBase {

    private List<Base> baseList = new ArrayList<>();

    private Long l ;

    private long s ;
}
