package kevinstar.device_tcp_port_set;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.Clock;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        textView2.setText(WifiUtil.getIp(this));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       CommandExecution.CommandResult result= CommandExecution.execCommand(new String[]{"setprop service.adb.tcp.port 5555","stop adbd","start adbd"},false);
                        if (result.successMsg!=null){
                            textView.setText("设置成功");
                        }
                   }
               },1000);
            }
        });
    }
}
