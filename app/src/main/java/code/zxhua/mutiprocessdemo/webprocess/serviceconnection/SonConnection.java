package code.zxhua.mutiprocessdemo.webprocess.serviceconnection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import code.zxhua.mutiprocessdemo.App;
import code.zxhua.mutiprocessdemo.IWebviewService;

/**
 * Created by Zxhua on 2018/4/13 0013.
 */

public class SonConnection implements ServiceConnection {

    public IWebviewService mRomte;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d(App.TAG,"son process connection host!");
        mRomte = IWebviewService.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        name.getClassName();
        mRomte = null;
    }
}
