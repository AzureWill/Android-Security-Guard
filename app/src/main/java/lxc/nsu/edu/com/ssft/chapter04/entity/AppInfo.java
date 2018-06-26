package lxc.nsu.edu.com.ssft.chapter04.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Isle yao on 2017/7/17.
 */

public class AppInfo {
    public String packageName;
    public Drawable icon;
    public String appName;
    public String apkPath;
    public long appSize;
    public boolean isInRoom;
    public boolean isUserApp;
    public boolean isSelected = false;
    public String getAppLocation(boolean isInRoom) {
        if (isInRoom) {
            return "手机内存";
        } else {
            return "外部存储";
        }
    }
}


