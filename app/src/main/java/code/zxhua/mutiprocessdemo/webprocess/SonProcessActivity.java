package code.zxhua.mutiprocessdemo.webprocess;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import code.zxhua.mutiprocessdemo.R;
import code.zxhua.mutiprocessdemo.mainprocess.service.WebService;
import code.zxhua.mutiprocessdemo.utils.ToastWapper;
import code.zxhua.mutiprocessdemo.webprocess.serviceconnection.SonConnection;

public class SonProcessActivity extends AppCompatActivity {

    private SonConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        connection = new SonConnection();
        Intent intent = new Intent(getApplicationContext(), WebService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

        findViewById(R.id.sendMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    connection.mRomte.sendMessage("this is Son process send message");
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    ToastWapper.show(" mRomte is null  ");
                }
            }
        });


        findViewById(R.id.handleMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    connection.mRomte.handleMessage();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    ToastWapper.show(" mRomte is null  ");
                }
            }
        });
    }
}
