package lxc.nsu.edu.com.ssft.chapter02;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lidroid.xutils.view.annotation.event.OnClick;

import org.apache.http.util.TextUtils;

import lxc.nsu.edu.com.ssft.R;

public class SetUp3Activity extends BaseSetUpActivity implements View.OnClickListener{
    private EditText mInputPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up3);
        initView();

        //控制登录用户名图标大小
        EditText editText1 = (EditText) findViewById(R.id.et_inputphone);
        Drawable drawable1 = getResources().getDrawable(R.drawable.ic_user);
        drawable1.setBounds(60, 0, 20, 60);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        editText1.setCompoundDrawables(drawable1, null, null, null);//只放左边
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ((RadioButton) findViewById(R.id.rb_third)).setChecked(true);
        findViewById(R.id.btn_addcontact).setOnClickListener(this);
        mInputPhone=(EditText) findViewById(R.id.et_inputphone);
        String safephone=sp.getString("safephone",null);
        if (!TextUtils.isEmpty(safephone)){
            mInputPhone.setText(safephone);
        }
    }
    public void showNext(){
        //判断文本输入框中是否有电话号码
        String safePhone=mInputPhone.getText().toString().trim();
        if (TextUtils.isEmpty(safePhone)){
            Toast.makeText(this,"╮(╯﹏╰)╭请输入安全号码",Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences.Editor edit=sp.edit();
        edit.putString("safephone",safePhone);
        edit.commit();
        startActivityAndFinishSelf(SetUp4Activity.class);
    }
    public void showPre(){
        startActivityAndFinishSelf(SetUp2Activity.class);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_addcontact:
                startActivityForResult(new Intent(this,ContactSelectActivity.class),Toast.LENGTH_LONG);
                break;
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (data!=null){
            String phone=data.getStringExtra("phone");
            mInputPhone.setText(phone);
        }
    }
}
