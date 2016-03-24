package remix.myplayer.receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.umeng.analytics.MobclickAgent;

import remix.myplayer.listeners.LockScreenListener;
import remix.myplayer.utils.ActivityManager;
import remix.myplayer.utils.ServiceManager;

/**
 * Created by taeja on 16-2-16.
 */

/**
 * 接受程序退出的广播
 */
public class ExitReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //停止所有service
            ServiceManager.StopAll();
            //关闭同孩子栏
            ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
            //停止锁屏监听
            if(LockScreenListener.mInstance != null){
                LockScreenListener.mInstance.stopListen();
            }
            MobclickAgent.onKillProcess(context);
            //关闭所有activity
            ActivityManager.FinishAll();
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
