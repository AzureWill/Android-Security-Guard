package lxc.nsu.edu.com.ssft.chapter01;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import lxc.nsu.edu.com.ssft.R;
import lxc.nsu.edu.com.ssft.chapter01.utils.MyUtils;
import lxc.nsu.edu.com.ssft.chapter01.utils.VersionUpdateUtils;

/**
 * Created by 123 on 2017/6/27.
 */

public class SplashActivity extends Activity{
    /** 应用版本号 **/
    private TextView mVersionTV;
    /** 本地版本号 **/
    private String mVersion;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        //设置该Activity没有标题栏，在加载布局之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mVersion= MyUtils.getVersion(getApplicationContext());
        initView();
        final VersionUpdateUtils updateUtils=new VersionUpdateUtils(mVersion,SplashActivity.this);
        new Thread(){
            public void run(){
                updateUtils.getCloudVersion();
            };
        }.start();
    }
    /** 初始化控件 **/
    private void initView(){
        mVersionTV=(TextView) findViewById(R.id.tv_splash_version);
        mVersionTV.setText("版本号"+mVersion);
    }
}
