package net.nessness.android.cavestream.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class ViewerActivity extends ActionBarActivity {

    private static final String TAG = ViewerActivity.class.getSimpleName();

    private final ViewerActivity self = this;

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);
        if(url == null){
            finish();
        }
    }
}
