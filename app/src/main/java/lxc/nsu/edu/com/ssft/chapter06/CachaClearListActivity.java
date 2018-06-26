package lxc.nsu.edu.com.ssft.chapter06;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import lxc.nsu.edu.com.ssft.R;

public class CachaClearListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cacha_clear_list);
    }
}
