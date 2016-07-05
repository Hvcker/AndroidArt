package com.hk.event;

import com.hvcker.doc.base.aty.ButtonListActivity;
import com.hvcker.doc.base.aty.HvckerActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends ButtonListActivity {

    @Override
    public Map<String, Class<? extends HvckerActivity>> getAtyMappings() {
        Map<String, Class<? extends HvckerActivity>> atys = new LinkedHashMap<>();
        atys.put("Get the DecorView and it's Children", DecorViewTestAty.class);
        return atys;
    }
}
