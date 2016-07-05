package com.hk.event;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.utils.L;

/**
 * Created by Hvcker on 2016/1/18.
 */
public class DecorViewTestAty extends HvckerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.aty_decor_test);
        super.findViewById(R.id.btn_event_decor_get).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testDecorVivew();
                    }
                }
        );
    }

    private void testDecorVivew() {
        Window window = getWindow();

        //Get the DecorView
        ViewGroup parent = (ViewGroup) window.getDecorView();
        showChildren(parent);

        //Get the FrameLayout
        parent = (ViewGroup) parent.findViewById(android.R.id.content);
        showChildren(parent);
        if (parent.getChildAt(0) instanceof ViewGroup) {
            //Get the view of super.setContentView
            parent = (ViewGroup) parent.getChildAt(0);
            showChildren(parent);
            int len = parent.getChildCount();
            for (int i = 0; i < len; i++) {
                View view = parent.getChildAt(i);
                if (view instanceof TextView) {
                    Toast.makeText(DecorViewTestAty.this,
                            ((TextView) view).getText().toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void showChildren(ViewGroup parent) {
        int len = parent.getChildCount();
        for (int i = 0; i < len; i++) {
            L.i("parent = " + parent.toString() + ",child[" + i + "] = " + parent.getChildAt(i));
        }
        L.i("-----------end-----------");
    }
}
