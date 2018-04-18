package code.zxhua.mutiprocessdemo.mainprocess.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import code.zxhua.mutiprocessdemo.App;
import code.zxhua.mutiprocessdemo.IWebviewService;
import code.zxhua.mutiprocessdemo.utils.ToastWapper;

/**
 * Created by Zxhua on 2018/4/13 0013.
 */

public class WebService extends Service {

    private final String TAG = this.getClass().getName();

    private final String EVENT = "event";

    private String message;

    private static Handler handler;

    private IBinder mBinder = new IWebviewService.Stub() {


        @Override
        public void sendMessage(String msg) throws RemoteException {
            message = msg;
        }

        @Override
        public void handleMessage() throws RemoteException {
            Message event = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString(EVENT, message);
            event.setData(bundle);
            handler.sendMessage(event);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(App.TAG, "webservice oncreate");


        String threadName = Thread.currentThread().getName();

        Log.i(App.TAG, threadName);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String message = msg.getData().getString(EVENT);
                ToastWapper.show(message);
            }
        };
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(App.TAG, "webservice onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
