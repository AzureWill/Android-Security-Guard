package lxc.nsu.edu.com.ssft.chapter04.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lxc.nsu.edu.com.ssft.chapter04.entity.AppInfo;

/**
 * Created by Isle yao on 2017/7/17.
 */

public class AppInfoParser {
    /**
     * 获取手机里面的所有应用程序
     * @param context 上下文
     * @return
     */
    public static List<AppInfo> getAppInfos(Context context){
        PackageManager pm=context.getPackageManager();
        List<PackageInfo> packageInfos=pm.getInstalledPackages(0);
        List<AppInfo> appinfos=new ArrayList<AppInfo>();
        for(PackageInfo packageInfo:packageInfos){
            AppInfo appinfo=new AppInfo();
            String packagename=packageInfo.packageName;
            appinfo.packageName=packagename;
            Drawable icon=packageInfo.applicationInfo.loadIcon(pm);
            appinfo.icon=icon;
            String appname=packageInfo.applicationInfo.loadLabel(pm).toString();
            appinfo.appName=appname;
            //应用程序apk包的路径
            String apkpath=packageInfo.applicationInfo.sourceDir;
            appinfo.apkPath=apkpath;
            File file=new File(apkpath);
            long appSize=file.length();
            appinfo.appSize=appSize;
            //应用程序安装的位置
            int flags=packageInfo.applicationInfo.flags;//二进制映射
            if ((ApplicationInfo.FLAG_EXTERNAL_STORAGE & flags)!=0) {
                //外部储存
                appinfo.isInRoom=false;
            }else {
                //手机内存
                appinfo.isInRoom=true;
            }
            if ((ApplicationInfo.FLAG_SYSTEM & flags)!=0) {
                //系统应用
                appinfo.isInRoom=true;
            } else {
                //用户应用
                appinfos.add(appinfo);
                appinfo=null;
            }
        }
        return appinfos;
    }
}

