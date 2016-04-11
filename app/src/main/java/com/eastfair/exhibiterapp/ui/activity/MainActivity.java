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

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,View.OnClickListener{

    private BottomNavigationBar bottomNavigation;
    private int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private String ExhibitorsTAG = "zhanshang";
    private String ExhibitsTAG = "zhanpin";
    private MessageFragment mMessageFragment;
    private ExhibitionFragment mExhibitionFragment;
    private ExhibitorsTabLayoutFragment exhibitorsTabLayoutFragment;
    private ExhibitsFragment mExhibitsFragment;
    private MyFragment mMyFragment;
    private Toolbar toolbar_title;
    private TextView text_Title ;
    private TextView text_regist ;
    private ImageView img_search ;
    private String searchtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void setListener() {
        text_regist.setOnClickListener(this);
        img_search.setOnClickListener(this);
    }

    private void initView() {
        bottomNavigation = (BottomNavigationBar) findViewById(R.id.bottom_navigation);
        img_search = (ImageView) findViewById(R.id.img_search);
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

        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        text_regist = (TextView) toolbar_title.findViewById(R.id.text_title1);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_title1://发需求
//                Intent intent = new Intent(this, SendDemandActivity.class);
//                startActivity(intent);
                break;
            case R.id.img_search://查找
                if(searchtag.equals(ExhibitorsTAG)){//展商
                    Intent intent = new Intent(this, ExhibitorsSearchActivity.class);
                    startActivity(intent);
                }else if(searchtag.equals(ExhibitsTAG)){//展品
                    Intent intent = new Intent(this, ExhibitsSearchActivity.class);
                    startActivity(intent);
                }

                break;
        }


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
}
