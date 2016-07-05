package com.hvcker.doc.base.aty;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;

import com.hvcker.doc.constants.Extras;

/**
 * Created by Hvcker on 2016/1/6 0006.
 * Good good study,day day up!
 */
public class HvckerActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra(Extras.EXTRA_TITLE);
        if (!TextUtils.isEmpty(title)) {
            super.setTitle(title);
        }
    }
}
