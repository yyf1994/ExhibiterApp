package com.eastfair.exhibiterapp.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.widget.Toast;
import com.eastfair.exhibiterapp.util.CustomProgressDialog;

/**
 * @author wangyan
 * @name BaseActivity
 * @description ui初始化及常用界面方法父类(所有activity继承此类)
 * @date 20151204
 */
public abstract class BaseFragment extends Fragment {
    private CustomProgressDialog progressDialog = null;


    /**
     * 吐出一个短的消息提示
     * @param message      消息内容
     */
    public void toastS(String message) {
      //  Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        Toast toast= Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    public void toastSError() {
       // Toast.makeText(getActivity(), "无法连接服务器", Toast.LENGTH_SHORT).show();
        Toast toast= Toast.makeText(getActivity(), "无法连接服务器", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    /**
     * 吐出一个短的消息提示
     *
     * @param messageId 消息ID
     */
    public void toastS(int messageId) {
//        Toast.makeText(getActivity(), getString(messageId),
//                Toast.LENGTH_SHORT).show();
        Toast toast= Toast.makeText(getActivity(), getString(messageId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }



    /**
     * 开始加载数据时候的进度
     */
    public void startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(getActivity());
            progressDialog.setMessage("正在加载中...");
        }
        if (!getActivity().isFinishing()) {
            progressDialog.show();
        }
    }

    /**
     * 停止加载
     */
    public void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
    public void SkipActivity(Class activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.grow_from_top,
//                R.anim.small_2_big);
    }


}






