package lxc.nsu.edu.com.ssft.chapter10.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by 123 on 2017/7/13.
 */

public class SystemInfoUtils {
    /**
     * 判断一个服务是否处于运行状态
     * @param context
     * @param className
     * @return
     */
    public static boolean isServiceRunning(Context context,String className) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos=am.getRunningServices(200);
        for (ActivityManager.RunningServiceInfo info:infos){
            String serviceClassName=info.service.getClassName();
            if (className.equals(serviceClassName)){
                return true;
            }
        }
        return false;
    }
}
