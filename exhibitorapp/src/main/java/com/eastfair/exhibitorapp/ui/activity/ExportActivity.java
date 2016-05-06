package com.eastfair.exhibitorapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 导出界面
 */
public class ExportActivity extends BaseActivity {

    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.export_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.edit_email)
    EditText editEmail;
    @Bind(R.id.btn_ok)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    private void setListener() {
        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initView() {
        textTitle.setText("导出");
        TextPaint tp = textTitle.getPaint();
        tp.setFakeBoldText(true);
        toolbar_title.setNavigationIcon(R.mipmap.back);
    }

    @OnClick(R.id.btn_ok)
    public void ok(View view) {
        // TODO submit data to server...
        Toast.makeText(this,"111",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
