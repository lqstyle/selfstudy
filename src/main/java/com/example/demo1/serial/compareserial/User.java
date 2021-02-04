package com.example.demo1.serial.compareserial;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liangqing
 * @since 2020/11/24 14:58
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {


    private String name;

    private Integer age;

    private String sex;

    public User(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User() {
    }
}
