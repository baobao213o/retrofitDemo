package com.example.admin.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Admin on 2017/1/25.
 */

public class ProgressHandler extends Handler {

    //接口，用来取消进度条
    public interface ProgressCancelListener {
        void onProgressCanceled();
    }

    public static final int SHOW_PROGRESS = 0;
    public static final int DISMISS_PROGRESS = 1;
    private ProgressDialog pd;
    private Context mContext;
    private ProgressCancelListener mProgressCancelListener;
    private boolean cancelable;
    public ProgressHandler(Context context, ProgressCancelListener listener,boolean cancelable){
        this.mContext = context;
        mProgressCancelListener = listener;
        this.cancelable = cancelable;
    }
    public void initProgressDialog(){
        if(pd == null){
            pd = new ProgressDialog(mContext);
            pd.setCancelable(cancelable);
            if(cancelable){
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mProgressCancelListener.onProgressCanceled();
                    }
                });
            }
            if(!pd.isShowing()){
                pd.show();//显示进度条
            }
        }
    }
    public void dismissProgressDialog(){
        if(pd!=null){
            pd.dismiss();//取消进度条
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch(msg.what){
            case SHOW_PROGRESS:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS:
                dismissProgressDialog();
                break;
        }
    }
}