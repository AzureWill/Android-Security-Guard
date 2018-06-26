package lxc.nsu.edu.com.ssft.chapter10;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.util.TextUtils;

import lxc.nsu.edu.com.ssft.R;
import lxc.nsu.edu.com.ssft.chapter10.utils.SystemInfoUtils;
import lxc.nsu.edu.com.ssft.chapter10.widget.SettingView;

public class SettingsActivity extends Activity implements View.OnClickListener,SettingView.OnCheckedStatusIsChanged{
    private SettingView mBlackNumSV;
    private SettingView mAppLockSV;
    private SharedPreferences mSP;
    private boolean running;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);
        mSP=getSharedPreferences("config",MODE_PRIVATE);
        initView();
    }

    private void initView() {
        findViewById(R.id.rl_titlebar).setBackgroundColor(getResources().getColor(R.color.cornflowerblue));
        ImageView mLeftImgv=(ImageView) findViewById(R.id.imgv_leftbtn);
        ((TextView) findViewById(R.id.tv_title)).setText("设置中心");
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.ic_icon07);
        mBlackNumSV=(SettingView) findViewById(R.id.sv_blacknumber_set);
        mAppLockSV=(SettingView) findViewById(R.id.sv_applock_set);
        mBlackNumSV.setOnCheckedStatusIsChanged(this);
        mAppLockSV.setOnCheckedStatusIsChanged(this);
    }
    protected void onStart(){
        running= SystemInfoUtils.isServiceRunning(this,"AppLockService");//需要上一个的Service
        mAppLockSV.setChecked(running);
        mBlackNumSV.setChecked(mSP.getBoolean("BlackNumStatus",true));
        super.onStart();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgv_leftbtn:
                finish();
                break;
        }
    }
    @Override
    public void onCheckedChanged(View view, boolean isChecked) {
        switch (view.getId()){
            case R.id.sv_blacknumber_set:
                saveStatus("BlackNumStatus",isChecked);
                break;
            case R.id.sv_applock_set:
                saveStatus("AppLockStatus",isChecked);
                //开启或关闭程序锁
                if (isChecked){
                    //intent=new Intent(this.AppLockService.class);
                    startService(intent);
                }else {
                    stopService(intent);
                }
                break;
        }
    }

    private void saveStatus(String keyname, boolean isChecked) {
        if (!TextUtils.isEmpty(keyname)){
            SharedPreferences.Editor edit = mSP.edit();
            edit.putBoolean(keyname,isChecked);
            edit.commit();
        }
    }
}
