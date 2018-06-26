package lxc.nsu.edu.com.ssft.chapter02.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import lxc.nsu.edu.com.ssft.App;

/**
 * Created by 123 on 2017/7/3.
 */

public class BootCompleteReceiver extends BroadcastReceiver{
    private static final String TAG = BootCompleteReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        ((App) context.getApplicationContext()).correctSIM();//初始化
    }
}
