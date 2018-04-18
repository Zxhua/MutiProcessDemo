package code.zxhua.mutiprocessdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import java.util.List;

/**
 * Created by Zxhua on 2018/4/13 0013.
 */

public class App extends Application {

    private int PID;

    public static final String TAG = "MutiProcess";

    public static Context c;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PID = Process.myPid();
        c = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!isLocalProcess()) {
            Log.d(TAG, "process name ：" + getRunningProcess().processName + ",子进程");
            return;
        }
        Log.d(TAG, "process name ：" + getRunningProcess().processName + ",主进程");
    }


    private boolean isLocalProcess() {
        ActivityManager.RunningAppProcessInfo myProcess = getRunningProcess();
        return myProcess != null && myProcess.processName.equals(getPackageName());
    }

    private ActivityManager.RunningAppProcessInfo getRunningProcess() {
        ActivityManager.RunningAppProcessInfo myProcess = null;
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo process : runningAppProcesses) {
                if (process.pid == PID) {
                    myProcess = process;
                    break;
                }
            }
        }
        return myProcess;
    }
}
