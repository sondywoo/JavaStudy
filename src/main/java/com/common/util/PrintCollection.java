package com.common.util;

import java.util.Collection;
import java.util.Iterator;

public class PrintCollection {
    public static void print(Collection<?> c){
        if(c==null) return;
//        Iterator it = c.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next());
//        }
        for(Object o:c){
            System.out.println(o);
        }
    }
}
