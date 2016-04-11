package com.eastfair.exhibiterapp.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.exhibits.ExhibitsListActivity;

public class ExportActivity extends BaseActivity implements View.OnClickListener {
    private ExportActivity mySelf() {
        return ExportActivity.this;
    }

    /**
     * 导出界面
     */
    private EditText edit_email;
    private Button btn_ok;
    private Toolbar toolbar_title;
    private TextView text_Title ;



    @Override
    public void findViews() {
        setContentView(R.layout.activity_export);
        initView();
    }

    @Override
    public void registerEvents() {
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void init() {

    }


    private void initView() {
        edit_email = (EditText) findViewById(R.id.edit_email);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("导出");

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 导出
             */
            case R.id.btn_ok:
//                CallPhoneNum("tel:10086");
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
