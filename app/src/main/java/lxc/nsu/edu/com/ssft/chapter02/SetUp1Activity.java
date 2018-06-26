package lxc.nsu.edu.com.ssft.chapter02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import lxc.nsu.edu.com.ssft.R;

public class SetUp1Activity extends BaseSetUpActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up1);
        initView();
    }

    protected void initView(){
        //设置第一个小圆点的颜色
        ((RadioButton) findViewById(R.id.rb_first)).setChecked(true);
    }
    public void showNext(){
        startActivityAndFinishSelf(SetUp2Activity.class);
    }
    public void showPre(){
        Toast.makeText(this,"(/≧▽≦)/当前页面已经是第一页",Toast.LENGTH_SHORT).show();
    }
}
