package lxc.nsu.edu.com.ssft.chapter05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import lxc.nsu.edu.com.ssft.R;

public class VirusScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_virus_scan);
    }
}
