package com.wtu.forum.utils;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName CollectionUtils
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/7 10:44
 * @Version 1.0
 **/
public class CollectionUtils {

    /**
     * set转list工具
     * @param set
     * @return
     */
    public static List<Integer> setToList(Set<Integer> set){
        List<Integer> list  = set.stream().map(s->{
            return s;
        }).collect(Collectors.toList());
        return list;

    }

    public static void main(String[] args) {
        setToList(new HashSet<Integer>(){{add(1);add(2);}}).forEach(s->{
            System.out.println(s);
        });
    }


}
