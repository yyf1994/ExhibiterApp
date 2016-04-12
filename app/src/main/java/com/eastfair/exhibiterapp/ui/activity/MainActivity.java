package com.eastfair.exhibiterapp.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.ui.activity.capture.CaptureActivity;
import com.eastfair.exhibiterapp.ui.activity.exhibitors.ExhibitorsSearchActivity;
import com.eastfair.exhibiterapp.ui.activity.exhibits.ExhibitsSearchActivity;
import com.eastfair.exhibiterapp.ui.fragment.ExhibitionFragment;
import com.eastfair.exhibiterapp.ui.fragment.ExhibitorsTabLayoutFragment;
import com.eastfair.exhibiterapp.ui.fragment.ExhibitsFragment;
import com.eastfair.exhibiterapp.ui.fragment.MessageFragment;
import com.eastfair.exhibiterapp.ui.fragment.MyFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    @Bind(R.id.bottom_navigation)
     BottomNavigationBar bottomNavigation;
    @Bind(R.id.toolbar_title)
     Toolbar toolbar_title;
    @Bind(R.id.text_title)
     TextView text_Title ;
    @Bind(R.id.text_title1)
     TextView text_regist ;
    @Bind(R.id.img_search)
     ImageView img_search ;

     int lastSelectedPosition = 0;
     String TAG = MainActivity.class.getSimpleName();
     String ExhibitorsTAG = "zhanshang";
     String ExhibitsTAG = "zhanpin";
    private MessageFragment mMessageFragment;
    private ExhibitionFragment mExhibitionFragment;
    private ExhibitorsTabLayoutFragment exhibitorsTabLayoutFragment;
    private ExhibitsFragment mExhibitsFragment;
    private MyFragment mMyFragment;

    private String searchtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 发需求按钮点击事件
     * */
    @OnClick(R.id.text_title1)
    public void sendDeman(){
        //                Intent intent = new Intent(this, SendDemandActivity.class);
//                startActivity(intent);
    }

    /**
     * 搜索按钮点击事件
     * */
    @OnClick(R.id.img_search)
    public void search(){
        if(searchtag.equals(ExhibitorsTAG)){//展商
            Intent intent = new Intent(this, ExhibitorsSearchActivity.class);
            startActivity(intent);
        }else if(searchtag.equals(ExhibitsTAG)){//展品
            Intent intent = new Intent(this, ExhibitsSearchActivity.class);
            startActivity(intent);
        }
    }

    private void initView() {
        bottomNavigation
                .addItem(new BottomNavigationItem(R.mipmap.ic_location_on_white_24dp, "消息").setActiveColor(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp, "展商").setActiveColor(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "展品").setActiveColor(R.color.green))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "展场").setActiveColor(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "我的").setActiveColor(R.color.blue))
                .setFirstSelectedPosition(lastSelectedPosition )//设置默认选中
                .initialise();
        setDefaultFragment();
        bottomNavigation.setTabSelectedListener(this);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");
        text_Title.setText("消息");
        text_regist.setText("发需求");
        toolbar_title.setNavigationIcon(R.mipmap.scanimg);

    }


    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mMessageFragment = MessageFragment.newInstance("消息");
        transaction.replace(R.id.tb, mMessageFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        android.support.v4.app.FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mMessageFragment == null) {
                    mMessageFragment = MessageFragment.newInstance("消息");
                }
                transaction.replace(R.id.tb, mMessageFragment);
                text_Title.setText("消息");
                img_search.setVisibility(View.GONE);
                break;
            case 1:

                if (exhibitorsTabLayoutFragment == null) {
                    exhibitorsTabLayoutFragment = ExhibitorsTabLayoutFragment.newInstance("展商");

                }
                transaction.replace(R.id.tb, exhibitorsTabLayoutFragment);
                text_Title.setText("展商");
                img_search.setVisibility(View.VISIBLE);
                searchtag = ExhibitorsTAG;
                break;
            case 2:
                if (mExhibitsFragment == null) {
                    mExhibitsFragment = ExhibitsFragment.newInstance("展品");
                }
                transaction.replace(R.id.tb, mExhibitsFragment);
                text_Title.setText("展品");
                img_search.setVisibility(View.VISIBLE);
                searchtag = ExhibitsTAG;
                break;
            case 3:
                if (mExhibitionFragment == null) {
                    mExhibitionFragment = ExhibitionFragment.newInstance("展场");
                }
                transaction.replace(R.id.tb, mExhibitionFragment);
                text_Title.setText("展场");
                img_search.setVisibility(View.GONE);
                break;
            case 4:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance("我的");
                }
                transaction.replace(R.id.tb, mMyFragment);
                text_Title.setText("我的");
                img_search.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(MainActivity.this,"扫码",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(MainActivity.this,"result"+scanResult,Toast.LENGTH_SHORT).show();
//            resultTextView.setText(scanResult);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
