package lxc.nsu.edu.com.ssft.chapter02.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import lxc.nsu.edu.com.ssft.R;

/**
 * Created by 123 on 2017/7/3.
 */

public class SetUpPasswordDialog extends Dialog implements android.view.View.OnClickListener{
    /**标题栏*/
    private TextView mTitleTV;
    /**首次输入密码文本框*/
    public EditText mFirstPWDET;
    /**确认密码文本框*/
    public EditText mAffirmET;
    /**回调接口*/
    private MyCallBack myCallBack;
    public SetUpPasswordDialog(Context context) {
        super(context, R.style.dialog_custom);//引入自定义对话框
    }
    public void setCallBack(MyCallBack myCallBack){
        this.myCallBack = myCallBack;
    }
    protected void onCreate(Bundle saveInstanceStace){
        setContentView(R.layout.setup_password_dialog);
        super.onCreate(saveInstanceStace);
        initView();
    }
    /**初始化控件*/
    private void initView() {
        mTitleTV = (TextView) findViewById(R.id.tv_setuppwd_title);
        mFirstPWDET = (EditText) findViewById(R.id.et_firstpwd);
        mAffirmET = (EditText) findViewById(R.id.et_affirm_password);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_cancle).setOnClickListener(this);
    }
    /**
     * 设置对话框标题栏
     * @param title
     */
    public void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            mTitleTV.setText(title);
        }
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                myCallBack.ok();
                break;
            case R.id.btn_cancle:
                myCallBack.cancle();
                break;
        }

    }
    public interface MyCallBack{
        void ok();
        void cancle();
    }
}
