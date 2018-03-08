package kevinstar.device_tcp_port_set;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    
    private TextView textView;
    private TextView textView2;
    private String lastIpAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        updateWifiIpAddress();

        Button button = findViewById(R.id.btn_port_set);
        button.setOnClickListener(view -> new Handler().postDelayed(() -> {
            CommandExecution.CommandResult result= CommandExecution.execCommand(new String[]{"setprop service.adb.tcp.port 5555","stop adbd","start adbd"},false);
             if (result.successMsg!=null){
                 textView.setText("设置成功");
                 updateWifiIpAddress();
             }else{
                 textView.setText("设置失败");
             }
        },1000));

        Button openSetting = findViewById(R.id.btn_setting_open);
        openSetting.setOnClickListener(v->{
            try{
                //打开设置界面
                ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
                Intent intent = new Intent();
                intent.setComponent(comp);
                intent.setAction("android.intent.action.VIEW");
                startActivityForResult(intent, 0);
            }catch (Exception e){
                e.printStackTrace();
                Log.d(TAG, "设置界面打开失败");
            }
        });

    }

    /**
     * 更新wifiIp地址
     */
    private void updateWifiIpAddress() {
        String ipAddress = WifiUtil.getIp(this);
        String wifiName = WifiUtil.getWifiName(this);
        textView2.setText(wifiName+":  "+ipAddress);
        lastIpAddress = ipAddress;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String oldIpAddress = textView2.getText().toString();
        updateWifiIpAddress();
        if (!oldIpAddress.equals(lastIpAddress)){
            textView.setText("监听结果");
        }
    }
}
