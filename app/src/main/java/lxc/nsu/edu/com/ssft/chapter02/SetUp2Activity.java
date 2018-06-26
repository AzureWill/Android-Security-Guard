package lxc.nsu.edu.com.ssft.chapter02;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import lxc.nsu.edu.com.ssft.R;

public class SetUp2Activity extends BaseSetUpActivity implements View.OnClickListener {
    private TelephonyManager mTelephonyManager;
    private Button mBindSIMBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up2);
        mTelephonyManager=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        initView();
    }

    private void initView() {
        //设置第二个小圆点的颜色
        ((RadioButton) findViewById(R.id.rb_second)).setChecked(true);
        mBindSIMBtn = (Button) findViewById(R.id.btn_bind_sim);
        mBindSIMBtn.setOnClickListener(this);
        if (isBind()){
            mBindSIMBtn.setEnabled(false);
        }else {
            mBindSIMBtn.setEnabled(true);
        }
    }

    public boolean isBind() {
        String simString = sp.getString("sim", null);
        if(TextUtils.isEmpty(simString)){
            return false;
        }
        return true;
    }
    public void showNext(){
        if (!isBind()){
            Toast.makeText(this,"您还没有绑定SIM卡!",Toast.LENGTH_SHORT).show();
            return;
        }
        startActivityAndFinishSelf(SetUp3Activity.class);
    }
    public void showPre(){
        startActivityAndFinishSelf(SetUp1Activity.class);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_bind_sim:
                //绑定SIM卡
                bindSIM();
                break;
        }
    }

    /**
     * 绑定SIM卡
     */
    private void bindSIM() {
        if (!isBind()){
            String simSerialNumber = mTelephonyManager.getSimSerialNumber();
            SharedPreferences.Editor edit=sp.edit();
            edit.putString("sim",simSerialNumber);
            edit.commit();
            Toast.makeText(this,"(*^▽^*)亲，SIM卡绑定成功！",Toast.LENGTH_SHORT).show();
            mBindSIMBtn.setEnabled(false);
        }else {
            //已经绑定，提醒用户
            Toast.makeText(this,"↖(^ω^)↗SIM卡已经绑定!",Toast.LENGTH_SHORT).show();
            mBindSIMBtn.setEnabled(false);
        }
    }
}
