package lxc.nsu.edu.com.ssft.chapter03.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lxc.nsu.edu.com.ssft.R;
import lxc.nsu.edu.com.ssft.chapter02.entity.ContactInfo;

/**
 * Created by 123 on 2017/7/11.
 */

public class ContactAdapter extends BaseAdapter{
    private List<ContactInfo> contactInfos;
    private Context context;
    public ContactAdapter(List<ContactInfo> contactInfos,Context context){
        super();
        this.contactInfos=contactInfos;
        this.context=context;
    }
    @Override
    public int getCount() {
        return contactInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item_list_contact_select,null);
            holder=new ViewHolder();
            holder.mNameTV=(TextView) convertView.findViewById(R.id.tv_name);
            holder.mPhoneTV=(TextView) convertView.findViewById(R.id.tv_phone);
            holder.mContactImgv=convertView.findViewById(R.id.view1);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        holder.mNameTV.setText(contactInfos.get(position).name);
        holder.mPhoneTV.setText(contactInfos.get(position).phone);
        holder.mNameTV.setTextColor(context.getResources().getColor(R.color.cornflowerblue));
        holder.mPhoneTV.setTextColor(context.getResources().getColor(R.color.cornflowerblue));
        holder.mContactImgv.setBackgroundResource(R.drawable.ic_app01);
        return convertView;
    }

    static class ViewHolder {
        TextView mNameTV;
        TextView mPhoneTV;
        View mContactImgv;
    }
}
