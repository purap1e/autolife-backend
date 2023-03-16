package kz.auto_life.paymentservice.generics;

import java.util.ArrayList;
import java.util.List;

public class Rest<T> {
    List<T> list = new ArrayList<T>();

    public void populate(List<T> t){
        list.addAll(t);
    }
}
