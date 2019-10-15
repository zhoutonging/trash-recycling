package com.mengzhou.trashrecycling;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ZHOUTONG
 * @date 2019年09月15日 10:32
 */
public class Test1 {
    @Value("${file.basePhysicPath}")
    static String staticAccessPath;

    public static void main(String[] args) {
       String path =  "D:/file/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
            System.err.println("不存在,以创建");
        } else {
            System.err.println("存在");
        }
    }


}
