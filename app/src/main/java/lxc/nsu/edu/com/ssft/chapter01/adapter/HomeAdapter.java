package lxc.nsu.edu.com.ssft.chapter01.adapter;

/**
 * Created by 123 on 2017/6/27.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lxc.nsu.edu.com.ssft.R;

/**显示界面用到的Adapter**/
public class HomeAdapter extends BaseAdapter{
    int[] imageId={R.drawable.ic_app04, R.drawable.ic_app11, R.drawable.ic_app05,
            R.drawable.ic_app12, R.drawable.ic_app08, R.drawable.ic_app13,
            R.drawable.ic_app02, R.drawable.ic_app10, R.drawable.ic_app06};
    String[] names={"手机防盗", "通讯卫士", "软件管家",
            "手机杀毒", "缓存清理", "进程管理",
            "流量统计","高级工具","设置中心"};
    private Context context;
    public HomeAdapter(Context context){
        this.context=context;
    }
    //设置GridView一共有多少条目
    public int getCount(){
        return 9;
    }
    //设置每个条目的界面
    public View getView(int position, View convertView, ViewGroup parent){
        View view=View.inflate(context,R.layout.item_home, null);
        ImageView iv_icon=(ImageView) view.findViewById(R.id.iv_icon);
        TextView tv_name=(TextView) view.findViewById(R.id.tv_name);
        iv_icon.setImageResource(imageId[position]);
        tv_name.setText(names[position]);
        return view;
    }
    //后面两个方法暂时不需要设置
    public Object getItem(int position){
        return null;
    }
    public long getItemId(int position){
        return 0;
    }

}
