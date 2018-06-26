package lxc.nsu.edu.com.ssft.chapter08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import lxc.nsu.edu.com.ssft.R;

public class TrafficMonitoringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_traffic_monitoring);
    }
}
