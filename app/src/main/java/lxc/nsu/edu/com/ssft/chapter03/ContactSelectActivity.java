package lxc.nsu.edu.com.ssft.chapter03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import lxc.nsu.edu.com.ssft.R;
import lxc.nsu.edu.com.ssft.chapter02.adapter.ContactAdapter;
import lxc.nsu.edu.com.ssft.chapter02.entity.ContactInfo;
import lxc.nsu.edu.com.ssft.chapter02.utils.ContactInfoParser;

public class ContactSelectActivity extends Activity implements View.OnClickListener {
    private ListView mListView;
    private ContactAdapter adapter;
    private List<ContactInfo> systemConatcts;

    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 10:
                    if (systemConatcts!=null) {
                        adapter = new ContactAdapter(systemConatcts, lxc.nsu.edu.com.ssft.chapter03.ContactSelectActivity.this);
                        mListView.setAdapter(adapter);
                    }
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contact_select);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("选择联系人");
        ImageView mLeftImgv = (ImageView)findViewById(R.id.imgv_leftbtn);
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.ic_icon07);
//		findViewById(R.id.rl_title).setBackgroundColor(getResources().getColor(R.color.purple));
        mListView = (ListView)findViewById(R.id.lv_contact);
        new Thread() {
            public void run() {
                systemConatcts = ContactInfoParser.getSystemContact(lxc.nsu.edu.com.ssft.chapter03.ContactSelectActivity.this);
//				systemConatcts.addAll(ContactInfoParser.getSimContacts(ContactSelectActivity.this));
                mHandler.sendEmptyMessage(10);
            };
        }.start();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                ContactInfo item = (ContactInfo)adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("phone", ContactInfoParser.numFormat(item.phone));
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.imgv_leftbtn:
                finish();
                break;
        }
    }
}
