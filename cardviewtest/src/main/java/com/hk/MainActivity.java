package com.hk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView  = (ImageView) findViewById(R.id.iv_show);
        final Bitmap bitmap =BitmapFactory.decodeResource(getResources(),R.mipmap.defaultcover11);
        imageView.setImageBitmap(bitmap);
        Palette.Builder builder  = new Palette.Builder(bitmap);
        Palette palette = builder.generate();
        int color = palette.getLightVibrantColor(android.R.color.black);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
    }
}
