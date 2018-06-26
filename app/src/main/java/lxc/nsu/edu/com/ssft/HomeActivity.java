package lxc.nsu.edu.com.ssft;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.util.TextUtils;

import lxc.nsu.edu.com.ssft.chapter01.adapter.HomeAdapter;
import lxc.nsu.edu.com.ssft.chapter02.LostFindActivity;
import lxc.nsu.edu.com.ssft.chapter02.dialog.InterPasswordDialog;
import lxc.nsu.edu.com.ssft.chapter02.dialog.SetUpPasswordDialog;
import lxc.nsu.edu.com.ssft.chapter02.receiver.MyDeviceAdminReceiver;
import lxc.nsu.edu.com.ssft.chapter02.utils.MD5Utils;
import lxc.nsu.edu.com.ssft.chapter03.SecurityPhoneActivity;
import lxc.nsu.edu.com.ssft.chapter04.AppManagerActivity;
import lxc.nsu.edu.com.ssft.chapter05.VirusScanActivity;
import lxc.nsu.edu.com.ssft.chapter06.CachaClearListActivity;
import lxc.nsu.edu.com.ssft.chapter07.ProcessManagerActivity;
import lxc.nsu.edu.com.ssft.chapter08.TrafficMonitoringActivity;
import lxc.nsu.edu.com.ssft.chapter09.AdvancedToolsActivity;
import lxc.nsu.edu.com.ssft.chapter10.SettingsActivity;

public class HomeActivity extends Activity {
    /**声明GridView,该控件类似ListView**/
    private GridView gv_home;
    /**存储手机防盗密码的sp**/
    private SharedPreferences msharedPreferences;
    /**设备管理员**/
    private DevicePolicyManager policyManager;
    /**申请权限**/
    private ComponentName componentName;
    private long mExitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        msharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
        //初始化GriView
        gv_home=(GridView) findViewById(R.id.gv_home);
        gv_home.setAdapter(new HomeAdapter(HomeActivity.this));
        //设置条目的点击事件
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //parent代表GridView，view代表每个条目的view对象，postion代表每个条目的位置
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                   case 0://手机防盗
                       if (isSetUpPassword()){
                            //弹出输入密码对话框
                            showInterPswdDialog();
                        }else {
                            //弹出设置密码对话框
                            showSetUpPswdDialog();
                        }
                        break;
                    case 1://通讯卫士
                        startActivity(SecurityPhoneActivity.class);
                        break;
                    case 2://软件管家
                        startActivity(AppManagerActivity.class);
                        break;
                    case 3://病毒查杀
                        startActivity(VirusScanActivity.class);
                        break;
                    case 4://缓存清理
                        startActivity(CachaClearListActivity.class);
                        break;
                    case 5://进程管理
                        startActivity(ProcessManagerActivity.class);
                        break;
                    case 6://流量统计
                        startActivity(TrafficMonitoringActivity.class);
                        break;
                    case 7://高级工具
                        startActivity(AdvancedToolsActivity.class);
                        break;
                    case 8://设置中心
                        startActivity(SettingsActivity.class);
                        break;
                }
            }
        });
        //1.获取设备管理员
        policyManager=(DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        //2.申请权限,MyDeviceAdminReceiver继承自DeviceAdminReceiver
        componentName=new ComponentName(this, MyDeviceAdminReceiver.class);
        //3.判断，如果没有权限则申请权限
        boolean active=policyManager.isAdminActive(componentName);
        if (!active){
            //没有管理员的权限，则获取管理员权限
            Intent intent=new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"(๑•̀ㅂ•́)و获取超级管理员权限，用于远程锁屏和清除数据");
            startActivity(intent);
        }
    }
    /**
     * 弹出设置密码对话框
     */
    private void showSetUpPswdDialog(){
        final SetUpPasswordDialog setUpPasswordDialog=new SetUpPasswordDialog(HomeActivity.this);
        setUpPasswordDialog.setCallBack(new SetUpPasswordDialog.MyCallBack() {
            @Override
            public void ok() {
                String firstPswd=setUpPasswordDialog.mFirstPWDET.getText().toString().trim();
                String affirmPswd=setUpPasswordDialog.mAffirmET.getText().toString().trim();
                if (!TextUtils.isEmpty(firstPswd) && !TextUtils.isEmpty(affirmPswd)){
                    if (firstPswd.equals(affirmPswd)){
                        //两次密码一致，存储密码
                        savePswd(affirmPswd);
                        setUpPasswordDialog.dismiss();
                        //显示输入密码对话框
                        showInterPswdDialog();
                    }else {
                        Toast.makeText(HomeActivity.this,"Σ( ° △ °|||)︴两次密码不一致！",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(HomeActivity.this,"(っ*´Д`)っ密码不能为空!",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void cancle() {
                setUpPasswordDialog.dismiss();
            }
        });
        setUpPasswordDialog.setCancelable(true);
        setUpPasswordDialog.show();
    }
    /**
     * 弹出输入密码对话框
     */
    private void showInterPswdDialog() {
        final String password=getPassword();
        final InterPasswordDialog mInPswdDialog=new InterPasswordDialog(HomeActivity.this);
        mInPswdDialog.setCallBack(new InterPasswordDialog.MyCallBack() {
            @Override
            public void confirm() {
                if (TextUtils.isEmpty(mInPswdDialog.getPassword())){
                    Toast.makeText(HomeActivity.this,"(っ*´Д`)っ密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if (password.equals(MD5Utils.encode(mInPswdDialog.getPassword()))){
                    //进入防盗主界面
                    mInPswdDialog.dismiss();
                    startActivity(LostFindActivity.class);
                }else {
                    //对话框消失，弹出土司
                    mInPswdDialog.dismiss();
                    Toast.makeText(HomeActivity.this,"(O_O)?密码错误，请重新输入!",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void cancle() {
                mInPswdDialog.dismiss();
            }
        });
        mInPswdDialog.setCancelable(true);
        //让对话框显示
        mInPswdDialog.show();
    }
    /**
     * 保存密码
     */
    private void savePswd(String affirmPswd){
        SharedPreferences.Editor edit=msharedPreferences.edit();
        //为了防止用户隐私被泄露，因此需要加密密码
        edit.putString("PhoneAntiTheftPWD",MD5Utils.encode(affirmPswd));
        edit.commit();
    }
    /**
     * 获取密码
     * @return sp存储的密码
     */
    private String getPassword(){
        String password=msharedPreferences.getString("PhoneAntiTheftPWD",null);
        if (TextUtils.isEmpty(password)){
            return "";
        }
        return password;
    }
    /**判断用户是否设置过手机防盗密码*/
    private boolean isSetUpPassword(){
        String password=msharedPreferences.getString("PhoneAntiTheftPWD",null);
        if (TextUtils.isEmpty(password)){
            return false;
        }
        return true;
    }
    /**
     * 开启新的Activity不关闭自己
     * @param cls,新的Activity的字节码
     */
    public void startActivity(Class<?> cls){
        Intent intent=new Intent(HomeActivity.this,cls);
        startActivity(intent);
    }
    /**
     * 按两次返回键退出程序
     */
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if ((System.currentTimeMillis()-mExitTime)<2000){
                System.exit(0);
            }else {
                Toast.makeText(this,"(っ °Д °;)っ再按一次退出程序",Toast.LENGTH_SHORT).show();
                mExitTime=System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
