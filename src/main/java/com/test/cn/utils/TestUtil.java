package com.test.cn.utils;


import com.google.common.collect.Sets;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static void main(String[] agrs){
        String[] arr = {"欢迎","关注","Java"};
        List list = Arrays.asList(arr);
        arr[1] = "爱上";
        list.add(2,"我");
        System.out.println(Arrays.toString(arr));
        System.out.println(list.toString());
    }
}
