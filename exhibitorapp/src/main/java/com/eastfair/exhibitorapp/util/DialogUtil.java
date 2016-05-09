package com.eastfair.exhibitorapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class DialogUtil {

    // 定义一个显示消息的对话框
    public static void showDialog(final Context ctx
            , String msg, boolean goHome) {
        // 创建一个AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                .setMessage(msg).setCancelable(false);
        if (goHome) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        } else {
            builder.setPositiveButton("确定", null);
        }
        builder.create().show();
    }

    // 定义一个显示指定组件的对话框
    public static void showDialog(Context ctx, View view) {
        new AlertDialog.Builder(ctx)
                .setView(view).setCancelable(false)
                .setPositiveButton("确定", null)
                .create()
                .show();
    }

    /**
     * 定义一个列表dialog
     */
    public static void showDialog(final Context ctx, String title, final String[] mData, final OnClickItemListener listenerItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//		builder.setIcon(R.mipmap.ic_launcher);
        if (title != null) {
            builder.setTitle(title);
        }
        //    设置一个下拉的列表选择项
        builder.setItems(mData, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listenerItem != null) {
                    listenerItem.onClickItem(which);
                }
            }
        });
        builder.show();
    }

    // 定义一个删除对话框
    public static void showDeleteDialog(final Context ctx
            , final OnClickListenerOk listenerok, final OnClickListenerCancel listenercancel) {
        // 创建一个AlertDialog.Builder对象

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage("确定删除文件？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listenerok != null) {
                    listenerok.onClickok(dialog,which);
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listenercancel != null) {
                    listenercancel.onClickcancel(dialog,which);
                }
            }
        });
        builder.create().show();
    }

    /**
     * 列表对话框的 Listener
     */
    // 因为本类不是activity所以通过继承接口的方法获取到点击的事件
    public interface OnClickItemListener {
        abstract void onClickItem(int which);
    }


    /**
     * 删除对话框的 Listener
     */
    // 因为本类不是activity所以通过继承接口的方法获取到点击的事件
    public interface OnClickListenerOk {
        abstract void onClickok(DialogInterface dialog, int which);
    }


    /**
     * 列表对话框的 Listener
     */
    // 因为本类不是activity所以通过继承接口的方法获取到点击的事件
    public interface OnClickListenerCancel {
        abstract void onClickcancel(DialogInterface dialog, int which);
    }
}
