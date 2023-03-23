package com.ppphuang.wechatapi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListUtil {

    public static List getRandomList(List list, int num) {
        List olist = new ArrayList<>();
        if (list.size() <= num) {
            return list;
        } else {
            Random random = new Random();
            for (int i = 0 ;i<num;i++){
                int intRandom = random.nextInt(list.size() - 1);
                olist.add(list.get(intRandom));
                list.remove(list.get(intRandom));
            }
            return olist;
        }
    }
}
