package code.zxhua.mutiprocessdemo.utils;

import android.widget.Toast;

import code.zxhua.mutiprocessdemo.App;

/**
 * Created by Zxhua on 2018/4/13 0013.
 */

public class ToastWapper {
    public static void show(String message) {
        Toast.makeText(App.c, message, Toast.LENGTH_SHORT).show();
    }
}
