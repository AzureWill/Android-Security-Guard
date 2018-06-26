package lxc.nsu.edu.com.ssft.chapter02.dialog;

/**
 * Created by 123 on 2017/7/3.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import lxc.nsu.edu.com.ssft.R;

/**
 * Created by Isle yao on 2017/6/29.
 */

public class InterPasswordDialog extends Dialog implements android.view.View.OnClickListener{
    private Context context;
    private TextView iTitle;
    private EditText iPWDET;
    private MyCallBack myCallBack;

    public InterPasswordDialog(Context context) {

        super(context, R.style.dialog_custom);
        this.context = context;
    }

    public void setCallBack(MyCallBack myCallBack){
        this.myCallBack = myCallBack;
    }

    @Override
    protected  void  onCreate(Bundle saveInstanceState){
        setContentView(R.layout.inter_password_dialog);
        super.onCreate(saveInstanceState);
        initView();
    }

    /**初始化*/
    private  void  initView(){
        iTitle =(TextView)findViewById(R.id.tv_interpwd_title);
        iPWDET = (EditText)findViewById(R.id.et_inter_password);
        findViewById(R.id.btn_comfirm).setOnClickListener(this);
        findViewById(R.id.btn_dismiss).setOnClickListener(this);


    }

    /**
     * 设置标题栏
     * @param title
     */

    public  void  setiTitle(String title){
        if (!TextUtils.isEmpty(title)){
            iTitle.setText(title);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_comfirm:
                myCallBack.confirm();
                break;
            case R.id.btn_dismiss:
                myCallBack.cancle();
                break;
        }
    }

    public String getPassword() {
        return this.iPWDET.getText().toString();
    }

    public interface MyCallBack{
        void confirm();
        void cancle();
    }
}
