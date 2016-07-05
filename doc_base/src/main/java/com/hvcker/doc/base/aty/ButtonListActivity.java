package com.hvcker.doc.base.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.hvcker.doc.constants.Extras;

import java.util.Map;

/**
 * Created by Hvcker on 2016/1/6 0006.
 * Good good study,day day up!
 */
public abstract class ButtonListActivity extends HvckerActivity implements View.OnClickListener {
    private Map<String, Class<? extends HvckerActivity>> mAtyMappings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAtyMappings = getAtyMappings();
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        addButtons(layout);
        scrollView.addView(layout);
        setContentView(scrollView);
    }

    private void addButtons(ViewGroup layout) {
        if (mAtyMappings != null)
            for (String key : mAtyMappings.keySet()) {
                addButton(layout, key, mAtyMappings.get(key));
            }
    }

    private void addButton(ViewGroup layout, String text, Class<? extends Activity> target) {
        Button btn = new Button(this);
        btn.setText(text);
        btn.setTag(target);
        btn.setOnClickListener(this);
        btn.setSingleLine(true);
        btn.setEllipsize(TextUtils.TruncateAt.END);
        layout.addView(btn);
    }


    @Override
    public void onClick(View v) {
        Class<? extends Activity> clazz = (Class<? extends Activity>) v.getTag();
        if (clazz != null) {
            Intent intent = new Intent(this, clazz);
            intent.putExtra(Extras.EXTRA_TITLE, ((Button) v).getText());
            startActivity(intent);
        }
    }

    public abstract Map<String, Class<? extends HvckerActivity>> getAtyMappings();
}
