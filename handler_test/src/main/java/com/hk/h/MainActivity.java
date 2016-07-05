package com.hk.h;

import com.hk.h.a.ThreadLocalTestActivity;
import com.hvcker.doc.base.aty.ButtonListActivity;
import com.hvcker.doc.base.aty.HvckerActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends ButtonListActivity {

    @Override
    public Map<String, Class<? extends HvckerActivity>> getAtyMappings() {
        Map<String,Class<? extends HvckerActivity>> atys = new LinkedHashMap<>();
        atys.put("ThreadLocal", ThreadLocalTestActivity.class);
        return atys;
    }
}
