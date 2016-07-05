package com.hk.androidart;

import com.hk.androidart.a.AIDLA;
import com.hk.androidart.a.BinderDieA;
import com.hk.androidart.a.BinderPoolTestAty;
import com.hk.androidart.a.MessengerTestAty;
import com.hk.androidart.a.PermissionTestAty;
import com.hvcker.doc.base.aty.ButtonListActivity;
import com.hvcker.doc.base.aty.HvckerActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends ButtonListActivity {

    @Override
    public Map<String, Class<? extends HvckerActivity>> getAtyMappings() {
        Map<String,Class<? extends HvckerActivity>> atys = new LinkedHashMap<>();
        atys.put("Messenger通信", MessengerTestAty.class);
        atys.put("AIDL通信", AIDLA.class);
        atys.put("Binder断裂处理", BinderDieA.class);
        atys.put("跨进程权限处理", PermissionTestAty.class);
        atys.put("Binder池", BinderPoolTestAty.class);
        return atys;
    }
}
