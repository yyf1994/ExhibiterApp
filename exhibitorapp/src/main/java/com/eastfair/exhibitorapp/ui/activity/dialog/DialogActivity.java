package com.eastfair.exhibitorapp.ui.activity.dialog;

import android.os.Bundle;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends BaseActivity {

    /**
     * dialog
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        ButterKnife.bind(this);
    }

    /**
     * 确定
     */
    @OnClick(R.id.dialog_button_ok)
    public void ok() {
        Toast.makeText(DialogActivity.this,"",Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 取消
     */
    @OnClick(R.id.dialog_button_cancel)
    public void cancel() {
       finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
