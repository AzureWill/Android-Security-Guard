package lxc.nsu.edu.com.ssft.chapter02.receiver;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.util.Log;

import org.apache.http.util.TextUtils;

import lxc.nsu.edu.com.ssft.R;
import lxc.nsu.edu.com.ssft.chapter02.service.GPSLocationService;

/**
 * Created by 123 on 2017/7/3.
 */

public class SmsLosFindReceiver extends BroadcastReceiver{
    private static final String TAG = SmsLosFindReceiver.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferences=context.getSharedPreferences("config", Activity.MODE_PRIVATE);
        boolean protecting=sharedPreferences.getBoolean("protecting",true);
        if (protecting){//防盗保护开启
            //获取超级管理员权限
            DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
            Object[] objs=(Object[]) intent.getExtras().get("pdus");
            for (Object obj:objs){
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
                String sender=smsMessage.getOriginatingAddress();
                String body=smsMessage.getMessageBody();
                String safephone=sharedPreferences.getString("safephone",null);
                //如果该短信是安全号码发送的
                if (!TextUtils.isEmpty(safephone) & sender.equals(safephone)){
                    if ("#*location*#".equals(body)){
                        Log.i(TAG,"返回位置信息");
                        //获取位置 放在服务器里面去实现
                        Intent service=new Intent(context, GPSLocationService.class);
                        context.startService(service);
                        abortBroadcast();
                    }else if ("#*alarm*#".equals(body)){
                        Log.i(TAG,"播放报警音乐");
                        MediaPlayer player = MediaPlayer.create(context, R.raw.ylzs);
                        player.setVolume(1.0f,1.0f);
                        player.start();
                        abortBroadcast();
                    }else if ("#*wipedata*#".equals(body)){
                        Log.i(TAG,"远程清除数据");
                        dpm.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);
                        abortBroadcast();
                    }else if ("#*lockscreen*#".equals(body)){
                        Log.i(TAG,"远程锁屏");
                        dpm.resetPassword("123",0);
                        dpm.lockNow();//没有管理员权限，调用时会崩溃
                        abortBroadcast();
                    }
                }
            }
        }
    }
}
